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

import android.util.Log
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEmpty
import assertk.assertions.isTrue
import io.github.ryunen344.log2timber.app.test.timber.TestingTree
import io.github.ryunen344.log2timber.app.test.timber.TimberRecord
import org.junit.After
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class LogCompanionStaticWarnTest {

    lateinit var tree: TestingTree

    @Before
    fun setup() {
        tree = TestingTree()
        Timber.plant(tree)
    }

    @After
    fun cleanup() {
        Timber.uproot(tree)
    }

    @Test
    fun test_wIsLoggable() {
        assertThat(LogCompanionStatic.wIsLoggable())
            .isTrue()
    }

    @Test
    fun test_wIsLoggableTagNull() {
        assertThat(LogCompanionStatic.wIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_w2() {
        LogCompanionStatic.w2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w2TagNull() {
        LogCompanionStatic.w2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w2Throwable() {
        LogCompanionStatic.w2Throwable()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanionStatic.TAG,
                null,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_w2ThrowableTagNull() {
        LogCompanionStatic.w2ThrowableTagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanionStatic.TAG,
                null,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_w2Null() {
        LogCompanionStatic.w2Null()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_w3() {
        LogCompanionStatic.w3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_w3MessageNull() {
        LogCompanionStatic.w3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanionStatic.TAG,
                null,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_w3ThrowableNull() {
        LogCompanionStatic.w3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w3TagOnly() {
        LogCompanionStatic.w3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_w3TagNull() {
        LogCompanionStatic.w3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_w3ThrowableOnly() {
        LogCompanionStatic.w3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanionStatic.TAG,
                null,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_w3MessageOnly() {
        LogCompanionStatic.w3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w3Null() {
        LogCompanionStatic.w3Null()
        assertThat(tree.stack).isEmpty()
    }
}
