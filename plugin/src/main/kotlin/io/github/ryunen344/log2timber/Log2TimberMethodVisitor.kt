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

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

public class Log2TimberMethodVisitor(
    api: Int,
    methodVisitor: MethodVisitor,
) : MethodVisitor(api, methodVisitor) {
    override fun visitMethodInsn(
        opcode: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean,
    ) {
        if (owner == "android/util/Log") {
            when (name) {
                "d", "i", "w", "e", "v", "wtf" -> {
                    when (descriptor) {
                        // Log.{d,i,w,e,v,wtf}(tag: String, message: String): Int
                        "(Ljava/lang/String;Ljava/lang/String;)I" -> {
                            // stack: [tag, message] -> [message, tag]
                            super.visitInsn(Opcodes.SWAP)

                            // call Timber.tag(tag)
                            super.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "timber/log/Timber",
                                "tag",
                                $$"(Ljava/lang/String;)Ltimber/log/Timber$Tree;",
                                false,
                            )

                            // stack: [message, Timber$Tree]
                            super.visitInsn(Opcodes.SWAP)

                            // empty Object[] for varargs
                            super.visitInsn(Opcodes.ICONST_0)
                            super.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object")

                            // call Timber$Tree.{ d, i, w, e, v, wtf }
                            super.visitMethodInsn(
                                Opcodes.INVOKEVIRTUAL,
                                $$"timber/log/Timber$Tree",
                                name,
                                "(Ljava/lang/String;[Ljava/lang/Object;)V",
                                false,
                            )

                            // Log.{d,i,w,e,v,wtf} returns int, so push 0 to stack
                            super.visitInsn(Opcodes.ICONST_0)
                        }

                        // Log.{d,i,w,e,v,wtf}(tag: String, throwable: Throwable): Int
                        "(Ljava/lang/String;Ljava/lang/Throwable;)I" -> {
                            // stack: [tag, throwable] -> [throwable, tag]
                            super.visitInsn(Opcodes.SWAP)

                            // call Timber.tag(tag)
                            super.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "timber/log/Timber",
                                "tag",
                                $$"(Ljava/lang/String;)Ltimber/log/Timber$Tree;",
                                false,
                            )

                            // stack: [throwable, Timber$Tree]
                            super.visitInsn(Opcodes.SWAP)

                            // call Timber$Tree.{ d, i, w, e, v, wtf }
                            super.visitMethodInsn(
                                Opcodes.INVOKEVIRTUAL,
                                $$"timber/log/Timber$Tree",
                                name,
                                "(Ljava/lang/Throwable;)V",
                                false,
                            )

                            // Log.{d,i,w,e,v,wtf} returns void, so push 0 to stack
                            super.visitInsn(Opcodes.ICONST_0)
                        }

                        // Log.{d,i,w,e,v,wtf}(tag: String, message: String, throwable: Throwable): Int
                        "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I" -> {
                            // stack: [tag, message, throwable] -> [message, throwable, tag, message, throwable]
                            super.visitInsn(Opcodes.DUP2_X1)
                            // stack: [message, throwable, tag, message, throwable] -> [message, throwable, tag]
                            super.visitInsn(Opcodes.POP2)

                            // call Timber.tag(tag)
                            super.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "timber/log/Timber",
                                "tag",
                                $$"(Ljava/lang/String;)Ltimber/log/Timber$Tree;",
                                false,
                            )

                            // stack: [message, throwable, Timber$Tree] -> [Timber$Tree, message, throwable, Timber$Tree]
                            super.visitInsn(Opcodes.DUP_X2)
                            // stack: [Timber$Tree, message, throwable, Timber$Tree] -> [Timber$Tree, message, throwable]
                            super.visitInsn(Opcodes.POP)
                            // stack: [Timber$Tree, message, throwable] -> [Timber$Tree, throwable, message]
                            super.visitInsn(Opcodes.SWAP)

                            // empty Object[] for varargs
                            super.visitInsn(Opcodes.ICONST_0)
                            super.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object")

                            // call Timber$Tree.{ d, i, w, e, v, wtf }
                            super.visitMethodInsn(
                                Opcodes.INVOKEVIRTUAL,
                                $$"timber/log/Timber$Tree",
                                name,
                                "(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V",
                                false,
                            )

                            // Log.{d,i,w,e,v,wtf} returns int, so push 0 to stack
                            super.visitInsn(Opcodes.ICONST_0)
                        }

                        else -> {
                            println("[Cat2Timber] Unsupported Log method: $name$descriptor")
                            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                        }
                    }
                }

                // Log.isLoggable(tag: String, level: Int): Boolean
                // isLoggable(Ljava/lang/String;I)Z
                "isLoggable" -> {
                    // stack: [tag, level]

                    // pop arguments
                    // stack: [tag, level] -> []
                    super.visitInsn(Opcodes.POP2)

                    // push true
                    super.visitInsn(Opcodes.ICONST_1)
                }

                // Log.println(priority: Int, tag: String, message: String): Int
                // println(ILjava/lang/String;Ljava/lang/String;)I
                "println" -> {
                    // transform nothing
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                }

                else -> {
                    println("[Cat2Timber] Unsupported Log method: $name$descriptor")
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                }
            }
        } else {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
        }
    }
}
