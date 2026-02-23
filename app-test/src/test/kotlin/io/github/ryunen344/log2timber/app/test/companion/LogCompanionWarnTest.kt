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

package io.github.ryunen344.log2timber.app.test.companion

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

class LogCompanionWarnTest {

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
        assertThat(LogCompanion.wIsLoggable())
            .isTrue()
    }

    @Test
    fun test_wIsLoggableTagNull() {
        assertThat(LogCompanion.wIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_w2() {
        LogCompanion.w2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w2TagNull() {
        LogCompanion.w2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w2Throwable() {
        LogCompanion.w2Throwable()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanion.TAG,
                null,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_w2ThrowableTagNull() {
        LogCompanion.w2ThrowableTagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanion.TAG,
                null,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_w2Null() {
        LogCompanion.w2Null()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_w3() {
        LogCompanion.w3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_w3MessageNull() {
        LogCompanion.w3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanion.TAG,
                null,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_w3ThrowableNull() {
        LogCompanion.w3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w3TagOnly() {
        LogCompanion.w3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_w3TagNull() {
        LogCompanion.w3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_w3ThrowableOnly() {
        LogCompanion.w3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogCompanion.TAG,
                null,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_w3MessageOnly() {
        LogCompanion.w3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w3Null() {
        LogCompanion.w3Null()
        assertThat(tree.stack).isEmpty()
    }
}
