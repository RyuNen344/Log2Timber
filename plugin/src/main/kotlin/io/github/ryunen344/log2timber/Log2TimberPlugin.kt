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

import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findPlugin
import org.gradle.kotlin.dsl.getByType

public class Log2TimberPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val plugin = target.plugins.findPlugin(AppPlugin::class)
        if (plugin == null) {
            target.logger.lifecycle("Cat2TimberPlugin: Android Application Plugin not found. Skipping plugin application.")
        } else {
            target.logger.lifecycle("Cat2TimberPlugin: Android Application Plugin found. Applying Cat2TimberPlugin.")
            target.extensions.getByType(ApplicationAndroidComponentsExtension::class).onVariants { variant ->
                variant.instrumentation.transformClassesWith(
                    Log2TimberVisitorFactory::class.java,
                    InstrumentationScope.ALL,
                ) {
                    // No parameters to set
                }
            }

            target.configurations.forEach {
                target.logger.lifecycle("Configuration: ${it.name}")
            }
        }
    }
}
