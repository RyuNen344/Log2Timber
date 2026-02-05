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

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFiles
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream
import java.util.zip.ZipFile
import javax.inject.Inject
import kotlin.io.buffered
import kotlin.io.copyTo
import kotlin.io.outputStream
import kotlin.use

/**
 * A Gradle task that dumps Log2Timber data from an input file to an output file.
 *
 * Incremental builds are not save during asm instrumentation.
 * Therefore, this task always copies the input file to the output file when executed.
 */
public abstract class DumpLog2TimberTask @Inject constructor() : DefaultTask() {

    @get:InputFiles
    public abstract val inputJars: ListProperty<RegularFile>

    @get:InputFiles
    public abstract val inputDirectories: ListProperty<Directory>

    @get:OutputFiles
    public abstract val into: RegularFileProperty

    @get:OutputFiles
    public abstract val output: RegularFileProperty

    @get:InputFiles
    public abstract val input: RegularFileProperty

    @Suppress("CyclomaticComplexMethod", "NestedBlockDepth")
    @TaskAction
    public fun action() {
        // Create merged jar file
        into.get().asFile.outputStream().buffered().let(::JarOutputStream).use { jos ->
            val computed: HashSet<String> = HashSet()
            inputJars.get()
                .map { it.asFile }
                .filter { it.exists() && it.isFile }
                .sortedBy { it.absolutePath }
                .forEach { jar ->
                    ZipFile(jar).use { zip ->
                        zip.entries().asSequence()
                            .filter { !it.isDirectory }
                            .sortedBy { it.name }
                            .forEach { entry ->
                                if (!computed.contains(entry.name)) {
                                    jos.putNextEntry(
                                        entry.apply {
                                            // Reset timestamps to ensure reproducible builds
                                            time = 0L
                                        },
                                    )
                                    zip.getInputStream(entry).use { input ->
                                        input.copyTo(jos)
                                    }
                                    computed.add(entry.name)
                                }
                            }
                    }
                }

            inputDirectories.get().forEach { dir ->
                val root = dir.asFile.toPath()
                Files.walk(root).use { paths ->
                    paths
                        .filter { Files.isRegularFile(it) }
                        .forEach { path ->
                            val name = root
                                .relativize(path)
                                .toString()
                                .replace(File.separatorChar, '/')
                            val entry = JarEntry(name).apply { size = Files.size(path) }
                            if (!computed.contains(entry.name)) {
                                jos.putNextEntry(
                                    entry.apply {
                                        // Reset timestamps to ensure reproducible builds
                                        time = 0L
                                    },
                                )
                                Files.newInputStream(path).use { input ->
                                    input.copyTo(jos)
                                }
                                computed.add(entry.name)
                            }
                        }
                }
            }
        }

        // Copy ASM dump file
        val inputFile = input.takeIf(RegularFileProperty::isPresent)?.get()?.asFile
        if (inputFile == null || !inputFile.exists()) {
            logger.info("Input file is not set or does not exist. Skipping dump.")
        } else {
            val outputFile = output.asFile.get()
            outputFile.parentFile?.mkdirs()
            inputFile.copyTo(outputFile, overwrite = true)
            logger.lifecycle("Dumped Log2Timber data to: ${outputFile.absolutePath}")
        }
    }
}
