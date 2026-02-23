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

package io.github.ryunen344.log2timber.app.test.instance

import android.annotation.SuppressLint
import android.util.Log

@Suppress("UtilityClassWithPublicConstructor")
@SuppressLint("LogNotTimber")
class LogCompanionStatic {
    companion object {
        const val TAG = $$"LogCompanionStatic$Comp"
        const val MESSAGE = $$"LogCompanionStatic$Comp message"
        val throwable = IllegalStateException($$"LogCompanionStatic$Comp exception")

        // region: verbose
        @JvmStatic
        fun vIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.VERBOSE)
        }

        @JvmStatic
        fun vIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.VERBOSE)
        }

        @JvmStatic
        fun v2() {
            Log.v(TAG, MESSAGE)
        }

        @JvmStatic
        fun v2TagNull() {
            Log.v(null, MESSAGE)
        }

        @JvmStatic
        fun v3() {
            Log.v(TAG, MESSAGE, throwable)
        }

        @JvmStatic
        fun v3MessageNull() {
            Log.v(TAG, null, throwable)
        }

        @JvmStatic
        fun v3ThrowableNull() {
            Log.v(TAG, MESSAGE, null)
        }

        @JvmStatic
        fun v3TagOnly() {
            Log.v(TAG, null, null)
        }

        @JvmStatic
        fun v3TagNull() {
            Log.v(null, MESSAGE, throwable)
        }

        @JvmStatic
        fun v3ThrowableOnly() {
            Log.v(null, null, throwable)
        }

        @JvmStatic
        fun v3MessageOnly() {
            Log.v(null, MESSAGE, null)
        }

        @JvmStatic
        fun v3Null() {
            Log.v(null, null, null)
        }
        // endregion

        // region: debug
        @JvmStatic
        fun dIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.DEBUG)
        }

        @JvmStatic
        fun dIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.DEBUG)
        }

        @JvmStatic
        fun d2() {
            Log.d(TAG, MESSAGE)
        }

        @JvmStatic
        fun d2TagNull() {
            Log.d(null, MESSAGE)
        }

        @JvmStatic
        fun d3() {
            Log.d(TAG, MESSAGE, throwable)
        }

        @JvmStatic
        fun d3MessageNull() {
            Log.d(TAG, null, throwable)
        }

        @JvmStatic
        fun d3ThrowableNull() {
            Log.d(TAG, MESSAGE, null)
        }

        @JvmStatic
        fun d3TagOnly() {
            Log.d(TAG, null, null)
        }

        @JvmStatic
        fun d3TagNull() {
            Log.d(null, MESSAGE, throwable)
        }

        @JvmStatic
        fun d3ThrowableOnly() {
            Log.d(null, null, throwable)
        }

        @JvmStatic
        fun d3MessageOnly() {
            Log.d(null, MESSAGE, null)
        }

        @JvmStatic
        fun d3Null() {
            Log.d(null, null, null)
        }
        // endregion

        // region: info
        @JvmStatic
        fun iIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.INFO)
        }

        @JvmStatic
        fun iIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.INFO)
        }

        @JvmStatic
        fun i2() {
            Log.i(TAG, MESSAGE)
        }

        @JvmStatic
        fun i2TagNull() {
            Log.i(null, MESSAGE)
        }

        @JvmStatic
        fun i3() {
            Log.i(TAG, MESSAGE, throwable)
        }

        @JvmStatic
        fun i3MessageNull() {
            Log.i(TAG, null, throwable)
        }

        @JvmStatic
        fun i3ThrowableNull() {
            Log.i(TAG, MESSAGE, null)
        }

        @JvmStatic
        fun i3TagOnly() {
            Log.i(TAG, null, null)
        }

        @JvmStatic
        fun i3TagNull() {
            Log.i(null, MESSAGE, throwable)
        }

        @JvmStatic
        fun i3ThrowableOnly() {
            Log.i(null, null, throwable)
        }

        @JvmStatic
        fun i3MessageOnly() {
            Log.i(null, MESSAGE, null)
        }

        @JvmStatic
        fun i3Null() {
            Log.i(null, null, null)
        }
        // endregion

        // region: warn
        @JvmStatic
        fun wIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.WARN)
        }

        @JvmStatic
        fun wIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.WARN)
        }

        @JvmStatic
        fun w2() {
            Log.w(TAG, MESSAGE)
        }

        @JvmStatic
        fun w2TagNull() {
            Log.w(null, MESSAGE)
        }

        @JvmStatic
        fun w2Throwable() {
            Log.w(TAG, throwable)
        }

        @JvmStatic
        fun w2ThrowableTagNull() {
            Log.w(null, throwable)
        }

        @JvmStatic
        fun w2Null() {
            Log.w(null, null)
        }

        @JvmStatic
        fun w3() {
            Log.w(TAG, MESSAGE, throwable)
        }

        @JvmStatic
        fun w3MessageNull() {
            Log.w(TAG, null, throwable)
        }

        @JvmStatic
        fun w3ThrowableNull() {
            Log.w(TAG, MESSAGE, null)
        }

        @JvmStatic
        fun w3TagOnly() {
            Log.w(TAG, null, null)
        }

        @JvmStatic
        fun w3TagNull() {
            Log.w(null, MESSAGE, throwable)
        }

        @JvmStatic
        fun w3ThrowableOnly() {
            Log.w(null, null, throwable)
        }

        @JvmStatic
        fun w3MessageOnly() {
            Log.w(null, MESSAGE, null)
        }

        @JvmStatic
        fun w3Null() {
            Log.w(null, null, null)
        }
        // endregion

        // region: error
        @JvmStatic
        fun eIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.ERROR)
        }

        @JvmStatic
        fun eIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.ERROR)
        }

        @JvmStatic
        fun e2() {
            Log.e(TAG, MESSAGE)
        }

        @JvmStatic
        fun e2TagNull() {
            Log.e(null, MESSAGE)
        }

        @JvmStatic
        fun e3() {
            Log.e(TAG, MESSAGE, throwable)
        }

        @JvmStatic
        fun e3MessageNull() {
            Log.e(TAG, null, throwable)
        }

        @JvmStatic
        fun e3ThrowableNull() {
            Log.e(TAG, MESSAGE, null)
        }

        @JvmStatic
        fun e3TagOnly() {
            Log.e(TAG, null, null)
        }

        @JvmStatic
        fun e3TagNull() {
            Log.e(null, MESSAGE, throwable)
        }

        @JvmStatic
        fun e3ThrowableOnly() {
            Log.e(null, null, throwable)
        }

        @JvmStatic
        fun e3MessageOnly() {
            Log.e(null, MESSAGE, null)
        }

        @JvmStatic
        fun e3Null() {
            Log.e(null, null, null)
        }
        // endregion

        // region: wtf
        @JvmStatic
        fun wtfIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.ASSERT)
        }

        @JvmStatic
        fun wtfIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.ASSERT)
        }

        @JvmStatic
        fun wtf2() {
            Log.wtf(TAG, MESSAGE)
        }

        @JvmStatic
        fun wtf2TagNull() {
            Log.wtf(null, MESSAGE)
        }

        @JvmStatic
        fun wtf2Throwable() {
            Log.wtf(TAG, throwable)
        }

        @JvmStatic
        fun wtf2ThrowableTagNull() {
            Log.wtf(null, throwable)
        }

        @JvmStatic
        fun wtf2Null() {
            Log.wtf(null, null)
        }

        @JvmStatic
        fun wtf3() {
            Log.wtf(TAG, MESSAGE, throwable)
        }

        @JvmStatic
        fun wtf3MessageNull() {
            Log.wtf(TAG, null, throwable)
        }

        @JvmStatic
        fun wtf3ThrowableNull() {
            Log.wtf(TAG, MESSAGE, null)
        }

        @JvmStatic
        fun wtf3TagOnly() {
            Log.wtf(TAG, null, null)
        }

        @JvmStatic
        fun wtf3TagNull() {
            Log.wtf(null, MESSAGE, throwable)
        }

        @JvmStatic
        fun wtf3ThrowableOnly() {
            Log.wtf(null, null, throwable)
        }

        @JvmStatic
        fun wtf3MessageOnly() {
            Log.wtf(null, MESSAGE, null)
        }

        @JvmStatic
        fun wtf3Null() {
            Log.wtf(null, null, null)
        }
        // endregion

        // region: println
        @JvmStatic
        fun println() {
            Log.println(Log.ASSERT, TAG, MESSAGE)
        }

        @JvmStatic
        fun printlnTagNull() {
            Log.println(Log.ASSERT, null, MESSAGE)
        }
        // endregion
    }
}
