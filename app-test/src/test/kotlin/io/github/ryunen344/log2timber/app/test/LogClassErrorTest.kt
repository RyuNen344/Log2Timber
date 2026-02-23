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
import io.github.ryunen344.log2timber.app.test.timber.TestingTree
import io.github.ryunen344.log2timber.app.test.timber.TimberRecord
import org.junit.After
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class LogClassErrorTest {

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
    fun test_e2() {
        target.e2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e2TagNull() {
        target.e2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e3() {
        target.e3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogClass.TAG,
                LogClass.MESSAGE,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_e3MessageNull() {
        target.e3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_e3ThrowableNull() {
        target.e3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e3TagOnly() {
        target.e3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_e3TagNull() {
        target.e3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogClass.TAG,
                LogClass.MESSAGE,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_e3ThrowableOnly() {
        target.e3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.ERROR,
                LogClass.TAG,
                null,
                LogClass.throwable,
            ),
        )
    }

    @Test
    fun test_e3MessageOnly() {
        target.e3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.ERROR,
                LogClass.TAG,
                LogClass.MESSAGE,
            ),
        )
    }

    @Test
    fun test_e3Null() {
        target.e3Null()
        assertThat(tree.stack).isEmpty()
    }
}
