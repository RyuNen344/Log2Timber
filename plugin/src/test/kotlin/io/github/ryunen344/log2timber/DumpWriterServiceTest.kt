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
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.services.BuildServiceSpec
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

class DumpWriterServiceTest {

    private lateinit var project: Project

    @BeforeEach
    fun setup() {
        project = ProjectBuilder.builder().build()
    }

    private fun service(configure: Action<BuildServiceSpec<DumpWriterService.Params>> = Action {}): DumpWriterService =
        project.gradle.sharedServices
            .registerIfAbsent("dump", DumpWriterService::class.java, configure)
            .get()

    @Test
    fun testWriter_givenDumpAbsent_thenNull() {
        assertThat(service().writer).isNull()
    }

    @Test
    fun testWriter_givenDumpPresent_thenCreatesFileAndWrites(@TempDir tempDir: Path) {
        val dump = tempDir.resolve("nested").resolve("dump.txt").toFile()
        val service = service { it.parameters.dump.set(dump) }

        val writer = service.writer
        assertThat(writer).isNotNull()
        assertThat(dump.exists()).isTrue()

        writer!!.println("hello")
        service.close()

        assertThat(dump.readText().trim()).isEqualTo("hello")
    }

    @Test
    fun testWriter_givenExistingFile_thenTruncated(@TempDir tempDir: Path) {
        val dump = tempDir.resolve("dump.txt").toFile().apply { writeText("stale content") }
        val service = service { it.parameters.dump.set(dump) }

        service.writer
        service.close()

        assertThat(dump.readText()).isEqualTo("")
    }

    @Test
    fun testClose_givenDumpAbsent_thenNoop() {
        service().close()
    }
}
