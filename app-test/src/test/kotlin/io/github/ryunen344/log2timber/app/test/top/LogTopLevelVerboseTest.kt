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

package io.github.ryunen344.log2timber.app.test.top

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

class LogTopLevelVerboseTest {

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
    fun test_vIsLoggable() {
        assertThat(vIsLoggable())
            .isTrue()
    }

    @Test
    fun test_vIsLoggableTagNull() {
        assertThat(vIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_v2() {
        v2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                TOP_LEVEL_TAG,
                TOP_LEVEL_MESSAGE,
            ),
        )
    }

    @Test
    fun test_v2TagNull() {
        v2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                TOP_LEVEL_TAG,
                TOP_LEVEL_MESSAGE,
            ),
        )
    }

    @Test
    fun test_v3() {
        v3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.VERBOSE,
                TOP_LEVEL_TAG,
                TOP_LEVEL_MESSAGE,
                topLevelThrowable,
            ),
        )
    }

    @Test
    fun test_v3MessageNull() {
        v3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.VERBOSE,
                TOP_LEVEL_TAG,
                null,
                topLevelThrowable,
            ),
        )
    }

    @Test
    fun test_v3ThrowableNull() {
        v3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                TOP_LEVEL_TAG,
                TOP_LEVEL_MESSAGE,
            ),
        )
    }

    @Test
    fun test_v3TagOnly() {
        v3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_v3TagNull() {
        v3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.VERBOSE,
                TOP_LEVEL_TAG,
                TOP_LEVEL_MESSAGE,
                topLevelThrowable,
            ),
        )
    }

    @Test
    fun test_v3ThrowableOnly() {
        v3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.VERBOSE,
                TOP_LEVEL_TAG,
                null,
                topLevelThrowable,
            ),
        )
    }

    @Test
    fun test_v3MessageOnly() {
        v3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                TOP_LEVEL_TAG,
                TOP_LEVEL_MESSAGE,
            ),
        )
    }

    @Test
    fun test_v3Null() {
        v3Null()
        assertThat(tree.stack).isEmpty()
    }
}
