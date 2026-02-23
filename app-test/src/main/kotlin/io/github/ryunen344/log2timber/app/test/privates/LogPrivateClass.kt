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

package io.github.ryunen344.log2timber.app.test.privates

import android.annotation.SuppressLint
import android.util.Log

class LogPrivateClass {

    // region: verbose
    fun vIsLoggable(): Boolean {
        return Private().vIsLoggable()
    }

    fun vIsLoggableTagNull(): Boolean {
        return Private().vIsLoggableTagNull()
    }

    fun v2() {
        Private().v2()
    }

    fun v2TagNull() {
        Private().v2TagNull()
    }

    fun v3() {
        Private().v3()
    }

    fun v3MessageNull() {
        Private().v3MessageNull()
    }

    fun v3ThrowableNull() {
        Private().v3ThrowableNull()
    }

    fun v3TagOnly() {
        Private().v3TagOnly()
    }

    fun v3TagNull() {
        Private().v3TagNull()
    }

    fun v3ThrowableOnly() {
        Private().v3ThrowableOnly()
    }

    fun v3MessageOnly() {
        Private().v3MessageOnly()
    }

    fun v3Null() {
        Private().v3Null()
    }
    // endregion

    // region: debug
    fun dIsLoggable(): Boolean {
        return Private().dIsLoggable()
    }

    fun dIsLoggableTagNull(): Boolean {
        return Private().dIsLoggableTagNull()
    }

    fun d2() {
        Private().d2()
    }

    fun d2TagNull() {
        Private().d2TagNull()
    }

    fun d3() {
        Private().d3()
    }

    fun d3MessageNull() {
        Private().d3MessageNull()
    }

    fun d3ThrowableNull() {
        Private().d3ThrowableNull()
    }

    fun d3TagOnly() {
        Private().d3TagOnly()
    }

    fun d3TagNull() {
        Private().d3TagNull()
    }

    fun d3ThrowableOnly() {
        Private().d3ThrowableOnly()
    }

    fun d3MessageOnly() {
        Private().d3MessageOnly()
    }

    fun d3Null() {
        Private().d3Null()
    }
    // endregion

    // region: info
    fun iIsLoggable(): Boolean {
        return Private().iIsLoggable()
    }

    fun iIsLoggableTagNull(): Boolean {
        return Private().iIsLoggableTagNull()
    }

    fun i2() {
        Private().i2()
    }

    fun i2TagNull() {
        Private().i2TagNull()
    }

    fun i3() {
        Private().i3()
    }

    fun i3MessageNull() {
        Private().i3MessageNull()
    }

    fun i3ThrowableNull() {
        Private().i3ThrowableNull()
    }

    fun i3TagOnly() {
        Private().i3TagOnly()
    }

    fun i3TagNull() {
        Private().i3TagNull()
    }

    fun i3ThrowableOnly() {
        Private().i3ThrowableOnly()
    }

    fun i3MessageOnly() {
        Private().i3MessageOnly()
    }

    fun i3Null() {
        Private().i3Null()
    }
    // endregion

    // region: warn
    fun wIsLoggable(): Boolean {
        return Private().wIsLoggable()
    }

    fun wIsLoggableTagNull(): Boolean {
        return Private().wIsLoggableTagNull()
    }

    fun w2() {
        Private().w2()
    }

    fun w2TagNull() {
        Private().w2TagNull()
    }

    fun w2Throwable() {
        Private().w2Throwable()
    }

    fun w2ThrowableTagNull() {
        Private().w2ThrowableTagNull()
    }

    fun w2Null() {
        Private().w2Null()
    }

    fun w3() {
        Private().w3()
    }

    fun w3MessageNull() {
        Private().w3MessageNull()
    }

    fun w3ThrowableNull() {
        Private().w3ThrowableNull()
    }

    fun w3TagOnly() {
        Private().w3TagOnly()
    }

    fun w3TagNull() {
        Private().w3TagNull()
    }

    fun w3ThrowableOnly() {
        Private().w3ThrowableOnly()
    }

    fun w3MessageOnly() {
        Private().w3MessageOnly()
    }

    fun w3Null() {
        Private().w3Null()
    }
    // endregion

    // region: error
    fun eIsLoggable(): Boolean {
        return Private().eIsLoggable()
    }

    fun eIsLoggableTagNull(): Boolean {
        return Private().eIsLoggableTagNull()
    }

    fun e2() {
        Private().e2()
    }

    fun e2TagNull() {
        Private().e2TagNull()
    }

    fun e3() {
        Private().e3()
    }

    fun e3MessageNull() {
        Private().e3MessageNull()
    }

    fun e3ThrowableNull() {
        Private().e3ThrowableNull()
    }

    fun e3TagOnly() {
        Private().e3TagOnly()
    }

    fun e3TagNull() {
        Private().e3TagNull()
    }

    fun e3ThrowableOnly() {
        Private().e3ThrowableOnly()
    }

    fun e3MessageOnly() {
        Private().e3MessageOnly()
    }

    fun e3Null() {
        Private().e3Null()
    }
    // endregion

    // region: wtf
    fun wtfIsLoggable(): Boolean {
        return Private().wtfIsLoggable()
    }

    fun wtfIsLoggableTagNull(): Boolean {
        return Private().wtfIsLoggableTagNull()
    }

    fun wtf2() {
        Private().wtf2()
    }

    fun wtf2TagNull() {
        Private().wtf2TagNull()
    }

    fun wtf2Throwable() {
        Private().wtf2Throwable()
    }

    fun wtf2ThrowableTagNull() {
        Private().wtf2ThrowableTagNull()
    }

    fun wtf2Null() {
        Private().wtf2Null()
    }

    fun wtf3() {
        Private().wtf3()
    }

    fun wtf3MessageNull() {
        Private().wtf3MessageNull()
    }

    fun wtf3ThrowableNull() {
        Private().wtf3ThrowableNull()
    }

    fun wtf3TagOnly() {
        Private().wtf3TagOnly()
    }

    fun wtf3TagNull() {
        Private().wtf3TagNull()
    }

    fun wtf3ThrowableOnly() {
        Private().wtf3ThrowableOnly()
    }

    fun wtf3MessageOnly() {
        Private().wtf3MessageOnly()
    }

    fun wtf3Null() {
        Private().wtf3Null()
    }
    // endregion

    // region: println
    fun println() {
        Private().println()
    }

    fun printlnTagNull() {
        Private().printlnTagNull()
    }
    // endregion

    @SuppressLint("LogNotTimber")
    private class Private {
        // region: verbose
        fun vIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.VERBOSE)
        }

        fun vIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.VERBOSE)
        }

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
        fun dIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.DEBUG)
        }

        fun dIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.DEBUG)
        }

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
        fun iIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.INFO)
        }

        fun iIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.INFO)
        }

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
        fun wIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.WARN)
        }

        fun wIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.WARN)
        }

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
        fun eIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.ERROR)
        }

        fun eIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.ERROR)
        }

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
        fun wtfIsLoggable(): Boolean {
            return Log.isLoggable(TAG, Log.ASSERT)
        }

        fun wtfIsLoggableTagNull(): Boolean {
            return Log.isLoggable(null, Log.ASSERT)
        }

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
    }

    companion object {
        const val TAG = $$"LogPrivateClass$Private"
        const val MESSAGE = $$"LogPrivateClass$Private message"
        val throwable = IllegalStateException($$"LogPrivateClass$Private exception")
    }
}
