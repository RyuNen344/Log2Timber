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

import com.android.builder.signing.DefaultSigningConfig.Companion.DEFAULT_ALIAS
import com.android.builder.signing.DefaultSigningConfig.Companion.DEFAULT_PASSWORD
import io.github.ryunen344.log2timber.dsl.log2timber
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("log2timber").version("+")
}

android {
    namespace = "io.github.ryunen344.log2timber.app.kts"
    compileSdk {
        version = release(36)
    }
    buildToolsVersion = "36.1.0"

    defaultConfig {
        applicationId = "io.github.ryunen344.log2timber.app.kts"
        minSdk {
            version = release(31)
        }
        targetSdk {
            version = release(36)
        }
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    signingConfigs {
        getByName("debug") {
            storeFile = layout.settingsDirectory.dir("..").dir("keystore").file("debug.keystore").asFile
            storePassword = DEFAULT_PASSWORD
            keyAlias = DEFAULT_ALIAS
            keyPassword = DEFAULT_PASSWORD
            enableV3Signing = true
            enableV4Signing = true
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("demo") {
            dimension = "version"
            applicationId = "io.github.ryunen344.log2timber.app.kts.demo"
            log2timber {
                enabled = true
                forcePlant = true
            }
        }
        create("full") {
            dimension = "version"
            applicationId = "io.github.ryunen344.log2timber.app.kts.full"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }

        release {
            log2timber {
                enabled = false
                forcePlant = false
            }
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    lint {
        lintConfig = layout.settingsDirectory.dir("..").dir(".lint").file("lint.xml").asFile
        checkDependencies = true
        checkGeneratedSources = false
        checkReleaseBuilds = false
        sarifReport = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
        unitTests.all { test ->
            test.testLogging.showStandardStreams = true
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility(libs.versions.jdk.get())
        targetCompatibility(libs.versions.jdk.get())
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(libs.versions.jdk.get())
    }
}

dependencies {
    implementation(libs.androidx.activity)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.collection)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.core)
    implementation(libs.androidx.emoji2)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.palette)
    implementation(libs.androidx.startup.runtime)
    implementation(libs.androidx.trace)
    implementation(libs.com.google.material)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation(libs.org.jetbrains.kotlinx.coroutine.android)
    implementation(libs.org.jetbrains.kotlinx.serialization.core)
    implementation(libs.org.jetbrains.kotlinx.serialization.json)
    implementation(libs.org.jetbrains.kotlinx.serialization.json.okio)
    implementation(libs.timber)
}
