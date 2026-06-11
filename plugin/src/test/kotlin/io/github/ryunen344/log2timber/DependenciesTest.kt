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

package io.github.ryunen344.log2timber

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.gradle.api.artifacts.ModuleIdentifier
import org.gradle.api.artifacts.component.ComponentIdentifier
import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.api.artifacts.result.ResolutionResult
import org.gradle.api.artifacts.result.ResolvedComponentResult
import org.junit.jupiter.api.Test
import java.lang.reflect.Proxy

class DependenciesTest {

    @Test
    fun testIsTimber_givenTimberModule_thenTrue() {
        val id = FakeModuleComponentIdentifier(
            group = Log2TimberPlugin.TIMBER_GROUP,
            module = Log2TimberPlugin.TIMBER_MODULE,
        )
        assertThat(id.isTimber()).isTrue()
    }

    @Test
    fun testIsTimber_givenDifferentGroup_thenFalse() {
        val id = FakeModuleComponentIdentifier(
            group = "com.example",
            module = Log2TimberPlugin.TIMBER_MODULE,
        )
        assertThat(id.isTimber()).isFalse()
    }

    @Test
    fun testIsTimber_givenDifferentModule_thenFalse() {
        val id = FakeModuleComponentIdentifier(
            group = Log2TimberPlugin.TIMBER_GROUP,
            module = "not-timber",
        )
        assertThat(id.isTimber()).isFalse()
    }

    @Test
    fun testIsTimber_givenNonModuleIdentifier_thenFalse() {
        assertThat(FakeComponentIdentifier.isTimber()).isFalse()
    }

    @Test
    fun testIsTimberMissing_givenTimberPresent_thenFalse() {
        val result = resolutionResult(
            FakeModuleComponentIdentifier("androidx.core", "core"),
            FakeModuleComponentIdentifier(Log2TimberPlugin.TIMBER_GROUP, Log2TimberPlugin.TIMBER_MODULE),
        )
        assertThat(result.isTimberMissing()).isFalse()
    }

    @Test
    fun testIsTimberMissing_givenTimberAbsent_thenTrue() {
        val result = resolutionResult(
            FakeModuleComponentIdentifier("androidx.core", "core"),
            FakeComponentIdentifier,
        )
        assertThat(result.isTimberMissing()).isTrue()
    }

    @Test
    fun testIsTimberMissing_givenNoComponents_thenTrue() {
        assertThat(resolutionResult().isTimberMissing()).isTrue()
    }

    private class FakeModuleComponentIdentifier(
        private val group: String,
        private val module: String,
    ) : ModuleComponentIdentifier {
        override fun getGroup(): String = group
        override fun getModule(): String = module
        override fun getVersion(): String = "1.0.0"
        override fun getModuleIdentifier(): ModuleIdentifier = throw UnsupportedOperationException()
        override fun getDisplayName(): String = "$group:$module"
    }

    private object FakeComponentIdentifier : ComponentIdentifier {
        override fun getDisplayName(): String = "fake"
    }

    private companion object {
        fun resolutionResult(vararg ids: ComponentIdentifier): ResolutionResult {
            val components = ids.mapTo(LinkedHashSet(), ::fakeComponent)
            return ResolutionResult::class.java.proxy { method, _ ->
                when (method.name) {
                    "getAllComponents" -> components
                    "toString" -> "FakeResolutionResult"
                    else -> throw UnsupportedOperationException(method.name)
                }
            }
        }

        fun fakeComponent(id: ComponentIdentifier): ResolvedComponentResult =
            ResolvedComponentResult::class.java.proxy { method, args ->
                when (method.name) {
                    "getId" -> id
                    "hashCode" -> System.identityHashCode(id)
                    "equals" -> id === (args?.getOrNull(0) as? ResolvedComponentResult)?.id
                    "toString" -> "FakeResolvedComponentResult($id)"
                    else -> throw UnsupportedOperationException(method.name)
                }
            }

        @Suppress("UNCHECKED_CAST")
        fun <T> Class<T>.proxy(handler: (java.lang.reflect.Method, Array<Any?>?) -> Any?): T =
            Proxy.newProxyInstance(classLoader, arrayOf(this)) { _, method, args -> handler(method, args) } as T
    }
}
