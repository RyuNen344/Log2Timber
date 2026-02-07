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

package io.github.ryunen344.log2timber.assertions

import assertk.Assert
import assertk.assertions.prop
import assertk.assertions.support.expected
import org.gradle.api.provider.Provider

fun <T> Assert<Provider<T>>.value(): Assert<T> = prop("value") { actual -> actual.get() }

fun <T> Assert<Provider<T>>.isPresent(): Assert<Provider<T>> = transform { actual ->
    if (actual.isPresent) {
        actual
    } else {
        expected("to be present")
    }
}

fun <T> Assert<Provider<T>>.isAbsent() = given { actual ->
    if (!actual.isPresent) return
    expected("to be absent")
}
