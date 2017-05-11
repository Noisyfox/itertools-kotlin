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
 * Created by Noisyfox on 2017/5/11.
 */

/**
 * Returns the first element matching the given [predicate],
 * or the result of calling the [defaultValue] function if no such element is found.
 */
inline fun <T> Iterable<T>.firstOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    for (element in this) if (predicate(element)) return element

    return defaultValue()
}

/**
 * Returns the first element which is not null.
 * @throws [NoSuchElementException] if there are no elements or all elements are null.
 */
fun <T> Iterable<T?>.firstNotNull(): T = this.first { it != null } as T

/**
 * Returns the first element which is not null.
 * @throws [NoSuchElementException] if there are no elements or all elements are null.
 */
fun <T> List<T?>.firstNotNull(): T = this.first { it != null } as T

/**
 * Returns the first element which is not null,
 * or the result of calling the [defaultValue] function if there are no elements or all elements are null.
 */
inline fun <T> Iterable<T?>.firstNotNullOrElse(defaultValue: () -> T): T = firstOrElse({ it != null }, defaultValue) as T

/**
 * Returns the first element which is not null,
 * or the result of calling the [defaultValue] function if there are no elements or all elements are null.
 */
inline fun <T> List<T?>.firstNotNullOrElse(defaultValue: () -> T): T = firstOrElse({ it != null }, defaultValue) as T

/**
 * Returns the last element matching the given [predicate],
 * or the result of calling the [defaultValue] function if no such element is found.
 */
inline fun <T> Iterable<T>.lastOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    var last: T? = null
    var found = false
    for (element in this) {
        if (predicate(element)) {
            last = element
            found = true
        }
    }
    if (!found) {
        return defaultValue()
    }
    return last as T
}

/**
 * Returns the last element matching the given [predicate],
 * or the result of calling the [defaultValue] function if no such element is found.
 */
inline fun <T> List<T>.lastOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    val iterator = this.listIterator(size)
    while (iterator.hasPrevious()) {
        val element = iterator.previous()
        if (predicate(element)) return element
    }

    return defaultValue()
}

/**
 * Returns the last element which is not null.
 * @throws [NoSuchElementException] if there are no elements or all elements are null.
 */
fun <T> Iterable<T?>.lastNotNull(): T = this.last { it != null } as T

/**
 * Returns the last element which is not null.
 * @throws [NoSuchElementException] if there are no elements or all elements are null.
 */
fun <T> List<T?>.lastNotNull(): T = this.last { it != null } as T

/**
 * Returns the last element which is not null,
 * or the result of calling the [defaultValue] function if there are no elements or all elements are null.
 */
inline fun <T> Iterable<T?>.lastNotNullOrElse(defaultValue: () -> T): T = lastOrElse({ it != null }, defaultValue) as T

/**
 * Returns the last element which is not null,
 * or the result of calling the [defaultValue] function if there are no elements or all elements are null.
 */
inline fun <T> List<T?>.lastNotNullOrElse(defaultValue: () -> T): T = lastOrElse({ it != null }, defaultValue) as T
