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

class LogClassVerboseTest {

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
    fun test_vIsLoggable() {
        assertThat(target.vIsLoggable())
            .isTrue()
    }

    @Test
    fun test_vIsLoggableTagNull() {
        assertThat(target.vIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_v2() {
        target.v2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_v2TagNull() {
        target.v2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_v3() {
        target.v3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.VERBOSE,
                LogClass.TAG,
                LogClass.MESSAGE,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_v3MessageNull() {
        target.v3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.VERBOSE,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_v3ThrowableNull() {
        target.v3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_v3TagOnly() {
        target.v3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_v3TagNull() {
        target.v3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.VERBOSE,
                LogClass.TAG,
                LogClass.MESSAGE,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_v3ThrowableOnly() {
        target.v3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.VERBOSE,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_v3MessageOnly() {
        target.v3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_v3Null() {
        target.v3Null()
        assertThat(tree.stack).isEmpty()
    }
}
