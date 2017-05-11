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

package cn.noisyfox.kotlin.itertools.test


import cn.noisyfox.kotlin.itertools.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.Test as test

/**
 * Created by Noisyfox on 2017/5/11.
 */
class TestExtensionMethods {
    @test fun testNullElse() {
        val l = listOf(null, 0, 1, 2, 3, 4, 5, 6, 7, null)

        assertThat((l as Iterable<Int?>).firstNotNull(), equalTo(0))
        assertThat((l as Iterable<Int?>).lastNotNull(), equalTo(7))

        assertThat(l.firstNotNull(), equalTo(0))
        assertThat(l.lastNotNull(), equalTo(7))

        assertThat((l as Iterable<Int?>).firstNotNullOrElse { 2 }, equalTo(0))
        assertThat((l as Iterable<Int?>).lastNotNullOrElse { 3 }, equalTo(7))

        assertThat(l.firstNotNullOrElse { 4 }, equalTo(0))
        assertThat(l.lastNotNullOrElse { 5 }, equalTo(7))

        val l2 = listOf<Int?>(null, null, null)

        assertFailsWith(NoSuchElementException::class)
        {
            (l2 as Iterable<Int?>).firstNotNull()
        }
        assertFailsWith(NoSuchElementException::class)
        {
            (l2 as Iterable<Int?>).lastNotNull()
        }
        assertFailsWith(NoSuchElementException::class)
        {
            l2.firstNotNull()
        }
        assertFailsWith(NoSuchElementException::class)
        {
            l2.lastNotNull()
        }

        assertThat((l2 as Iterable<Int?>).firstNotNullOrElse { 2 }, equalTo(2))
        assertThat((l2 as Iterable<Int?>).lastNotNullOrElse { 3 }, equalTo(3))

        assertThat(l2.firstNotNullOrElse { 4 }, equalTo(4))
        assertThat(l2.lastNotNullOrElse { 5 }, equalTo(5))
    }

    @test fun testCombination() {
        val left = listOf(1, 2, 3, 4, 5, null)
        val right = listOf('A', 'B', 'C')

        assertEquals(listOf(
                Pair(1, 'A'), Pair(1, 'B'), Pair(1, 'C'),
                Pair(2, 'A'), Pair(2, 'B'), Pair(2, 'C'),
                Pair(3, 'A'), Pair(3, 'B'), Pair(3, 'C'),
                Pair(4, 'A'), Pair(4, 'B'), Pair(4, 'C'),
                Pair(5, 'A'), Pair(5, 'B'), Pair(5, 'C'),
                Pair(null, 'A'), Pair(null, 'B'), Pair(null, 'C')
        ), left.product(right).toList())

        assertEquals(listOf(
                Pair(1, 'A'), Pair(1, 'B'), Pair(1, 'C'),
                Pair(2, 'A')
        ), left.product(right).take(4).toList())

        assertEquals(listOf(
                Pair('A', 'A'), Pair('A', 'B'), Pair('A', 'C'),
                Pair('B', 'A'), Pair('B', 'B'), Pair('B', 'C'),
                Pair('C', 'A'), Pair('C', 'B'), Pair('C', 'C')
        ), right.product().toList())
    }

    @test fun testInfinite() {
        assertEquals(listOf(1, 1, 1, 1, 1, 1, 1, 1), repeat(1, 8).toList())
    }
}
