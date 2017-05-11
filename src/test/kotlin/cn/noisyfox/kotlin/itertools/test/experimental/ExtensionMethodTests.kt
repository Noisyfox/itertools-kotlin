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

package cn.noisyfox.kotlin.itertools.test.experimental

import cn.noisyfox.kotlin.itertools.experimental.xproduct
import kotlin.test.assertEquals
import org.junit.Test as test

/**
 * Created by Noisyfox on 2017/5/12.
 */
class TestExtensionMethods {

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
        ), left.xproduct(right).toList())
    }
}
