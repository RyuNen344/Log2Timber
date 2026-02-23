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

package io.github.ryunen344.log2timber.app.test.objects

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

class LogObjectWtfTest {

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
        assertThat(LogObject.wtfIsLoggable())
            .isTrue()
    }

    @Test
    fun test_wtfIsLoggableTagNull() {
        assertThat(LogObject.wtfIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_wtf2() {
        LogObject.wtf2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogObject.TAG,
                LogObject.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf2TagNull() {
        LogObject.wtf2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogObject.TAG,
                LogObject.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf2Throwable() {
        LogObject.wtf2Throwable()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogObject.TAG,
                null,
                LogObject.throwable,
            ),
        )
    }

    @Test
    fun test_wtf2ThrowableTagNull() {
        LogObject.wtf2ThrowableTagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogObject.TAG,
                null,
                LogObject.throwable,
            ),
        )
    }

    @Test
    fun test_wtf2Null() {
        LogObject.wtf2Null()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_wtf3() {
        LogObject.wtf3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogObject.TAG,
                LogObject.MESSAGE,
                LogObject.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3MessageNull() {
        LogObject.wtf3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogObject.TAG,
                null,
                LogObject.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3ThrowableNull() {
        LogObject.wtf3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogObject.TAG,
                LogObject.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf3TagOnly() {
        LogObject.wtf3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_wtf3TagNull() {
        LogObject.wtf3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogObject.TAG,
                LogObject.MESSAGE,
                LogObject.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3ThrowableOnly() {
        LogObject.wtf3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogObject.TAG,
                null,
                LogObject.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3MessageOnly() {
        LogObject.wtf3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogObject.TAG,
                LogObject.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf3Null() {
        LogObject.wtf3Null()
        assertThat(tree.stack).isEmpty()
    }
}
