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
 * Cartesian product of input iterables.
 *
 * The nested loops cycle like an odometer with the rightmost element advancing on every iteration.
 * This pattern creates a lexicographic ordering so that if the input’s iterables are sorted,
 * the product tuples are emitted in sorted order.
 */
fun <T1, T2> Iterable<T1>.product(other: Iterable<T2>): Iterable<Pair<T1, T2>> {
    val result = mutableListOf<Pair<T1, T2>>()

    for (left in this) {
        other.mapTo(result) { Pair(left, it) }
    }

    return result
}