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

package io.github.ryunen344.log2timber.visitor

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.android.build.api.instrumentation.ClassData
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Log2TimberVisitorFactoryTest {

    private lateinit var factory: Log2TimberVisitorFactory

    @BeforeEach
    fun setup() {
        val project = ProjectBuilder.builder().build()
        factory = project.objects.newInstance(Log2TimberVisitorFactory::class.java)
        factory.parameters.set(
            project.objects.newInstance(Log2TimberVisitorFactory.Parameter::class.java).apply {
                enabled.set(true)
                forcePlant.set(true)
            },
        )
    }

    @Test
    fun testIsInstrumentable_givenDisabled_thenFalse() {
        factory.parameters.get().enabled.set(false)
        assertThat(factory.isInstrumentable(FakeClassData("com.example.Foo"))).isFalse()
    }

    @Test
    fun testIsInstrumentable_givenTimberClass_thenFalse() {
        assertThat(factory.isInstrumentable(FakeClassData("timber.log.Timber"))).isFalse()
    }

    @Test
    fun testIsInstrumentable_givenAsmIncompatibleClass_thenFalse() {
        assertThat(factory.isInstrumentable(FakeClassData("androidx.window.sidecar.SidecarDeviceState"))).isFalse()
    }

    @Test
    fun testIsInstrumentable_givenOrdinaryClass_thenTrue() {
        assertThat(factory.isInstrumentable(FakeClassData("com.example.Foo"))).isTrue()
    }

    @Test
    fun testIsInstrumentable_givenTimberSuperClass_thenFalse() {
        val classData = FakeClassData("com.example.MyTree", superClasses = listOf($$"timber.log.Timber$Tree"))
        assertThat(factory.isInstrumentable(classData)).isFalse()
    }

    @Test
    fun testIsInstrumentable_givenUnrelatedSuperClass_thenTrue() {
        val classData = FakeClassData("com.example.Foo", superClasses = listOf("android.app.Activity"))
        assertThat(factory.isInstrumentable(classData)).isTrue()
    }

    private class FakeClassData(
        override val className: String,
        override val superClasses: List<String> = emptyList(),
        override val classAnnotations: List<String> = emptyList(),
        override val interfaces: List<String> = emptyList(),
    ) : ClassData
}
