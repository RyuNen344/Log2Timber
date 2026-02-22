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

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class Log2TimberMethodVisitor(
    api: Int,
    methodVisitor: MethodVisitor,
    private val forcePlant: Boolean,
    private val onTransform: (
        opcode: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean,
    ) -> Unit,
    private val onVisitEnd: () -> Unit,
) : MethodVisitor(api, methodVisitor) {

    private val logger: Logger by lazy { LoggerFactory.getLogger(javaClass) }

    @Suppress("LongMethod")
    override fun visitMethodInsn(
        opcode: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean,
    ) {
        if (owner == "android/util/Log") {
            onTransform(opcode, owner, name, descriptor, isInterface)
            when (name) {
                "d", "i", "w", "e", "v", "wtf" -> {
                    when (descriptor) {
                        // Log.{v,d,i,w,e,wtf}(@Nullable tag: String, @NonNull message: String): Int
                        "(Ljava/lang/String;Ljava/lang/String;)I" -> {
                            val nullLabel = Label()
                            val endLabel = Label()

                            // stack: [tag, message] -> [message, tag]
                            super.visitInsn(Opcodes.SWAP)

                            // stack: [message, tag] -> [message, tag, tag]
                            super.visitInsn(Opcodes.DUP)

                            // if tag is null, jump to nullLabel
                            // stack: [message, tag, tag] -> [message, tag]
                            super.visitJumpInsn(Opcodes.IFNULL, nullLabel)

                            // if tag is not null, call Timber.tag(tag).{v,d,i,w,e,wtf}(message)
                            super.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "timber/log/Timber",
                                "tag",
                                $$"(Ljava/lang/String;)Ltimber/log/Timber$Tree;",
                                false,
                            )

                            // stack: [message, Timber$Tree]　-> [Timber$Tree, message]
                            super.visitInsn(Opcodes.SWAP)

                            // empty Object[] for varargs
                            // stack: [Timber$Tree, message] -> [Timber$Tree, message, Object[]]
                            super.visitInsn(Opcodes.ICONST_0)
                            super.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object")

                            // call Timber$Tree.{v,d,i,w,e,wtf}
                            super.visitMethodInsn(
                                Opcodes.INVOKEVIRTUAL,
                                $$"timber/log/Timber$Tree",
                                name,
                                "(Ljava/lang/String;[Ljava/lang/Object;)V",
                                false,
                            )

                            // jump to end
                            super.visitJumpInsn(Opcodes.GOTO, endLabel)

                            // nullLabel: call Timber.{v,d,i,w,e,wtf}(message)
                            super.visitLabel(nullLabel)

                            // stack: [message, null] -> [message]
                            super.visitInsn(Opcodes.POP)

                            // empty Object[] for varargs
                            // stack: [message] -> [message, Object[]]
                            super.visitInsn(Opcodes.ICONST_0)
                            super.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object")

                            // call Timber.{v,d,i,w,e,wtf}
                            super.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "timber/log/Timber",
                                name,
                                "(Ljava/lang/String;[Ljava/lang/Object;)V",
                                false,
                            )

                            super.visitLabel(endLabel)

                            // Log.{v,d,i,w,e,wtf} returns int, so push 0 to stack
                            super.visitInsn(Opcodes.ICONST_0)
                        }

                        // Log.{w,wtf}(@Nullable tag: String, @Nullable throwable: Throwable): Int
                        "(Ljava/lang/String;Ljava/lang/Throwable;)I" -> {
                            val nullLabel = Label()
                            val endLabel = Label()

                            // stack: [tag, throwable] -> [throwable, tag]
                            super.visitInsn(Opcodes.SWAP)

                            // stack: [throwable, tag] -> [throwable, tag, tag]
                            super.visitInsn(Opcodes.DUP)

                            // if tag is null, jump to nullLabel
                            // stack: [throwable, tag, tag] -> [throwable, tag]
                            super.visitJumpInsn(Opcodes.IFNULL, nullLabel)

                            // if tag is not null, call Timber.tag(tag).{w,wtf}(throwable)
                            super.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "timber/log/Timber",
                                "tag",
                                $$"(Ljava/lang/String;)Ltimber/log/Timber$Tree;",
                                false,
                            )

                            // stack: [throwable, Timber$Tree] -> [Timber$Tree, throwable]
                            super.visitInsn(Opcodes.SWAP)

                            // call Timber$Tree.{w,wtf}(throwable)
                            super.visitMethodInsn(
                                Opcodes.INVOKEVIRTUAL,
                                $$"timber/log/Timber$Tree",
                                name,
                                "(Ljava/lang/Throwable;)V",
                                false,
                            )

                            // jump to end
                            super.visitJumpInsn(Opcodes.GOTO, endLabel)

                            // nullLabel: call Timber.{w,wtf}(throwable)
                            super.visitLabel(nullLabel)

                            // stack: [throwable, null] -> [throwable]
                            super.visitInsn(Opcodes.POP)

                            // call Timber.{w,wtf}
                            super.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "timber/log/Timber",
                                name,
                                "(Ljava/lang/Throwable;)V",
                                false,
                            )

                            super.visitLabel(endLabel)

                            // Log.{w,wtf} returns int, so push 0 to stack
                            super.visitInsn(Opcodes.ICONST_0)
                        }

                        // Log.{v,d,i,w,e,wtf}(@Nullable tag: String, @Nullable message: String, @Nullable throwable: Throwable): Int
                        "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I" -> {
                            val nullLabel = Label()
                            val endLabel = Label()

                            // stack: [tag, message, throwable] -> [message, throwable, tag, message, throwable]
                            super.visitInsn(Opcodes.DUP2_X1)
                            // stack: [message, throwable, tag, message, throwable] -> [message, throwable, tag]
                            super.visitInsn(Opcodes.POP2)

                            // stack: [message, throwable, tag] -> [message, throwable, tag, tag]
                            super.visitInsn(Opcodes.DUP)

                            // if tag is null, jump to nullLabel
                            // stack: [message, throwable, tag, tag] -> [message, throwable, tag]
                            super.visitJumpInsn(Opcodes.IFNULL, nullLabel)

                            // if tag is not null, call Timber.tag(tag).{v,d,i,w,e,wtf}(throwable, message)
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
                            // stack: [Timber$Tree, throwable, message] -> [Timber$Tree, throwable, message, Object[]]
                            super.visitInsn(Opcodes.ICONST_0)
                            super.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object")

                            // call Timber$Tree.{v,d,i,w,e,wtf}(throwable, message)
                            super.visitMethodInsn(
                                Opcodes.INVOKEVIRTUAL,
                                $$"timber/log/Timber$Tree",
                                name,
                                "(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V",
                                false,
                            )

                            // jump to end
                            super.visitJumpInsn(Opcodes.GOTO, endLabel)

                            // nullLabel: call Timber.{v,d,i,w,e,wtf}(throwable, message)
                            super.visitLabel(nullLabel)

                            // stack: [message, throwable, null] -> [message, throwable]
                            super.visitInsn(Opcodes.POP)

                            // stack: [message, throwable] -> [throwable, message]
                            super.visitInsn(Opcodes.SWAP)

                            // empty Object[] for varargs
                            // stack: [throwable, message] -> [throwable, message, Object[]]
                            super.visitInsn(Opcodes.ICONST_0)
                            super.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object")

                            // call Timber.{v,d,i,w,e,wtf}(throwable, message)
                            super.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "timber/log/Timber",
                                name,
                                "(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V",
                                false,
                            )

                            super.visitLabel(endLabel)

                            // Log.{v,d,i,w,e,wtf} returns int, so push 0 to stack
                            super.visitInsn(Opcodes.ICONST_0)
                        }

                        else -> {
                            logger.warn("Log2Timber: Unsupported descriptor: {}{}", name, descriptor)
                            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                        }
                    }
                }

                // Log.isLoggable(tag: String, level: Int): Boolean
                // isLoggable(Ljava/lang/String;I)Z
                "isLoggable" -> {
                    if (forcePlant) {
                        // stack: [tag, level] -> []
                        super.visitInsn(Opcodes.POP2)

                        // push true
                        super.visitInsn(Opcodes.ICONST_1)
                    } else {
                        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                    }
                }

                // Log.println(priority: Int, @Nullable tag: String, @NonNull message: String): Int
                // println(ILjava/lang/String;Ljava/lang/String;)I
                "println" -> {
                    val nullLabel = Label()
                    val endLabel = Label()

                    // stack: [priority, tag, message] -> [priority, message, tag]
                    super.visitInsn(Opcodes.SWAP)

                    // stack: [priority, message, tag] -> [priority, message, tag, tag]
                    super.visitInsn(Opcodes.DUP)

                    // if tag is null, jump to nullLabel
                    // stack: [priority, message, tag, tag] -> [priority, message, tag]
                    super.visitJumpInsn(Opcodes.IFNULL, nullLabel)

                    // if tag is not null, call Timber.tag(tag).log(priority, message)
                    super.visitMethodInsn(
                        Opcodes.INVOKESTATIC,
                        "timber/log/Timber",
                        "tag",
                        $$"(Ljava/lang/String;)Ltimber/log/Timber$Tree;",
                        false,
                    )

                    // stack: [priority, message, Timber$Tree] -> [Timber$Tree, priority, message, Timber$Tree]
                    super.visitInsn(Opcodes.DUP_X2)
                    // stack: [Timber$Tree, priority, message, Timber$Tree] -> [Timber$Tree, priority, message]
                    super.visitInsn(Opcodes.POP)

                    // empty Object[] for varargs
                    // stack: [Timber$Tree, priority, message] -> [Timber$Tree, priority, message, Object[]]
                    super.visitInsn(Opcodes.ICONST_0)
                    super.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object")

                    // call Timber$Tree.log(priority, message)
                    super.visitMethodInsn(
                        Opcodes.INVOKEVIRTUAL,
                        $$"timber/log/Timber$Tree",
                        "log",
                        "(ILjava/lang/String;[Ljava/lang/Object;)V",
                        false,
                    )

                    // jump to end
                    super.visitJumpInsn(Opcodes.GOTO, endLabel)

                    // nullLabel: call Timber.log(priority, message)
                    super.visitLabel(nullLabel)

                    // stack: [priority, message, null] -> [priority, message]
                    super.visitInsn(Opcodes.POP)

                    // empty Object[] for varargs
                    // stack: [priority, message] -> [priority, message, Object[]]
                    super.visitInsn(Opcodes.ICONST_0)
                    super.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object")

                    // call Timber.log(priority, message)
                    super.visitMethodInsn(
                        Opcodes.INVOKESTATIC,
                        "timber/log/Timber",
                        "log",
                        "(ILjava/lang/String;[Ljava/lang/Object;)V",
                        false,
                    )

                    super.visitLabel(endLabel)

                    // Log.println returns int, so push 0 to stack
                    super.visitInsn(Opcodes.ICONST_0)
                }

                else -> {
                    logger.warn("Log2Timber Unsupported name: {}{}", name, descriptor)
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                }
            }
        } else {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
        }
    }

    override fun visitEnd() {
        onVisitEnd()
        super.visitEnd()
    }
}
