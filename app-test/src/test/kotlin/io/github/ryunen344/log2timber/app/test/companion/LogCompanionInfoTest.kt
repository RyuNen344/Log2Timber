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

class LogCompanionInfoTest {

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
    fun test_iIsLoggable() {
        assertThat(LogCompanion.iIsLoggable())
            .isTrue()
    }

    @Test
    fun test_iIsLoggableTagNull() {
        assertThat(LogCompanion.iIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_i2() {
        LogCompanion.i2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.INFO,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_i2TagNull() {
        LogCompanion.i2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.INFO,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_i3() {
        LogCompanion.i3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.INFO,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_i3MessageNull() {
        LogCompanion.i3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.INFO,
                LogCompanion.TAG,
                null,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_i3ThrowableNull() {
        LogCompanion.i3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.INFO,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_i3TagOnly() {
        LogCompanion.i3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_i3TagNull() {
        LogCompanion.i3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.INFO,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_i3ThrowableOnly() {
        LogCompanion.i3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.INFO,
                LogCompanion.TAG,
                null,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_i3MessageOnly() {
        LogCompanion.i3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.INFO,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_i3Null() {
        LogCompanion.i3Null()
        assertThat(tree.stack).isEmpty()
    }
}
