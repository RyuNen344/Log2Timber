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

package io.github.ryunen344.log2timber.app.test.timber

import timber.log.Timber

class TestingTree : Timber.DebugTree() {

    private val _stack: MutableList<TimberRecord> = mutableListOf()
    val stack: List<TimberRecord> = _stack

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        _stack.add(
            TimberRecord(
                priority = priority,
                tag = tag,
                message = message,
                t = t,
            ),
        )
    }
}
