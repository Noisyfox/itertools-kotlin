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

package cn.noisyfox.kotlin.itertools.experimental

import kotlin.coroutines.experimental.buildIterator

/**
 * Created by Noisyfox on 2017/5/12.
 */

/**
 * Cartesian product of input iterables.
 *
 * The nested loops cycle like an odometer with the rightmost element advancing on every iteration.
 * This pattern creates a lexicographic ordering so that if the input’s iterables are sorted,
 * the product tuples are emitted in sorted order.
 *
 * This one use yield.
 */
fun <T1, T2> Iterable<T1>.product(other: Iterable<T2>): Iterable<Pair<T1, T2>> {
    return object : Iterable<Pair<T1, T2>> {
        override fun iterator(): Iterator<Pair<T1, T2>> = buildIterator {
            for (left in this@product) {
                for (right in other) {
                    yield(Pair(left, right))
                }
            }
        }
    }
}

/**
 * Cartesian product of input iterables.
 *
 * The nested loops cycle like an odometer with the rightmost element advancing on every iteration.
 * This pattern creates a lexicographic ordering so that if the input’s iterables are sorted,
 * the product tuples are emitted in sorted order.
 *
 * This one use yield.
 */
fun <T> Iterable<T>.product(): Iterable<Pair<T, T>> = this.product(this)

/**
 * Return successive 2 length permutations of elements in the iterable.
 *
 * This one use yield.
 */
fun <T> Iterable<T>.permutations(): Iterable<Pair<T, T>> {
    return object : Iterable<Pair<T, T>> {
        override fun iterator(): Iterator<Pair<T, T>> = buildIterator {
            for ((i1, v1) in this@permutations.withIndex()) {
                for ((i2, v2) in this@permutations.withIndex()) {
                    if (i1 != i2) {
                        yield(Pair(v1, v2))
                    }
                }
            }
        }
    }
}

/**
 * Return 2 length subsequences of elements from the input iterable.
 *
 * Combinations are emitted in lexicographic sort order. So, if the input iterable is sorted,
 * the combination tuples will be produced in sorted order.
 *
 * Elements are treated as unique based on their position, not on their value.
 * So if the input elements are unique, there will be no repeat values in each combination.
 *
 * This one use yield.
 */
fun <T> Iterable<T>.combinations(): Iterable<Pair<T, T>> {
    return object : Iterable<Pair<T, T>> {
        override fun iterator(): Iterator<Pair<T, T>> = buildIterator {
            for ((i1, v1) in this@combinations.withIndex()) {
                for (v2 in this@combinations.drop(i1 + 1)) {
                    yield(Pair(v1, v2))
                }
            }
        }
    }
}

/**
 * Return 2 length subsequences of elements from the input iterable allowing individual elements to be repeated more than once.
 *
 * Combinations are emitted in lexicographic sort order. So, if the input iterable is sorted,
 * the combination tuples will be produced in sorted order.
 *
 * Elements are treated as unique based on their position, not on their value.
 * So if the input elements are unique, there will be no repeat values in each combination.
 *
 * This one use yield.
 */
fun <T> Iterable<T>.combinationsWithReplacement(): Iterable<Pair<T, T>> {
    return object : Iterable<Pair<T, T>> {
        override fun iterator(): Iterator<Pair<T, T>> = buildIterator {
            for ((i1, v1) in this@combinationsWithReplacement.withIndex()) {
                for (v2 in this@combinationsWithReplacement.drop(i1)) {
                    yield(Pair(v1, v2))
                }
            }
        }
    }
}
