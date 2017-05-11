/*
 * Copyright 2017 Noisyfox.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package cn.noisyfox.kotlin.itertools

/**
 * Created by Noisyfox on 2017/5/12.
 */

/**
 * Make an iterator that returns [element] over and over again. Runs [count] times.
 */
fun <T> repeat(element: T, count: Int): Sequence<T> {
    val result = mutableListOf<T>()
    for (i in 1..count) {
        result.add(element)
    }

    return result.asSequence()
}
