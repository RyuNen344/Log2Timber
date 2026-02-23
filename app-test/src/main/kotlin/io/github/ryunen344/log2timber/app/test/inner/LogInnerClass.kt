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

package io.github.ryunen344.log2timber.app.test.inner

import android.annotation.SuppressLint
import android.util.Log

class LogInnerClass {

    fun touch() {
        // noop
    }

    @SuppressLint("LogNotTimber")
    inner class InnerClass {
        // region: verbose
        fun vIsLoggable(): Boolean {
            touch()
            return Log.isLoggable(TAG, Log.VERBOSE)
        }

        fun vIsLoggableTagNull(): Boolean {
            touch()
            return Log.isLoggable(null, Log.VERBOSE)
        }

        fun v2() {
            touch()
            Log.v(TAG, MESSAGE)
        }

        fun v2TagNull() {
            touch()
            Log.v(null, MESSAGE)
        }

        fun v3() {
            touch()
            Log.v(TAG, MESSAGE, throwable)
        }

        fun v3MessageNull() {
            touch()
            Log.v(TAG, null, throwable)
        }

        fun v3ThrowableNull() {
            touch()
            Log.v(TAG, MESSAGE, null)
        }

        fun v3TagOnly() {
            touch()
            Log.v(TAG, null, null)
        }

        fun v3TagNull() {
            touch()
            Log.v(null, MESSAGE, throwable)
        }

        fun v3ThrowableOnly() {
            touch()
            Log.v(null, null, throwable)
        }

        fun v3MessageOnly() {
            touch()
            Log.v(null, MESSAGE, null)
        }

        fun v3Null() {
            touch()
            Log.v(null, null, null)
        }
        // endregion

        // region: debug
        fun dIsLoggable(): Boolean {
            touch()
            return Log.isLoggable(TAG, Log.DEBUG)
        }

        fun dIsLoggableTagNull(): Boolean {
            touch()
            return Log.isLoggable(null, Log.DEBUG)
        }

        fun d2() {
            touch()
            Log.d(TAG, MESSAGE)
        }

        fun d2TagNull() {
            touch()
            Log.d(null, MESSAGE)
        }

        fun d3() {
            touch()
            Log.d(TAG, MESSAGE, throwable)
        }

        fun d3MessageNull() {
            touch()
            Log.d(TAG, null, throwable)
        }

        fun d3ThrowableNull() {
            touch()
            Log.d(TAG, MESSAGE, null)
        }

        fun d3TagOnly() {
            touch()
            Log.d(TAG, null, null)
        }

        fun d3TagNull() {
            touch()
            Log.d(null, MESSAGE, throwable)
        }

        fun d3ThrowableOnly() {
            touch()
            Log.d(null, null, throwable)
        }

        fun d3MessageOnly() {
            touch()
            Log.d(null, MESSAGE, null)
        }

        fun d3Null() {
            touch()
            Log.d(null, null, null)
        }
        // endregion

        // region: info
        fun iIsLoggable(): Boolean {
            touch()
            return Log.isLoggable(TAG, Log.INFO)
        }

        fun iIsLoggableTagNull(): Boolean {
            touch()
            return Log.isLoggable(null, Log.INFO)
        }

        fun i2() {
            touch()
            Log.i(TAG, MESSAGE)
        }

        fun i2TagNull() {
            touch()
            Log.i(null, MESSAGE)
        }

        fun i3() {
            touch()
            Log.i(TAG, MESSAGE, throwable)
        }

        fun i3MessageNull() {
            touch()
            Log.i(TAG, null, throwable)
        }

        fun i3ThrowableNull() {
            touch()
            Log.i(TAG, MESSAGE, null)
        }

        fun i3TagOnly() {
            touch()
            Log.i(TAG, null, null)
        }

        fun i3TagNull() {
            touch()
            Log.i(null, MESSAGE, throwable)
        }

        fun i3ThrowableOnly() {
            touch()
            Log.i(null, null, throwable)
        }

        fun i3MessageOnly() {
            touch()
            Log.i(null, MESSAGE, null)
        }

        fun i3Null() {
            touch()
            Log.i(null, null, null)
        }
        // endregion

        // region: warn
        fun wIsLoggable(): Boolean {
            touch()
            return Log.isLoggable(TAG, Log.WARN)
        }

        fun wIsLoggableTagNull(): Boolean {
            touch()
            return Log.isLoggable(null, Log.WARN)
        }

        fun w2() {
            touch()
            Log.w(TAG, MESSAGE)
        }

        fun w2TagNull() {
            touch()
            Log.w(null, MESSAGE)
        }

        fun w2Throwable() {
            touch()
            Log.w(TAG, throwable)
        }

        fun w2ThrowableTagNull() {
            touch()
            Log.w(null, throwable)
        }

        fun w2Null() {
            touch()
            Log.w(null, null)
        }

        fun w3() {
            touch()
            Log.w(TAG, MESSAGE, throwable)
        }

        fun w3MessageNull() {
            touch()
            Log.w(TAG, null, throwable)
        }

        fun w3ThrowableNull() {
            touch()
            Log.w(TAG, MESSAGE, null)
        }

        fun w3TagOnly() {
            touch()
            Log.w(TAG, null, null)
        }

        fun w3TagNull() {
            touch()
            Log.w(null, MESSAGE, throwable)
        }

        fun w3ThrowableOnly() {
            touch()
            Log.w(null, null, throwable)
        }

        fun w3MessageOnly() {
            touch()
            Log.w(null, MESSAGE, null)
        }

        fun w3Null() {
            touch()
            Log.w(null, null, null)
        }
        // endregion

        // region: error
        fun eIsLoggable(): Boolean {
            touch()
            return Log.isLoggable(TAG, Log.ERROR)
        }

        fun eIsLoggableTagNull(): Boolean {
            touch()
            return Log.isLoggable(null, Log.ERROR)
        }

        fun e2() {
            touch()
            Log.e(TAG, MESSAGE)
        }

        fun e2TagNull() {
            touch()
            Log.e(null, MESSAGE)
        }

        fun e3() {
            touch()
            Log.e(TAG, MESSAGE, throwable)
        }

        fun e3MessageNull() {
            touch()
            Log.e(TAG, null, throwable)
        }

        fun e3ThrowableNull() {
            touch()
            Log.e(TAG, MESSAGE, null)
        }

        fun e3TagOnly() {
            touch()
            Log.e(TAG, null, null)
        }

        fun e3TagNull() {
            touch()
            Log.e(null, MESSAGE, throwable)
        }

        fun e3ThrowableOnly() {
            touch()
            Log.e(null, null, throwable)
        }

        fun e3MessageOnly() {
            touch()
            Log.e(null, MESSAGE, null)
        }

        fun e3Null() {
            touch()
            Log.e(null, null, null)
        }
        // endregion

        // region: wtf
        fun wtfIsLoggable(): Boolean {
            touch()
            return Log.isLoggable(TAG, Log.ASSERT)
        }

        fun wtfIsLoggableTagNull(): Boolean {
            touch()
            return Log.isLoggable(null, Log.ASSERT)
        }

        fun wtf2() {
            touch()
            Log.wtf(TAG, MESSAGE)
        }

        fun wtf2TagNull() {
            touch()
            Log.wtf(null, MESSAGE)
        }

        fun wtf2Throwable() {
            touch()
            Log.wtf(TAG, throwable)
        }

        fun wtf2ThrowableTagNull() {
            touch()
            Log.wtf(null, throwable)
        }

        fun wtf2Null() {
            touch()
            Log.wtf(null, null)
        }

        fun wtf3() {
            touch()
            Log.wtf(TAG, MESSAGE, throwable)
        }

        fun wtf3MessageNull() {
            touch()
            Log.wtf(TAG, null, throwable)
        }

        fun wtf3ThrowableNull() {
            touch()
            Log.wtf(TAG, MESSAGE, null)
        }

        fun wtf3TagOnly() {
            touch()
            Log.wtf(TAG, null, null)
        }

        fun wtf3TagNull() {
            touch()
            Log.wtf(null, MESSAGE, throwable)
        }

        fun wtf3ThrowableOnly() {
            touch()
            Log.wtf(null, null, throwable)
        }

        fun wtf3MessageOnly() {
            touch()
            Log.wtf(null, MESSAGE, null)
        }

        fun wtf3Null() {
            touch()
            Log.wtf(null, null, null)
        }
        // endregion

        // region: println
        fun println() {
            touch()
            Log.println(Log.ASSERT, TAG, MESSAGE)
        }

        fun printlnTagNull() {
            touch()
            Log.println(Log.ASSERT, null, MESSAGE)
        }
        // endregion
    }

    companion object {
        const val TAG = $$"LogInnerClass$InnerClas"
        const val MESSAGE = $$"LogInnerClass$InnerClas message"
        val throwable = IllegalStateException($$"LogInnerClass$InnerClas exception")
    }
}
