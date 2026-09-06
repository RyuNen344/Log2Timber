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

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.DslExtension
import com.android.build.gradle.AppPlugin
import io.github.ryunen344.log2timber.visitor.Log2TimberVisitorFactory
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.result.ResolvedComponentResult
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.getByType

public class Log2TimberPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.withType(AppPlugin::class.java) { _ ->
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
                val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
                val projectExtension = (common as ExtensionAware).extensions.getByType(Log2TimberDslExtension::class.java)
                val enabled = variantExtension?.enabled?.takeIf(Property<Boolean>::isPresent) ?: projectExtension.enabled.convention(true)

                // AGP offers no lazy counterpart for transformClassesWith
                if (enabled.get()) {
                    val forcePlant = variantExtension?.forcePlant?.takeIf(Property<Boolean>::isPresent)
                        ?: projectExtension.forcePlant.convention(true)
                    val dump = variantExtension?.dump?.takeIf(RegularFileProperty::isPresent) ?: projectExtension.dump

                    val verify = target.tasks.register(
                        "verify${variant.name.replaceFirstChar(Char::uppercaseChar)}TimberDependency",
                        VerifyTimberDependencyTask::class.java,
                    ) {
                        it.variantName.set(variant.name)
                        it.timberMissing.set(
                            variant.runtimeConfiguration.incoming.resolutionResult.rootComponent
                                .map(ResolvedComponentResult::isTimberMissing),
                        )
                    }
                    variant.lifecycleTasks.registerPreBuild(verify)

                    val service = target.gradle.sharedServices.registerIfAbsent(
                        "log2timber-${target.path}-${variant.name}-dump",
                        DumpWriterService::class.java,
                    ) {
                        it.parameters.dump.set(dump)
                        it.maxParallelUsages.set(1)
                    }

                    variant.instrumentation.transformClassesWith(
                        Log2TimberVisitorFactory::class.java,
                        InstrumentationScope.ALL,
                    ) {
                        it.service.set(service)
                        it.forcePlant.set(forcePlant)
                    }

                    // Log to Timber transformation may change the stack map frames.
                    // Therefore, we need to recompute the frames for the instrumented methods.
                    variant.instrumentation.setAsmFramesComputationMode(FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS)
                } else {
                    target.logger.info("Log2Timber: instrumentation is disabled for variant '${variant.name}'. Skipping.")
                }
            }
        }
        target.afterEvaluate {
            if (!it.plugins.hasPlugin(AppPlugin::class.java)) {
                it.logger.warn("Log2Timber: Android Application Plugin not found. Skipping plugin application.")
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
