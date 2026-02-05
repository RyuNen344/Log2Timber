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

package io.github.ryunen344.log2timber.dsl

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationProductFlavor
import io.github.ryunen344.log2timber.Log2TimberDslExtension
import org.gradle.api.Action
import org.gradle.api.plugins.ExtensionAware

public fun ApplicationBuildType.log2timber(action: Action<Log2TimberDslExtension>) {
    (this as ExtensionAware).extensions.configure(Log2TimberDslExtension::class.java, action)
}

public fun ApplicationProductFlavor.log2timber(action: Action<Log2TimberDslExtension>) {
    (this as ExtensionAware).extensions.configure(Log2TimberDslExtension::class.java, action)
}
