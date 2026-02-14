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

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import io.github.ryunen344.log2timber.DumpWriterService
import org.gradle.api.provider.Property
import org.gradle.api.services.ServiceReference
import org.gradle.api.tasks.Input
import org.objectweb.asm.ClassVisitor

public abstract class Log2TimberVisitorFactory : AsmClassVisitorFactory<Log2TimberVisitorFactory.Parameter> {

    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor,
    ): ClassVisitor {
        val params = parameters.get()
        return Log2TimberClassVisitor(
            instrumentationContext.apiVersion.get(),
            nextClassVisitor,
            params.forcePlant.get(),
            params.service.get().writer,
        )
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        return !(TIMBER_CLASS.contains(classData.className) || classData.superClasses.any { TIMBER_CLASS.contains(it) })
    }

    public interface Parameter : InstrumentationParameters {
        @get:ServiceReference
        public val service: Property<DumpWriterService>

        @get:Input
        public val forcePlant: Property<Boolean>
    }

    private companion object {
        val TIMBER_CLASS: List<String> = listOf(
            $$"timber.log.Timber$Tree",
            "timber.log.Timber",
            $$"timber.log.Timber$Forest",
            $$"timber.log.Timber$DebugTree$Companion",
            $$"timber.log.Timber$DebugTree",
        )
    }
}
