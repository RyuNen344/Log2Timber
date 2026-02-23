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

class LogCompanionDebugTest {

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
    fun test_dIsLoggable() {
        assertThat(LogCompanion.dIsLoggable())
            .isTrue()
    }

    @Test
    fun test_dIsLoggableTagNull() {
        assertThat(LogCompanion.dIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_d2() {
        LogCompanion.d2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.DEBUG,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_d2TagNull() {
        LogCompanion.d2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.DEBUG,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_d3() {
        LogCompanion.d3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.DEBUG,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_d3MessageNull() {
        LogCompanion.d3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.DEBUG,
                LogCompanion.TAG,
                null,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_d3ThrowableNull() {
        LogCompanion.d3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.DEBUG,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_d3TagOnly() {
        LogCompanion.d3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_d3TagNull() {
        LogCompanion.d3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.DEBUG,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_d3ThrowableOnly() {
        LogCompanion.d3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.DEBUG,
                LogCompanion.TAG,
                null,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_d3MessageOnly() {
        LogCompanion.d3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.DEBUG,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_d3Null() {
        LogCompanion.d3Null()
        assertThat(tree.stack).isEmpty()
    }
}
