/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.util;

import android.annotation.IntDef;
import android.annotation.NonNull;
import android.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Same ABI for testing.
 */
public final class Log {

    @IntDef({ASSERT, ERROR, WARN, INFO, DEBUG, VERBOSE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Level {
    }

    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;

    private Log() {
    }

    public static int v(@Nullable String tag, @NonNull String msg) {
        return 0;
    }

    public static int v(@Nullable String tag, @Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }

    public static int d(@Nullable String tag, @NonNull String msg) {
        return 0;
    }

    public static int d(@Nullable String tag, @Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }

    public static int i(@Nullable String tag, @NonNull String msg) {
        return 0;
    }

    public static int i(@Nullable String tag, @Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }

    public static int w(@Nullable String tag, @NonNull String msg) {
        return 0;
    }

    public static int w(@Nullable String tag, @Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }

    public static native boolean isLoggable(@Nullable String tag, @Level int level);

    public static int w(@Nullable String tag, @Nullable Throwable tr) {
        return 0;
    }

    public static int e(@Nullable String tag, @NonNull String msg) {
        return 0;
    }

    public static int e(@Nullable String tag, @Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }

    public static int wtf(@Nullable String tag, @Nullable String msg) {
        return 0;
    }

    public static int wtf(@Nullable String tag, @NonNull Throwable tr) {
        return 0;
    }

    public static int wtf(@Nullable String tag, @Nullable String msg, @Nullable Throwable tr) {
        return 0;
    }

    public static int println(@Level int priority, @Nullable String tag, @NonNull String msg) {
        return 0;
    }
}
