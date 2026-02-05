/*
 * Copyright (C) 2026 RyuNen344
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

package io.github.ryunen344.log2timber

import com.android.build.api.artifact.ScopedArtifact
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.DslExtension
import com.android.build.api.variant.ScopedArtifacts
import com.android.build.gradle.AppPlugin
import io.github.ryunen344.log2timber.visitor.Log2TimberVisitorFactory
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Delete
import org.gradle.internal.extensions.stdlib.capitalized
import org.gradle.kotlin.dsl.findPlugin
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register

@Suppress("UnstableApiUsage")
public class Log2TimberPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val plugin = target.plugins.findPlugin(AppPlugin::class)
        if (plugin == null) {
            target.logger.warn("Log2Timber: Android Application Plugin not found. Skipping plugin application.")
        } else {
            target.logger.info("Log2Timber: Android Application Plugin found. Applying Log2TimberPlugin.")
            val androidComponents = target.extensions.getByType(ApplicationAndroidComponentsExtension::class)
            androidComponents.registerExtension(
                DslExtension.Builder(EXTENSION_NAME)
                    .extendProjectWith(Log2TimberDslExtension::class.java)
                    .extendBuildTypeWith(Log2TimberDslExtension::class.java)
                    .extendProductFlavorWith(Log2TimberDslExtension::class.java)
                    .build(),
            ) { config ->
                target.objects.newInstance(Log2TimberVariantDslExtension::class.java, config)
            }
            val common = target.extensions.getByType(CommonExtension::class.java)
            androidComponents.onVariants { variant ->
                variant.runtimeConfiguration.incoming.afterResolve {
                    if (variant.runtimeConfiguration.state == Configuration.State.RESOLVED) {
                        val missing = it.resolutionResult.allComponents
                            .none { result ->
                                val id = result.id
                                id is ModuleComponentIdentifier && id.group == TIMBER_GROUP && id.module == TIMBER_MODULE
                            }

                        if (missing) {
                            target.logger.error("Log2Timber: No dependencies found in runtime configuration for variant '${variant.name}'")
                            target.logger.error("Log2Timber: Log2Timber may occur runtime crash if Timber is not included as a dependency.")
                        }
                    }
                }

                // FIXME implement test
                val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
                val projectExtension = (common as ExtensionAware).extensions.getByType(Log2TimberDslExtension::class.java)
                val enabled = variantExtension?.enabled?.takeIf(Property<Boolean>::isPresent) ?: projectExtension.enabled.convention(true)
                if (enabled.get()) {
                    val forcePlant = variantExtension?.forcePlant?.takeIf(Property<Boolean>::isPresent)
                        ?: projectExtension.forcePlant.convention(true)
                    val dump = variantExtension?.dump?.takeIf(RegularFileProperty::isPresent) ?: projectExtension.dump

                    val intermediates = target.objects.fileProperty()
                    if (dump.isPresent) {
                        intermediates.set(
                            target.layout.buildDirectory.file(
                                "intermediates/log2timber/${variant.name.capitalized()}/${dump.get().asFile.name}",
                            ),
                        )

                        // clean up debug output file before build
                        val cleanupTask = target.tasks.register<Delete>(variant.computeTaskName("clean", "Log2Timber")) {
                            setDelete(dump)
                        }
                        variant.lifecycleTasks.registerPreBuild(cleanupTask)
                        variant.instrumentation.transformClassesWith(
                            Log2TimberVisitorFactory::class.java,
                            InstrumentationScope.ALL,
                        ) {
                            it.forcePlant.set(forcePlant)
                            it.dump.set(intermediates)
                        }

                        val dumpTask = target.tasks.register<DumpLog2TimberTask>(variant.computeTaskName("dump", "Log2Timber")) {
                            input.set(intermediates)
                            output.set(dump)
                        }

                        variant.artifacts
                            .forScope(ScopedArtifacts.Scope.ALL)
                            .use(dumpTask)
                            .toTransform(
                                ScopedArtifact.CLASSES,
                                DumpLog2TimberTask::inputJars,
                                DumpLog2TimberTask::inputDirectories,
                                DumpLog2TimberTask::into,
                            )
                    }

                    // Log to Timber transformation may change the stack map frames.
                    // Therefore, we need to recompute the frames for the instrumented methods.
                    variant.instrumentation.setAsmFramesComputationMode(FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS)
                }
            }
        }
    }

    public companion object {
        /**
         * Minimum supported Android Gradle Plugin version
         *
         * Required for [com.android.build.api.variant.LifecycleTasks.registerPreBuild]
         */
        public const val MINIMUM_SUPPORTED_AGP_VERSION: String = "8.5.2"

        public const val EXTENSION_NAME: String = "log2timber"

        public const val TIMBER_GROUP: String = "com.jakewharton.timber"

        public const val TIMBER_MODULE: String = "timber"
    }
}
