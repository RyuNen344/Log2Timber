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

package io.github.ryunen344.log2timber.app.groovy

import android.util.Log

@Suppress("LogNotTimber", "UNUSED")
fun calls() {
    val tag = "Calls2"
    val throwable = IllegalArgumentException("Example exception")
    Log.v(tag, "This is a verbose log message.")
    Log.v(tag, "This is a verbose log message.", throwable)
    Log.d(tag, "This is a debug log message.")
    Log.d(tag, "This is a debug log message.", throwable)
    Log.i(tag, "This is an info log message.")
    Log.i(tag, "This is an info log message.", throwable)
    Log.w(tag, "This is an warning log message.")
    Log.w(tag, "This is an warning log message.", throwable)
    Log.w(tag, throwable)
    Log.e(tag, "This is an error log message.")
    Log.e(tag, "This is an error log message.", throwable)
    Log.wtf(tag, "This is an what terrible failure log message.")
    Log.wtf(tag, "This is an what terrible failure log message.", throwable)
    Log.wtf(tag, throwable)
    Log.println(Log.VERBOSE, tag, "This is a println verbose log message.")
    Log.println(Log.VERBOSE, tag, "This is a println verbose log message.")
    Log.println(Log.DEBUG, tag, "This is a println debug log message.")
    Log.println(Log.INFO, tag, "This is a println info log message.")
    Log.println(Log.WARN, tag, "This is a println warning log message.")
    Log.println(Log.ERROR, tag, "This is a println error log message.")
    Log.println(Log.ASSERT, tag, "This is a println assert log message.")
}
