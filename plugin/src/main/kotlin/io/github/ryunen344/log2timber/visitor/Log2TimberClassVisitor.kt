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

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.util.Textifier
import java.io.PrintWriter

internal class Log2TimberClassVisitor(
    api: Int,
    cv: ClassVisitor,
    private val forcePlant: Boolean,
    private val writer: PrintWriter?,
) : ClassVisitor(api, cv) {

    private var count: Int = 0

    private val textifier: Textifier by lazy { Textifier() }

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String?>?) {
        count = 0
        textifier.visit(version, access, name, signature, superName, interfaces)
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String?>?,
    ): MethodVisitor {
        val current = count
        return Log2TimberMethodVisitor(
            api,
            cv.visitMethod(access, name, descriptor, signature, exceptions),
            forcePlant,
            { methodOpcode: Int, methodOwner: String?, methodName: String?, methodDescriptor: String?, methodIsInterface: Boolean ->
                if (current == count) {
                    textifier.visitMethod(access, name, descriptor, signature, exceptions)
                }
                textifier.visitMethodInsn(methodOpcode, methodOwner, methodName, methodDescriptor, methodIsInterface)
                count++
            },
            {
                if (current != count) {
                    textifier.visitMethodEnd()
                }
            },
        )
    }

    override fun visitEnd() {
        textifier.visitClassEnd()
        if (count > 0) {
            writer?.let(textifier::print)
            writer?.flush()
        }
        count = 0
        super.visitEnd()
    }
}
