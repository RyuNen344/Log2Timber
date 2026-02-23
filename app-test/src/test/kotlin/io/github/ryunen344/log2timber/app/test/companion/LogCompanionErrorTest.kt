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

class LogCompanionErrorTest {

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
    fun test_eIsLoggable() {
        assertThat(LogCompanion.eIsLoggable())
            .isTrue()
    }

    @Test
    fun test_eIsLoggableTagNull() {
        assertThat(LogCompanion.eIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_e2() {
        LogCompanion.e2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e2TagNull() {
        LogCompanion.e2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e3() {
        LogCompanion.e3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_e3MessageNull() {
        LogCompanion.e3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogCompanion.TAG,
                null,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_e3ThrowableNull() {
        LogCompanion.e3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e3TagOnly() {
        LogCompanion.e3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_e3TagNull() {
        LogCompanion.e3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_e3ThrowableOnly() {
        LogCompanion.e3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogCompanion.TAG,
                null,
                LogCompanion.throwable,
            ),
        )
    }

    @Test
    fun test_e3MessageOnly() {
        LogCompanion.e3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogCompanion.TAG,
                LogCompanion.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e3Null() {
        LogCompanion.e3Null()
        assertThat(tree.stack).isEmpty()
    }
}
