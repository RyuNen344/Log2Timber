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

class LogCompanionStaticWtfTest {

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
    fun test_wtfIsLoggable() {
        assertThat(LogCompanionStatic.wtfIsLoggable())
            .isTrue()
    }

    @Test
    fun test_wtfIsLoggableTagNull() {
        assertThat(LogCompanionStatic.wtfIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_wtf2() {
        LogCompanionStatic.wtf2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf2TagNull() {
        LogCompanionStatic.wtf2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf2Throwable() {
        LogCompanionStatic.wtf2Throwable()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogCompanionStatic.TAG,
                null,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_wtf2ThrowableTagNull() {
        LogCompanionStatic.wtf2ThrowableTagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogCompanionStatic.TAG,
                null,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_wtf2Null() {
        LogCompanionStatic.wtf2Null()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_wtf3() {
        LogCompanionStatic.wtf3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3MessageNull() {
        LogCompanionStatic.wtf3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogCompanionStatic.TAG,
                null,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3ThrowableNull() {
        LogCompanionStatic.wtf3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf3TagOnly() {
        LogCompanionStatic.wtf3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_wtf3TagNull() {
        LogCompanionStatic.wtf3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3ThrowableOnly() {
        LogCompanionStatic.wtf3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogCompanionStatic.TAG,
                null,
                LogCompanionStatic.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3MessageOnly() {
        LogCompanionStatic.wtf3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogCompanionStatic.TAG,
                LogCompanionStatic.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf3Null() {
        LogCompanionStatic.wtf3Null()
        assertThat(tree.stack).isEmpty()
    }
}
