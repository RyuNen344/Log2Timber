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
import io.github.ryunen344.log2timber.app.test.timber.TestingTree
import io.github.ryunen344.log2timber.app.test.timber.TimberRecord
import org.junit.After
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class EdgesTest {

    lateinit var tree: TestingTree
    lateinit var target: Edges

    @Before
    fun setup() {
        tree = TestingTree()
        Timber.plant(tree)
        target = Edges()
    }

    @After
    fun cleanup() {
        Timber.uproot(tree)
    }

    @Test
    fun test_anonymous() {
        target.anonymous()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                "Edges",
                "in runnable",
            ),
        )
    }

    @Test
    fun test_local() {
        target.local()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                $$"Edges$local$Local",
                "in local",
            ),
        )
    }

    @Test
    fun test_lambda() {
        target.lambda()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                "Edges",
                "in lambda",
            ),
        )
    }

    @Test
    fun test_initializer() {
        target.initializer()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                $$"Edges$initializer$Initi",
                "in initializer",
            ),
        )
    }

    @Test
    fun test_abstract() {
        target.abstract()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                $$"Edges$abstract$Abstract",
                "in abstract",
            ),
        )
    }

    @Test
    fun test_default() {
        target.default()
        assertThat(tree.stack).containsOnly(
            TimberRecord(
                Log.VERBOSE,
                $$"Edges$Default",
                "in default",
            ),
        )
    }
}
