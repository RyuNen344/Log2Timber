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

class LogClassDebugTest {

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
    fun test_dIsLoggable() {
        assertThat(target.dIsLoggable())
            .isTrue()
    }

    @Test
    fun test_dIsLoggableTagNull() {
        assertThat(target.dIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_d2() {
        target.d2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.DEBUG,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_d2TagNull() {
        target.d2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.DEBUG,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_d3() {
        target.d3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.DEBUG,
                LogClass.TAG,
                LogClass.MESSAGE,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_d3MessageNull() {
        target.d3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.DEBUG,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_d3ThrowableNull() {
        target.d3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.DEBUG,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_d3TagOnly() {
        target.d3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_d3TagNull() {
        target.d3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.DEBUG,
                LogClass.TAG,
                LogClass.MESSAGE,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_d3ThrowableOnly() {
        target.d3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.DEBUG,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_d3MessageOnly() {
        target.d3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.DEBUG,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_d3Null() {
        target.d3Null()
        assertThat(tree.stack).isEmpty()
    }
}
