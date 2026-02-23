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

package io.github.ryunen344.log2timber.app.test

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

class LogClassWtfTest {

    lateinit var tree: TestingTree
    lateinit var target: LogClass

    @Before
    fun setup() {
        tree = TestingTree()
        Timber.plant(tree)
        target = LogClass()
    }

    @After
    fun cleanup() {
        Timber.uproot(tree)
    }

    @Test
    fun test_wtfIsLoggable() {
        assertThat(target.wtfIsLoggable())
            .isTrue()
    }

    @Test
    fun test_wtfIsLoggableTagNull() {
        assertThat(target.wtfIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_wtf2() {
        target.wtf2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf2TagNull() {
        target.wtf2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf2Throwable() {
        target.wtf2Throwable()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_wtf2ThrowableTagNull() {
        target.wtf2ThrowableTagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_wtf2Null() {
        target.wtf2Null()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_wtf3() {
        target.wtf3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogClass.TAG,
                LogClass.MESSAGE,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3MessageNull() {
        target.wtf3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3ThrowableNull() {
        target.wtf3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf3TagOnly() {
        target.wtf3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_wtf3TagNull() {
        target.wtf3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogClass.TAG,
                LogClass.MESSAGE,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3ThrowableOnly() {
        target.wtf3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ASSERT,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_wtf3MessageOnly() {
        target.wtf3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ASSERT,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_wtf3Null() {
        target.wtf3Null()
        assertThat(tree.stack).isEmpty()
    }
}
