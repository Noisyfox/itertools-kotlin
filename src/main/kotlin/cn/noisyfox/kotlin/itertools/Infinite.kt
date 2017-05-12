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
 * Make an iterator that returns values starting with [start] with evenly space [step].
 */
inline fun <T> count(start: T, step: T, crossinline add: (T, T) -> T): Sequence<T> {
    return Sequence {
        object : Iterator<T> {
            var n = start

            override fun hasNext(): Boolean = true

            override fun next(): T {
                val v = n
                n = add(n, step)

                return v
            }
        }
    }
}

/**
 * Make an iterator that returns values starting with [start] with evenly space [step].
 */
fun count(start: Byte, step: Byte = 1): Sequence<Byte> = count(start, step) { n, s -> (n + s).toByte() }

/**
 * Make an iterator that returns values starting with [start] with evenly space [step].
 */
fun count(start: Short, step: Short = 1): Sequence<Short> = count(start, step) { n, s -> (n + s).toShort() }

/**
 * Make an iterator that returns values starting with [start] with evenly space [step].
 */
fun count(start: Int, step: Int = 1): Sequence<Int> = count(start, step) { n, s -> n + s }

/**
 * Make an iterator that returns values starting with [start] with evenly space [step].
 */
fun count(start: Long, step: Long = 1L): Sequence<Long> = count(start, step) { n, s -> n + s }

/**
 * Make an iterator that returns values starting with [start] with evenly space [step].
 */
fun count(start: Float, step: Float = 1f): Sequence<Float> = count(start, step) { n, s -> n + s }

/**
 * Make an iterator that returns values starting with [start] with evenly space [step].
 */
fun count(start: Double, step: Double = 1.0): Sequence<Double> = count(start, step) { n, s -> n + s }


/**
 * Make an iterator returning elements from the iterable and saving a copy of each.
 * When the iterable is exhausted, return elements from the saved copy. Repeats indefinitely.
 */
fun <T> Iterable<T>.cycle(): Sequence<T> {
    val notEmpty = this.any()

    return Sequence {
        object : Iterator<T> {
            val saved = mutableListOf<T>()
            var iter: Iterator<T>? = this@cycle.iterator()
            lateinit var iter_saved: Iterator<T>

            override fun hasNext(): Boolean = notEmpty

            override fun next(): T {
                val iter_orig = iter

                if (iter_orig != null) {
                    if (iter_orig.hasNext()) {
                        val v = iter_orig.next()
                        saved.add(v)

                        return v
                    } else {
                        iter = null

                        if (saved.none()) {
                            throw IllegalStateException("Items changed during iterating.")
                        }

                        iter_saved = saved.iterator()
                    }
                }

                if (!iter_saved.hasNext()) {
                    iter_saved = saved.iterator()
                }

                return iter_saved.next()
            }

        }
    }
}


/**
 * Make an iterator that returns element over and over again. Runs indefinitely.
 */
fun <T> T.repeat(): Sequence<T> {
    return Sequence {
        object : Iterator<T> {
            override fun hasNext(): Boolean = true

            override fun next(): T = this@repeat
        }
    }
}

/**
 * Make an iterator that returns element over and over again. Runs [count] times.
 */
fun <T> T.repeat(count: Int): Sequence<T> {
    return Sequence {
        object : Iterator<T> {
            val it = (1..count).iterator()

            override fun hasNext(): Boolean = it.hasNext()

            override fun next(): T {
                it.next()
                return this@repeat
            }
        }
    }
}
