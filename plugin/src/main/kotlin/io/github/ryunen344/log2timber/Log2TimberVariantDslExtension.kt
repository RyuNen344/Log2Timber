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

import com.android.build.api.variant.VariantExtension
import com.android.build.api.variant.VariantExtensionConfig
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import java.io.Serializable
import javax.inject.Inject

/**
 * DSL extension for Log2Timber per variant.
 *
 * Build Type > Product Flavor
 */
public abstract class Log2TimberVariantDslExtension @Inject constructor(
    config: VariantExtensionConfig<*>,
) : VariantExtension, Serializable {
    /**
     * Default to true, enable instrumentation.
     */
    public abstract val enabled: Property<Boolean>

    /**
     * If true, replace Log.isLoggable calls with true to force all logs to be kept.
     */
    public abstract val forcePlant: Property<Boolean>

    /**
     * If given file path, debug information will be output to the file.
     */
    public abstract val dump: RegularFileProperty

    init {
        // FIXME implement test
        val productFlavors = config.productFlavorsExtensions(Log2TimberDslExtension::class.java)
        val buildType = config.buildTypeExtension(Log2TimberDslExtension::class.java)
        (productFlavors.reversed() + listOf(buildType)).forEach { extension ->
            extension.enabled
                .takeIf(Property<Boolean>::isPresent)
                ?.let(enabled::set)

            extension.forcePlant
                .takeIf(Property<Boolean>::isPresent)
                ?.let(forcePlant::set)

            extension.dump
                .takeIf(RegularFileProperty::isPresent)
                ?.let(dump::set)
        }
    }

    private companion object {
        private const val serialVersionUID = 23466453743L
    }
}
