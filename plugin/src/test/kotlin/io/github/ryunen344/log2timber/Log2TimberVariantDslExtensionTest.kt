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
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import io.github.ryunen344.log2timber.assertions.isAbsent
import io.github.ryunen344.log2timber.assertions.isPresent
import io.github.ryunen344.log2timber.assertions.value
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

class Log2TimberVariantDslExtensionTest {

    private lateinit var project: Project

    private lateinit var android: ApplicationExtension

    private lateinit var androidComponents: ApplicationAndroidComponentsExtension

    @BeforeEach
    fun setup() {
        project = ProjectBuilder.builder().build()
        project.plugins.apply("com.android.application")
        project.plugins.apply("log2timber")

        android = project.extensions.getByType(ApplicationExtension::class.java)
        android.compileSdk = 33
        android.namespace = "io.github.ryunen344.log2timber.test"

        androidComponents =
            project.extensions.getByType(ApplicationAndroidComponentsExtension::class.java)
    }

    private fun runTest(block: () -> Unit) {
        block()
        (project as org.gradle.api.internal.project.ProjectInternal).evaluate()
    }

    // region: enabled
    @Test
    fun testEnabled_givenNoExtensionsPresent_thenAbsent() = runTest {
        androidComponents.onVariants(androidComponents.selector().all()) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::enabled)
                .isAbsent()
        }
    }

    @Test
    fun testEnabled_givenBuildTypePresent_thenTakenFromBuildType() = runTest {
        android.buildTypes {
            getByName("debug") { buildType ->
                buildType.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.enabled.set(true)
                }
            }
        }
        androidComponents.onVariants(androidComponents.selector().withBuildType("debug")) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::enabled)
                .isPresent()
                .value()
                .isTrue()
        }
    }

    @Test
    fun testEnabled_givenProductFlavorPresent_thenTakenFromProductFlavor() = runTest {
        android.flavorDimensions += "dimension"
        android.productFlavors {
            create("flavorA") { flavor ->
                flavor.dimension = "dimension"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.enabled.set(true)
                }
            }
        }
        androidComponents.onVariants(androidComponents.selector().withFlavor("dimension", "flavorA")) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::enabled)
                .isPresent()
                .value()
                .isTrue()
        }
    }

    @Test
    fun testEnabled_givenProductFlavorAndBuildTypePresent_thenTakenFromBuildType() = runTest {
        android.buildTypes {
            getByName("debug") { buildType ->
                buildType.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.enabled.set(true)
                }
            }
        }
        android.flavorDimensions += "dimension"
        android.productFlavors {
            create("flavorA") { flavor ->
                flavor.dimension = "dimension"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.enabled.set(false)
                }
            }
        }
        androidComponents.onVariants(
            androidComponents.selector()
                .withFlavor("dimension", "flavorA")
                .withBuildType("debug"),
        ) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::enabled)
                .isPresent()
                .value()
                .isTrue()
        }
    }

    @Test
    fun testEnabled_givenMultipleProductFlavorsEnabledPresent_thenTakenFromLastProductFlavor() = runTest {
        android.flavorDimensions += "dimension1"
        android.flavorDimensions += "dimensionA"
        android.productFlavors {
            create("flavor1") { flavor ->
                flavor.dimension = "dimension1"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.enabled.set(false)
                }
            }
            create("flavorA") { flavor ->
                flavor.dimension = "dimensionA"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.enabled.set(true)
                }
            }
        }
        androidComponents.onVariants(
            androidComponents.selector()
                .withFlavor("dimension1", "flavor1")
                .withFlavor("dimensionA", "flavorA"),
        ) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::enabled)
                .isPresent()
                .value()
                .isFalse()
        }
    }
    // endregion

    // region: forcePlant
    @Test
    fun testForcePlant_givenNoExtensionsPresent_thenAbsent() = runTest {
        androidComponents.onVariants(androidComponents.selector().all()) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::forcePlant)
                .isAbsent()
        }
    }

    @Test
    fun testForcePlant_givenBuildTypePresent_thenTakenFromBuildType() = runTest {
        android.buildTypes {
            getByName("debug") { buildType ->
                buildType.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.forcePlant.set(true)
                }
            }
        }
        androidComponents.onVariants(androidComponents.selector().withBuildType("debug")) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::forcePlant)
                .isPresent()
                .value()
                .isTrue()
        }
    }

    @Test
    fun testForcePlant_givenProductFlavorPresent_thenTakenFromProductFlavor() = runTest {
        android.flavorDimensions += "dimension"
        android.productFlavors {
            create("flavorA") { flavor ->
                flavor.dimension = "dimension"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.forcePlant.set(true)
                }
            }
        }
        androidComponents.onVariants(androidComponents.selector().withFlavor("dimension", "flavorA")) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::forcePlant)
                .isPresent()
                .value()
                .isTrue()
        }
    }

    @Test
    fun testForcePlant_givenProductFlavorAndBuildTypePresent_thenTakenFromBuildType() = runTest {
        android.buildTypes {
            getByName("debug") { buildType ->
                buildType.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.forcePlant.set(true)
                }
            }
        }
        android.flavorDimensions += "dimension"
        android.productFlavors {
            create("flavorA") { flavor ->
                flavor.dimension = "dimension"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.forcePlant.set(false)
                }
            }
        }
        androidComponents.onVariants(
            androidComponents.selector()
                .withFlavor("dimension", "flavorA")
                .withBuildType("debug"),
        ) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::forcePlant)
                .isPresent()
                .value()
                .isTrue()
        }
    }

    @Test
    fun testForcePlant_givenMultipleProductFlavorsEnabledPresent_thenTakenFromLastProductFlavor() = runTest {
        android.flavorDimensions += "dimension1"
        android.flavorDimensions += "dimensionA"
        android.productFlavors {
            create("flavor1") { flavor ->
                flavor.dimension = "dimension1"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.forcePlant.set(false)
                }
            }
            create("flavorA") { flavor ->
                flavor.dimension = "dimensionA"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.forcePlant.set(true)
                }
            }
        }
        androidComponents.onVariants(
            androidComponents.selector()
                .withFlavor("dimension1", "flavor1")
                .withFlavor("dimensionA", "flavorA"),
        ) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::forcePlant)
                .isPresent()
                .value()
                .isFalse()
        }
    }
    // endregion

    // region: dump
    @Test
    fun testDump_givenNoExtensionsPresent_thenAbsent() = runTest {
        androidComponents.onVariants(androidComponents.selector().all()) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::dump)
                .isAbsent()
        }
    }

    @Test
    fun testDump_givenBuildTypePresent_thenTakenFromBuildType(@TempDir tempDir: Path) = runTest {
        val expect = tempDir.resolve("dump.txt").toFile()
        android.buildTypes {
            getByName("debug") { buildType ->
                buildType.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.dump.set(expect)
                }
            }
        }
        androidComponents.onVariants(androidComponents.selector().withBuildType("debug")) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::dump)
                .isPresent()
                .value()
                .prop("absolutePath") { it.asFile.absolutePath }
                .isEqualTo(expect.absolutePath)
        }
    }

    @Test
    fun testDump_givenProductFlavorPresent_thenTakenFromProductFlavor(@TempDir tempDir: Path) = runTest {
        val expect = tempDir.resolve("dump.txt").toFile()
        android.flavorDimensions += "dimension"
        android.productFlavors {
            create("flavorA") { flavor ->
                flavor.dimension = "dimension"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.dump.set(expect)
                }
            }
        }
        androidComponents.onVariants(androidComponents.selector().withFlavor("dimension", "flavorA")) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::dump)
                .isPresent()
                .value()
                .prop("absolutePath") { it.asFile.absolutePath }
                .isEqualTo(expect.absolutePath)
        }
    }

    @Test
    fun testDump_givenProductFlavorAndBuildTypePresent_thenTakenFromBuildType(@TempDir tempDir: Path) = runTest {
        val dumpType = tempDir.resolve("dump-type.txt").toFile()
        val dumpFlavor = tempDir.resolve("dump-flavor.txt").toFile()
        android.buildTypes {
            getByName("debug") { buildType ->
                buildType.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.dump.set(dumpType)
                }
            }
        }
        android.flavorDimensions += "dimension"
        android.productFlavors {
            create("flavorA") { flavor ->
                flavor.dimension = "dimension"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.dump.set(dumpFlavor)
                }
            }
        }
        androidComponents.onVariants(
            androidComponents.selector()
                .withFlavor("dimension", "flavorA")
                .withBuildType("debug"),
        ) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::dump)
                .isPresent()
                .value()
                .prop("absolutePath") { it.asFile.absolutePath }
                .isEqualTo(dumpType.absolutePath)
        }
    }

    @Test
    fun testDump_givenMultipleProductFlavorsEnabledPresent_thenTakenFromLastProductFlavor(@TempDir tempDir: Path) = runTest {
        val dumpDimensionNumber = tempDir.resolve("dump-dimension1.txt").toFile()
        val dumpDimensionAlphabet = tempDir.resolve("dump-dimensionA.txt").toFile()

        android.flavorDimensions += "dimension1"
        android.flavorDimensions += "dimensionA"
        android.productFlavors {
            create("flavor1") { flavor ->
                flavor.dimension = "dimension1"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.dump.set(dumpDimensionNumber)
                }
            }
            create("flavorA") { flavor ->
                flavor.dimension = "dimensionA"
                flavor.extensions.configure(Log2TimberDslExtension::class.java) { extension ->
                    extension.dump.set(dumpDimensionAlphabet)
                }
            }
        }
        androidComponents.onVariants(
            androidComponents.selector()
                .withFlavor("dimension1", "flavor1")
                .withFlavor("dimensionA", "flavorA"),
        ) { variant ->
            val variantExtension = variant.getExtension(Log2TimberVariantDslExtension::class.java)
            assertThat(variantExtension)
                .isNotNull()
                .prop(Log2TimberVariantDslExtension::dump)
                .isPresent()
                .value()
                .prop("absolutePath") { it.asFile.absolutePath }
                .isEqualTo(dumpDimensionNumber.absolutePath)
        }
    }
    // endregion
}
