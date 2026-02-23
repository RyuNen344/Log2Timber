@file:SuppressLint("LogNotTimber")

/*
 * Copyright (C) 2026-2026 RyuNen344
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

package io.github.ryunen344.log2timber.app.test.top

import android.annotation.SuppressLint
import android.util.Log

const val TOP_LEVEL_TAG = "LogTopLevelKt"
const val TOP_LEVEL_MESSAGE = "LogTopLevel message"
val topLevelThrowable = IllegalStateException("LogTopLevel exception")

// region: verbose
fun vIsLoggable(): Boolean {
    return Log.isLoggable(TOP_LEVEL_TAG, Log.VERBOSE)
}

fun vIsLoggableTagNull(): Boolean {
    return Log.isLoggable(null, Log.VERBOSE)
}

fun v2() {
    Log.v(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE)
}

fun v2TagNull() {
    Log.v(null, TOP_LEVEL_MESSAGE)
}

fun v3() {
    Log.v(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun v3MessageNull() {
    Log.v(TOP_LEVEL_TAG, null, topLevelThrowable)
}

fun v3ThrowableNull() {
    Log.v(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, null)
}

fun v3TagOnly() {
    Log.v(TOP_LEVEL_TAG, null, null)
}

fun v3TagNull() {
    Log.v(null, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun v3ThrowableOnly() {
    Log.v(null, null, topLevelThrowable)
}

fun v3MessageOnly() {
    Log.v(null, TOP_LEVEL_MESSAGE, null)
}

fun v3Null() {
    Log.v(null, null, null)
}
// endregion

// region: debug
fun dIsLoggable(): Boolean {
    return Log.isLoggable(TOP_LEVEL_TAG, Log.DEBUG)
}

fun dIsLoggableTagNull(): Boolean {
    return Log.isLoggable(null, Log.DEBUG)
}

fun d2() {
    Log.d(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE)
}

fun d2TagNull() {
    Log.d(null, TOP_LEVEL_MESSAGE)
}

fun d3() {
    Log.d(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun d3MessageNull() {
    Log.d(TOP_LEVEL_TAG, null, topLevelThrowable)
}

fun d3ThrowableNull() {
    Log.d(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, null)
}

fun d3TagOnly() {
    Log.d(TOP_LEVEL_TAG, null, null)
}

fun d3TagNull() {
    Log.d(null, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun d3ThrowableOnly() {
    Log.d(null, null, topLevelThrowable)
}

fun d3MessageOnly() {
    Log.d(null, TOP_LEVEL_MESSAGE, null)
}

fun d3Null() {
    Log.d(null, null, null)
}
// endregion

// region: info
fun iIsLoggable(): Boolean {
    return Log.isLoggable(TOP_LEVEL_TAG, Log.INFO)
}

fun iIsLoggableTagNull(): Boolean {
    return Log.isLoggable(null, Log.INFO)
}

fun i2() {
    Log.i(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE)
}

fun i2TagNull() {
    Log.i(null, TOP_LEVEL_MESSAGE)
}

fun i3() {
    Log.i(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun i3MessageNull() {
    Log.i(TOP_LEVEL_TAG, null, topLevelThrowable)
}

fun i3ThrowableNull() {
    Log.i(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, null)
}

fun i3TagOnly() {
    Log.i(TOP_LEVEL_TAG, null, null)
}

fun i3TagNull() {
    Log.i(null, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun i3ThrowableOnly() {
    Log.i(null, null, topLevelThrowable)
}

fun i3MessageOnly() {
    Log.i(null, TOP_LEVEL_MESSAGE, null)
}

fun i3Null() {
    Log.i(null, null, null)
}
// endregion

// region: warn
fun wIsLoggable(): Boolean {
    return Log.isLoggable(TOP_LEVEL_TAG, Log.WARN)
}

fun wIsLoggableTagNull(): Boolean {
    return Log.isLoggable(null, Log.WARN)
}

fun w2() {
    Log.w(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE)
}

fun w2TagNull() {
    Log.w(null, TOP_LEVEL_MESSAGE)
}

fun w2Throwable() {
    Log.w(TOP_LEVEL_TAG, topLevelThrowable)
}

fun w2ThrowableTagNull() {
    Log.w(null, topLevelThrowable)
}

fun w2Null() {
    Log.w(null, null)
}

fun w3() {
    Log.w(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun w3MessageNull() {
    Log.w(TOP_LEVEL_TAG, null, topLevelThrowable)
}

fun w3ThrowableNull() {
    Log.w(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, null)
}

fun w3TagOnly() {
    Log.w(TOP_LEVEL_TAG, null, null)
}

fun w3TagNull() {
    Log.w(null, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun w3ThrowableOnly() {
    Log.w(null, null, topLevelThrowable)
}

fun w3MessageOnly() {
    Log.w(null, TOP_LEVEL_MESSAGE, null)
}

fun w3Null() {
    Log.w(null, null, null)
}
// endregion

// region: error
fun eIsLoggable(): Boolean {
    return Log.isLoggable(TOP_LEVEL_TAG, Log.ERROR)
}

fun eIsLoggableTagNull(): Boolean {
    return Log.isLoggable(null, Log.ERROR)
}

fun e2() {
    Log.e(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE)
}

fun e2TagNull() {
    Log.e(null, TOP_LEVEL_MESSAGE)
}

fun e3() {
    Log.e(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun e3MessageNull() {
    Log.e(TOP_LEVEL_TAG, null, topLevelThrowable)
}

fun e3ThrowableNull() {
    Log.e(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, null)
}

fun e3TagOnly() {
    Log.e(TOP_LEVEL_TAG, null, null)
}

fun e3TagNull() {
    Log.e(null, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun e3ThrowableOnly() {
    Log.e(null, null, topLevelThrowable)
}

fun e3MessageOnly() {
    Log.e(null, TOP_LEVEL_MESSAGE, null)
}

fun e3Null() {
    Log.e(null, null, null)
}
// endregion

// region: wtf
fun wtfIsLoggable(): Boolean {
    return Log.isLoggable(TOP_LEVEL_TAG, Log.ASSERT)
}

fun wtfIsLoggableTagNull(): Boolean {
    return Log.isLoggable(null, Log.ASSERT)
}

fun wtf2() {
    Log.wtf(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE)
}

fun wtf2TagNull() {
    Log.wtf(null, TOP_LEVEL_MESSAGE)
}

fun wtf2Throwable() {
    Log.wtf(TOP_LEVEL_TAG, topLevelThrowable)
}

fun wtf2ThrowableTagNull() {
    Log.wtf(null, topLevelThrowable)
}

fun wtf2Null() {
    Log.wtf(null, null)
}

fun wtf3() {
    Log.wtf(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun wtf3MessageNull() {
    Log.wtf(TOP_LEVEL_TAG, null, topLevelThrowable)
}

fun wtf3ThrowableNull() {
    Log.wtf(TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE, null)
}

fun wtf3TagOnly() {
    Log.wtf(TOP_LEVEL_TAG, null, null)
}

fun wtf3TagNull() {
    Log.wtf(null, TOP_LEVEL_MESSAGE, topLevelThrowable)
}

fun wtf3ThrowableOnly() {
    Log.wtf(null, null, topLevelThrowable)
}

fun wtf3MessageOnly() {
    Log.wtf(null, TOP_LEVEL_MESSAGE, null)
}

fun wtf3Null() {
    Log.wtf(null, null, null)
}
// endregion

// region: println
fun println() {
    Log.println(Log.ASSERT, TOP_LEVEL_TAG, TOP_LEVEL_MESSAGE)
}

fun printlnTagNull() {
    Log.println(Log.ASSERT, null, TOP_LEVEL_MESSAGE)
}
// endregion
