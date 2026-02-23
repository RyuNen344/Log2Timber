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

class LogObjectInfoTest {

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
    fun test_iIsLoggable() {
        assertThat(LogObject.iIsLoggable())
            .isTrue()
    }

    @Test
    fun test_iIsLoggableTagNull() {
        assertThat(LogObject.iIsLoggableTagNull())
            .isTrue()
    }

    @Test
    fun test_i2() {
        LogObject.i2()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.INFO,
                LogObject.TAG,
                LogObject.MESSAGE,
            ),
        )
    }

    @Test
    fun test_i2TagNull() {
        LogObject.i2TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.INFO,
                LogObject.TAG,
                LogObject.MESSAGE,
            ),
        )
    }

    @Test
    fun test_i3() {
        LogObject.i3()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.INFO,
                LogObject.TAG,
                LogObject.MESSAGE,
                LogObject.throwable,
            ),
        )
    }

    @Test
    fun test_i3MessageNull() {
        LogObject.i3MessageNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.INFO,
                LogObject.TAG,
                null,
                LogObject.throwable,
            ),
        )
    }

    @Test
    fun test_i3ThrowableNull() {
        LogObject.i3ThrowableNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.INFO,
                LogObject.TAG,
                LogObject.MESSAGE,
            ),
        )
    }

    @Test
    fun test_i3TagOnly() {
        LogObject.i3TagOnly()
        assertThat(tree.stack).isEmpty()
    }

    @Test
    fun test_i3TagNull() {
        LogObject.i3TagNull()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.INFO,
                LogObject.TAG,
                LogObject.MESSAGE,
                LogObject.throwable,
            ),
        )
    }

    @Test
    fun test_i3ThrowableOnly() {
        LogObject.i3ThrowableOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord.exception(
                Log.INFO,
                LogObject.TAG,
                null,
                LogObject.throwable,
            ),
        )
    }

    @Test
    fun test_i3MessageOnly() {
        LogObject.i3MessageOnly()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.INFO,
                LogObject.TAG,
                LogObject.MESSAGE,
            ),
        )
    }

    @Test
    fun test_i3Null() {
        LogObject.i3Null()
        assertThat(tree.stack).isEmpty()
    }
}
