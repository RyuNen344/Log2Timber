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
import assertk.assertions.contains
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import org.junit.jupiter.api.Test
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.util.CheckClassAdapter
import org.objectweb.asm.util.TraceClassVisitor
import java.io.InputStream
import java.io.PrintWriter
import java.io.StringWriter

class Log2TimberClassVisitorTest {

    @Test
    fun testTransform_givenSupportedLogCalls_thenMatchesFixture() {
        val actual = transform(SAMPLE, forcePlant = false)
        assertMatchesFixture("$VISITOR_RESOURCES/LogSample.txt", actual)
    }

    @Test
    fun testTransform_givenIsLoggableWithForcePlant_thenForcedTrue() {
        val actual = transform(IS_LOGGABLE, forcePlant = true)
        assertMatchesFixture("$VISITOR_RESOURCES/LogIsLoggable-forcePlant.txt", actual)
    }

    @Test
    fun testTransform_givenIsLoggableWithoutForcePlant_thenPassthrough() {
        val actual = transform(IS_LOGGABLE, forcePlant = false)
        assertMatchesFixture("$VISITOR_RESOURCES/LogIsLoggable-passthrough.txt", actual)
    }

    @Test
    fun testDump_givenPassthroughOnly_thenEmpty() {
        val actual = dump(IS_LOGGABLE)
        assertThat(actual).isEmpty()
    }

    @Test
    fun testDump_givenTransformed_thenRecordsOriginalCall() {
        val actual = dump(SAMPLE)
        assertThat(actual).isNotEmpty()
        assertThat(actual).contains("android/util/Log")
    }

    private fun transform(internalName: String, forcePlant: Boolean): String {
        val writer = StringWriter()
        val check = CheckClassAdapter(TraceClassVisitor(null, PrintWriter(writer)), false)
        ClassReader(readClass(internalName)).accept(
            Log2TimberClassVisitor(Opcodes.ASM9, check, forcePlant, null),
            ClassReader.SKIP_FRAMES,
        )
        return writer.toString()
    }

    private fun dump(internalName: String): String {
        val writer = StringWriter()
        ClassReader(readClass(internalName)).accept(
            Log2TimberClassVisitor(Opcodes.ASM9, ClassWriter(0), false, PrintWriter(writer)),
            ClassReader.SKIP_FRAMES,
        )
        return writer.toString()
    }

    private fun readClass(internalName: String): ByteArray {
        return requireNotNull(javaClass.classLoader.getResourceAsStream("$internalName.class"))
            .use(InputStream::readBytes)
    }

    private fun assertMatchesFixture(resourcePath: String, actual: String) {
        val expected = requireNotNull(javaClass.classLoader.getResourceAsStream(resourcePath))
            .use { it.reader().readText() }
        assertThat(actual).isEqualTo(expected)
    }

    private companion object {
        const val SAMPLE = "io/github/ryunen344/log2timber/sample/LogSample"
        const val IS_LOGGABLE = "io/github/ryunen344/log2timber/sample/LogIsLoggable"
        const val VISITOR_RESOURCES = "io/github/ryunen344/log2timber/visitor"
    }
}
