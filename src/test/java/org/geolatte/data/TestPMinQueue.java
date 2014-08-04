/*
 * This file is part of the GeoLatte project.
 *
 *     GeoLatte is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     GeoLatte is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with GeoLatte.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2010 - 2011 and Ownership of code is shared by:
 * Qmino bvba - Esperantolaan 4 - 3001 Heverlee  (http://www.qmino.com)
 * Geovise bvba - Generaal Eisenhowerlei 9 - 2140 Antwerpen (http://www.geovise.com)
 */

package org.geolatte.data;

//public class TestPMinQueue {
//
//    PMinQueue<Double> minQueue;
//
//    @Before
//    public void setup(){
//        minQueue = new PMinQueue<Double>();
//        minQueue.add(Double.valueOf(100f), 100f);
//        minQueue.add(Double.valueOf(50f), 50f);
//        minQueue.add(Double.valueOf(10f), 10f);
//        minQueue.add(Double.valueOf(50f), 50f);
//        minQueue.add(Double.valueOf(40f), 40f);
//        minQueue.add(Double.valueOf(80f), 80f);
//        minQueue.add(Double.valueOf(30f), 30f);
//        minQueue.add(Double.valueOf(120f), 120f);
//    }
//
//    @Test
//    public void test_min_queue_min_order() {
//        assertEquals(Double.valueOf(10f), minQueue.deleteMin());
//        minQueue.add(Double.valueOf(1.0f), 1.0f);
//        assertEquals(Double.valueOf(1f), minQueue.deleteMin());
//    }
//
//    @Test
//    public void test_get_value() {
//        Double f = minQueue.get(Double.valueOf(80f));
//        assertEquals(Double.valueOf(80f), f);
//    }
//
//    @Test
//    public void test_isEmpty() {
//        assertTrue(!minQueue.isEmpty());
//        PMinQueue<Double> emptyQueue = new PMinQueue<Double>();
//        assertTrue(emptyQueue.isEmpty());
//    }
//
//
//    @Test
//    public void test_update() {
//        Double value = Double.valueOf(80f);
//        minQueue.update(value, 0.0f);
//        Double minValue = minQueue.deleteMin();
//        assertEquals(value, minValue);
//    }
//
//
//
//
//}
