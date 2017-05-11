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


import cn.noisyfox.kotlin.itertools.firstNotNull
import cn.noisyfox.kotlin.itertools.firstNotNullOrElse
import cn.noisyfox.kotlin.itertools.lastNotNull
import cn.noisyfox.kotlin.itertools.lastNotNullOrElse
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
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
}