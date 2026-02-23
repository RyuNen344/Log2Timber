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

class LogClassWarnTest {

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
    fun test_wIsLoggable() {
        assertThat(target.wIsLoggable())
            .isTrue()
    }

    @Test
    fun test_wIsLoggableTagNull() {
        assertThat(target.wIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_w2() {
        target.w2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w2TagNull() {
        target.w2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w2Throwable() {
        target.w2Throwable()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_w2ThrowableTagNull() {
        target.w2ThrowableTagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_w2Null() {
        target.w2Null()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_w3() {
        target.w3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogClass.TAG,
                LogClass.MESSAGE,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_w3MessageNull() {
        target.w3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_w3ThrowableNull() {
        target.w3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w3TagOnly() {
        target.w3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_w3TagNull() {
        target.w3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogClass.TAG,
                LogClass.MESSAGE,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_w3ThrowableOnly() {
        target.w3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.WARN,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_w3MessageOnly() {
        target.w3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.WARN,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_w3Null() {
        target.w3Null()
        assertThat(tree.stack).isEmpty()
    }
}
