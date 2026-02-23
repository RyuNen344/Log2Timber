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

package io.github.ryunen344.log2timber.app.test

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("LogNotTimber")
class LogClass {

    // region: verbose
    fun v2() {
        Log.v(TAG, MESSAGE)
    }

    fun v2TagNull() {
        Log.v(null, MESSAGE)
    }

    fun v3() {
        Log.v(TAG, MESSAGE, throwable)
    }

    fun v3MessageNull() {
        Log.v(TAG, null, throwable)
    }

    fun v3ThrowableNull() {
        Log.v(TAG, MESSAGE, null)
    }

    fun v3TagOnly() {
        Log.v(TAG, null, null)
    }

    fun v3TagNull() {
        Log.v(null, MESSAGE, throwable)
    }

    fun v3ThrowableOnly() {
        Log.v(null, null, throwable)
    }

    fun v3MessageOnly() {
        Log.v(null, MESSAGE, null)
    }

    fun v3Null() {
        Log.v(null, null, null)
    }
    // endregion

    // region: debug
    fun d2() {
        Log.d(TAG, MESSAGE)
    }

    fun d2TagNull() {
        Log.d(null, MESSAGE)
    }

    fun d3() {
        Log.d(TAG, MESSAGE, throwable)
    }

    fun d3MessageNull() {
        Log.d(TAG, null, throwable)
    }

    fun d3ThrowableNull() {
        Log.d(TAG, MESSAGE, null)
    }

    fun d3TagOnly() {
        Log.d(TAG, null, null)
    }

    fun d3TagNull() {
        Log.d(null, MESSAGE, throwable)
    }

    fun d3ThrowableOnly() {
        Log.d(null, null, throwable)
    }

    fun d3MessageOnly() {
        Log.d(null, MESSAGE, null)
    }

    fun d3Null() {
        Log.d(null, null, null)
    }
    // endregion

    // region: info
    fun i2() {
        Log.i(TAG, MESSAGE)
    }

    fun i2TagNull() {
        Log.i(null, MESSAGE)
    }

    fun i3() {
        Log.i(TAG, MESSAGE, throwable)
    }

    fun i3MessageNull() {
        Log.i(TAG, null, throwable)
    }

    fun i3ThrowableNull() {
        Log.i(TAG, MESSAGE, null)
    }

    fun i3TagOnly() {
        Log.i(TAG, null, null)
    }

    fun i3TagNull() {
        Log.i(null, MESSAGE, throwable)
    }

    fun i3ThrowableOnly() {
        Log.i(null, null, throwable)
    }

    fun i3MessageOnly() {
        Log.i(null, MESSAGE, null)
    }

    fun i3Null() {
        Log.i(null, null, null)
    }
    // endregion

    // region: warn
    fun w2() {
        Log.w(TAG, MESSAGE)
    }

    fun w2TagNull() {
        Log.w(null, MESSAGE)
    }

    fun w2Throwable() {
        Log.w(TAG, throwable)
    }

    fun w2ThrowableTagNull() {
        Log.w(null, throwable)
    }

    fun w2Null() {
        Log.w(null, null)
    }

    fun w3() {
        Log.w(TAG, MESSAGE, throwable)
    }

    fun w3MessageNull() {
        Log.w(TAG, null, throwable)
    }

    fun w3ThrowableNull() {
        Log.w(TAG, MESSAGE, null)
    }

    fun w3TagOnly() {
        Log.w(TAG, null, null)
    }

    fun w3TagNull() {
        Log.w(null, MESSAGE, throwable)
    }

    fun w3ThrowableOnly() {
        Log.w(null, null, throwable)
    }

    fun w3MessageOnly() {
        Log.w(null, MESSAGE, null)
    }

    fun w3Null() {
        Log.w(null, null, null)
    }
    // endregion

    // region: error
    fun e2() {
        Log.e(TAG, MESSAGE)
    }

    fun e2TagNull() {
        Log.e(null, MESSAGE)
    }

    fun e3() {
        Log.e(TAG, MESSAGE, throwable)
    }

    fun e3MessageNull() {
        Log.e(TAG, null, throwable)
    }

    fun e3ThrowableNull() {
        Log.e(TAG, MESSAGE, null)
    }

    fun e3TagOnly() {
        Log.e(TAG, null, null)
    }

    fun e3TagNull() {
        Log.e(null, MESSAGE, throwable)
    }

    fun e3ThrowableOnly() {
        Log.e(null, null, throwable)
    }

    fun e3MessageOnly() {
        Log.e(null, MESSAGE, null)
    }

    fun e3Null() {
        Log.e(null, null, null)
    }
    // endregion

    // region: wtf
    fun wtf2() {
        Log.wtf(TAG, MESSAGE)
    }

    fun wtf2TagNull() {
        Log.wtf(null, MESSAGE)
    }

    fun wtf2Throwable() {
        Log.wtf(TAG, throwable)
    }

    fun wtf2ThrowableTagNull() {
        Log.wtf(null, throwable)
    }

    fun wtf2Null() {
        Log.wtf(null, null)
    }

    fun wtf3() {
        Log.wtf(TAG, MESSAGE, throwable)
    }

    fun wtf3MessageNull() {
        Log.wtf(TAG, null, throwable)
    }

    fun wtf3ThrowableNull() {
        Log.wtf(TAG, MESSAGE, null)
    }

    fun wtf3TagOnly() {
        Log.wtf(TAG, null, null)
    }

    fun wtf3TagNull() {
        Log.wtf(null, MESSAGE, throwable)
    }

    fun wtf3ThrowableOnly() {
        Log.wtf(null, null, throwable)
    }

    fun wtf3MessageOnly() {
        Log.wtf(null, MESSAGE, null)
    }

    fun wtf3Null() {
        Log.wtf(null, null, null)
    }
    // endregion

    // region: println
    fun println() {
        Log.println(Log.ASSERT, TAG, MESSAGE)
    }

    fun printlnTagNull() {
        Log.println(Log.ASSERT, null, MESSAGE)
    }
    // endregion

    companion object {
        const val TAG = "LogClass"
        const val MESSAGE = "LogClass message"
        val throwable = IllegalStateException("LogClass exception")
    }
}
