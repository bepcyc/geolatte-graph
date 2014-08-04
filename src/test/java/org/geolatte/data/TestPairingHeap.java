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

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.*;

/**
 * @author Karel Maesen, Geovise BVBA
 *         creation-date: Apr 22, 2010
 */
public class TestPairingHeap {


    @Test
    public void test_empty_constructor() {
        PairingHeap<Data> ph = new PairingHeap<Data>();
        assertNotNull(ph);
    }

    @Test
    public void test_insert_respects_invariant() {
        PairingHeap<Data> heap = new PairingHeap<Data>();
        assertTrue(heap.isEmpty());
        heap.insert(new Data(10d));
        assertTrue(!heap.isEmpty());
        Data min = heap.findMin();
        assertEquals(Double.valueOf(10d), min.value);
        heap.insert(new Data(1d));
        min = heap.findMin();
        assertEquals(Double.valueOf(1d), min.value);
        heap.insert(new Data(4.d));
        min = heap.findMin();
        assertEquals(Double.valueOf(1d), min.value);
        heap.insert(new Data(12d));
        min = heap.findMin();
        assertEquals(Double.valueOf(1d), min.value);

        //start deleting elements in order
        min = heap.deleteMin();
        assertEquals(Double.valueOf(1d), min.value);
        min = heap.findMin();
        assertEquals(Double.valueOf(4.0d), min.value);
        min = heap.deleteMin();
        assertEquals(Double.valueOf(4.0d), min.value);
        min = heap.deleteMin();
        assertEquals(Double.valueOf(10.0d), min.value);
        min = heap.deleteMin();
        assertEquals(Double.valueOf(12.0d), min.value);
        assertTrue(heap.isEmpty());

    }


    @Test
    public void test_random_order() {
        int size = 100000;
        PairingHeap<Data> heap = new PairingHeap<Data>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int rint = random.nextInt();
            Data d = new Data((double) rint);
            heap.insert(d);
        }

        Data prev = heap.deleteMin();

        while (!heap.isEmpty()) {
            Data min = heap.deleteMin();
            assertTrue(min.value >= prev.value);
        }

    }

    @Test
    public void test_random_decrease() {
        int size = 100000;
        PairingHeap<Data> heap = new PairingHeap<Data>();
        Random random = new Random();
        List<PairNode<Data>> nodelist = new ArrayList<PairNode<Data>>();
        for (int i = 0; i < size; i++) {
            int rint = random.nextInt();
            Data d = new Data((double) rint);
            PairNode<Data> pn = heap.insert(d);
            nodelist.add(pn);
        }

        for (int i = 0; i < size; i++) {
            PairNode<Data> pn = nodelist.get(i);
            Data data = pn.getElement();
            data.value -= 100f;
            heap.decreaseKey(pn, data);
        }

        Data prev = heap.deleteMin();
        while (!heap.isEmpty()) {
            Data min = heap.deleteMin();
            assertTrue(min.value >= prev.value);
        }

    }

    @Test
    public void test_random_order_with_comparator() {
        int size = 100000;
        PairingHeap<NCData> heap = new PairingHeap<NCData>(new NCDataComparator());
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int rint = random.nextInt();
            NCData d = new NCData((double) rint);
            heap.insert(d);
        }

        NCData prev = heap.deleteMin();

        while (!heap.isEmpty()) {
            NCData min = heap.deleteMin();
            assertTrue(min.value >= prev.value);
        }

    }

    @Test
    public void test_comparator_or_comparable() {
        PairingHeap<NCData> heap = new PairingHeap<NCData>();
        try {
            heap.insert(new NCData(10d));
            fail("Either comparable, or explicit comparator needed!");
        } catch (IllegalArgumentException e) {
            //OK
        }
    }

    @Test
    public void test_decrease_key() {
        PairingHeap<Data> heap = new PairingHeap<Data>();

        Data d1 = new Data(1.0d);
        Data d2 = new Data(2.0d);
        Data d3 = new Data(3.0d);
        Data d4 = new Data(4.0d);

        PairNode<Data> pn2 = heap.insert(d2);
        PairNode<Data> pn4 = heap.insert(d4);
        PairNode<Data> pn1 = heap.insert(d1);
        PairNode<Data> pn3 = heap.insert(d3);

        heap.decreaseKey(pn4, new Data(-1.0d));
        assertEquals(Double.valueOf(-1.0d), heap.findMin().value);

        heap.decreaseKey(pn3, new Data(-2.0d));
        assertEquals(Double.valueOf(-2.0d), heap.findMin().value);

    }

    @Test
    public void test_decrease_key_with_higher_value() {
        PairingHeap<Data> heap = new PairingHeap<Data>();

        Data d1 = new Data(1.0d);
        Data d2 = new Data(2.0d);
        Data d3 = new Data(3.0d);
        Data d4 = new Data(4.0d);

        PairNode<Data> pn2 = heap.insert(d2);
        PairNode<Data> pn4 = heap.insert(d4);
        PairNode<Data> pn1 = heap.insert(d1);
        PairNode<Data> pn3 = heap.insert(d3);

        heap.decreaseKey(pn1, new Data(2.0d));
        heap.decreaseKey(pn2, new Data(3.0d));
        heap.decreaseKey(pn3, new Data(3.0d));

        assertEquals(Double.valueOf(1.0d), heap.deleteMin().value);
        assertEquals(Double.valueOf(2.0d), heap.deleteMin().value);
        assertEquals(Double.valueOf(3.0d), heap.deleteMin().value);
    }

    @Test
    public void test_delete_min_on_empty_throws_exception() {
        PairingHeap<Data> heap = new PairingHeap<Data>();
        try {
            heap.deleteMin();
            fail("Exception should be thrown.");
        } catch (IllegalStateException e) {
            //OK
            return;
        }
        fail("Wrong exception thrown");
    }

    @Test
    public void test_find_min_on_empty_throws_exception() {
        PairingHeap<Data> heap = new PairingHeap<Data>();
        try {
            heap.findMin();
            fail("Exception should be thrown.");
        } catch (IllegalStateException e) {
            //OK
            return;
        }
        fail("Wrong exception thrown");
    }

    static class Data implements Comparable<Data> {

        public Double value;

        Data(Double d) {
            this.value = d;
        }

        public int compareTo(Data o) {
            return this.value.compareTo(o.value);
        }
    }

    static class NCData {
        public final Double value;

        NCData(Double d) {
            this.value = d;
        }
    }


    static class NCDataComparator implements Comparator<NCData> {

        public int compare(NCData o1, NCData o2) {
            return o1.value.compareTo(o2.value);
        }
    }


}

