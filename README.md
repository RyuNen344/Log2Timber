Log2Timber
====

**Log2Timber** is a Gradle plugin for Android applications that rewrites `android.util.Log` calls
into [Timber](https://github.com/JakeWharton/timber) calls at bytecode level.

It centralizes logging on Timber across the whole APK, including third-party SDKs whose source you
cannot touch, and gives log routing back to the app author.

## Acknowledgements

Huge thanks to [Jake Wharton](https://github.com/JakeWharton)
for [Timber](https://github.com/JakeWharton/timber), the great logging foundation this plugin builds
on.

## Features

### Preserves Tag and Throwable

Every `Log` overload is mapped to its `Timber` counterpart, keeping the original arguments:

| `android.util.Log`                               | `timber.log.Timber`                   |
|--------------------------------------------------|---------------------------------------|
| `v` / `d` / `i` / `w` / `e` / `wtf`(tag, msg)    | `Timber.tag(tag).*(msg)`              |
| `w` / `wtf`(tag, throwable)                      | `Timber.tag(tag).*(throwable)`        |
| `v` / `d` / `i` / `w` / `e` / `wtf`(tag, msg, t) | `Timber.tag(tag).*(t, msg)`           |
| `println(priority, tag, msg)`                    | `Timber.tag(tag).log(priority, msg)`  |
| `isLoggable(tag, level)`                         | `true` (when `forcePlant` is enabled) |

A null tag falls back to the untagged `Timber` call, so behaviour stays identical to the original
code.

### Configurable per Build Type and Product Flavor

Instrumentation can be switched on or off per project, per build type, and per product flavor.
Keep it enabled for debug builds and drop it entirely from release builds without touching a single
line of source code.

## Requirements

- Android Gradle Plugin `8.5.2` or newer
- `com.android.application` module (library modules are skipped with a warning)
- `com.jakewharton.timber:timber` on the runtime classpath

The plugin registers a `verify<Variant>TimberDependency` pre-build task that warns when Timber is
missing from the variant runtime classpath.

## Installation

### Gradle Kotlin DSL

```kotlin
plugins {
    id("com.android.application")
    id("io.github.ryunen344.log2timber") version "$version"
}
```

### Gradle Groovy DSL

```gradle
plugins {
    id 'com.android.application'
    id 'io.github.ryunen344.log2timber' version '$version'
}
```

## How to use

### Step 1: Plant a Tree

```kotlin
class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}
```

### Step 2: Configure the Plugin

Defaults are `enabled = true` and `forcePlant = true`, so applying the plugin is already enough.
Override them when a variant needs different behaviour.

```kotlin
import io.github.ryunen344.log2timber.dsl.log2timber

android {
    log2timber {
        enabled = true
        forcePlant = true
    }

    buildTypes {
        release {
            log2timber {
                enabled = false
                forcePlant = false
            }
        }
    }

    productFlavors {
        create("demo") {
            dimension = "version"
            log2timber {
                dump = layout.buildDirectory.file("log2timber/demo-output.txt")
            }
        }
    }
}
```

| Option       | Type                  | Default | Description                                                                                           |
|--------------|-----------------------|---------|-------------------------------------------------------------------------------------------------------|
| `enabled`    | `Property<Boolean>`   | `true`  | Enables the bytecode transformation for the variant.                                                  |
| `forcePlant` | `Property<Boolean>`   | `true`  | Replaces `Log.isLoggable` with `true` so that logs guarded by it always reach your `Timber.Tree`.     |
| `dump`       | `RegularFileProperty` | -       | Writes the transformed methods as readable bytecode to the given file. Useful for verifying coverage. |

Values set on a build type or product flavor take precedence over the project level configuration.

### Step 3: Build and Verify

```console
$ ./gradlew assembleDebug
```

With `dump` configured, the transformed call sites are written to the specified file:

```text
// class version 52.0 (52)
class io/github/ryunen344/log2timber/app/ExampleActivity {
  onCreate(Landroid/os/Bundle;)V
   INVOKESTATIC android/util/Log.d (Ljava/lang/String;Ljava/lang/String;)I
}
```

> [!TIP]
> `dump` is written per variant, so pointing each build type at its own file makes it easy to diff
> what changed between them.

> [!NOTE]
> The dump only contains what the current build actually instrumented.
> With incremental builds and the build cache, up-to-date classes are not transformed again, so
> their call sites will be missing from the file.
> Run a clean build when you need the full list.

## License
```text
Copyright (C) 2026 RyuNen344

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
```
