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

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

/**
 * Warns when the instrumented variant does not carry Timber on its runtime classpath.
 *
 * Resolution happens through a lazy [Property], so the runtime configuration stays unresolved
 * during configuration and the task remains configuration cache compatible.
 */
public abstract class VerifyTimberDependencyTask : DefaultTask() {

    @get:Input
    public abstract val variantName: Property<String>

    @get:Input
    public abstract val instrumentationEnabled: Property<Boolean>

    @get:Input
    public abstract val timberMissing: Property<Boolean>

    @TaskAction
    public fun verify() {
        if (!instrumentationEnabled.get() || !timberMissing.get()) return
        logger.error("Log2Timber: No Timber dependency found in runtime configuration for variant '${variantName.get()}'")
        logger.error("Log2Timber: Log2Timber may occur runtime crash if Timber is not included as a dependency.")
    }
}
