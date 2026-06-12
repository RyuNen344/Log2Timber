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

import org.gradle.api.file.RegularFileProperty
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import java.io.PrintWriter
import java.lang.AutoCloseable
import javax.inject.Inject

public abstract class DumpWriterService @Inject constructor() : BuildService<DumpWriterService.Params>, AutoCloseable {

    public val writer: PrintWriter? by lazy {
        val file = parameters.dump.orNull?.asFile
        file?.parentFile?.mkdirs()
        file?.delete()
        file?.createNewFile()
        file?.let(::PrintWriter)
    }

    override fun close() {
        writer?.close()
    }

    public interface Params : BuildServiceParameters {
        @get:OutputFile
        @get:Optional
        public val dump: RegularFileProperty
    }
}
