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

import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.property
import javax.inject.Inject

public abstract class Log2TimberDslExtension @Inject constructor(objects: ObjectFactory) {
    /**
     * Default to true, enable instrumentation.
     */
    public val enabled: Property<Boolean> = objects.property<Boolean>()

    /**
     * Default to true, replace Log.isLoggable calls with true to force all logs to be kept.
     */
    public val forcePlant: Property<Boolean> = objects.property<Boolean>()

    /**
     * If given file path, debug information will be output to the file.
     */
    public val dump: RegularFileProperty = objects.fileProperty()
}
