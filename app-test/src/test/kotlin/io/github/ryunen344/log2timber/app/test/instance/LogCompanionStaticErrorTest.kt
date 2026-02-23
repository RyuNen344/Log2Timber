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

class LogCompanionStaticErrorTest {

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
        assertThat(LogCompanionStatic.eIsLoggable())
            .isTrue()
    }

    @Test
    fun test_eIsLoggableTagNull() {
        assertThat(LogCompanionStatic.eIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_e2() {
        LogCompanionStatic.e2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e2TagNull() {
        LogCompanionStatic.e2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e3() {
        LogCompanionStatic.e3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_e3MessageNull() {
        LogCompanionStatic.e3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogCompanionStatic.TAG,
                null,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_e3ThrowableNull() {
        LogCompanionStatic.e3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e3TagOnly() {
        LogCompanionStatic.e3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_e3TagNull() {
        LogCompanionStatic.e3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_e3ThrowableOnly() {
        LogCompanionStatic.e3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogCompanionStatic.TAG,
                null,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_e3MessageOnly() {
        LogCompanionStatic.e3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e3Null() {
        LogCompanionStatic.e3Null()
        assertThat(tree.stack).isEmpty()
    }
}
