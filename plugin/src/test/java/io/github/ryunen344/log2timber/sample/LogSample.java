/*
 * Copyright (C) 2026 RyuNen344
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
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

package io.github.ryunen344.log2timber.sample;

import android.util.Log;

public final class LogSample {

    private LogSample() {
    }

    public static void message(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void messageThrowable(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
    }

    public static void throwableWarn(String tag, Throwable tr) {
        Log.w(tag, tr);
    }

    public static void throwableWtf(String tag, Throwable tr) {
        Log.wtf(tag, tr);
    }

    public static void print(int priority, String tag, String msg) {
        Log.println(priority, tag, msg);
    }
}
