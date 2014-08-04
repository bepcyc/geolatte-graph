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

package org.geolatte.graph.algorithms;

import org.geolatte.data.PairNode;
import org.geolatte.data.PairingHeap;
import org.geolatte.graph.InternalNode;
import org.geolatte.graph.PredGraph;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements a Min-PriorityQueue for PredGraphs in terms of a Pairing Heap.
 *
 * @param <V>
 * @author Karel Maesen, Geovise BVBA
 */
public class PMinQueue<V, E> implements Serializable {

    private static final long serialVersionUID = -5781184998490076527L;
	private final PairingHeap<Element<V, E>> heap = new PairingHeap<Element<V, E>>();
    private final Map<InternalNode<V, E>, PairNode<Element<V, E>>> index = new HashMap<InternalNode<V, E>, PairNode<Element<V, E>>>();

    /**
     * Creates an instance of PMinQueue.
     */
    protected PMinQueue() {
    }

    /**
     * Adds a the given value with the given priority (key).
     *
     * @param value The value to add.
     * @param key   The priority.
     */
    public void add(PredGraph<V, E> value, double key) {
        PairNode<Element<V, E>> pn = heap.insert(new Element<V, E>(value, key));
        this.index.put(value.getInternalNode(), pn);
    }

    /**
     * Removes and returns the element from the queue with the smallest key (lowest priority).
     *
     * @return The element with the smallest key.
     */
    public PredGraph<V, E> extractMin() {
        PredGraph<V, E> val = heap.deleteMin().value;
        this.index.remove(val.getInternalNode());
        return val;
    }

    /**
     * Gets the predecessor graph associated with the given node.
     *
     * @param node The node.
     * @return A predecessor graph.
     */
    public PredGraph<V, E> get(InternalNode<V, E> node) {
        PairNode<Element<V, E>> pNode = this.index.get(node);
        if (pNode == null) {
            return null;
        }
        return pNode.getElement().value;
    }

    /**
     * Gets a value indicating whether the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    public void update(PredGraph<V, E> value, double r) {
        PairNode<Element<V, E>> node = this.index.get(value.getInternalNode());
        if (node == null) {
            throw new RuntimeException("Node not in Pairing Heap.");
        }
        Element<V, E> newElement = new Element<V, E>(value, r);
        this.heap.decreaseKey(node, newElement);
    }

    static class Element<V, E> implements Comparable<Element<V, E>>, Serializable {
        private static final long serialVersionUID = -8745364601911679907L;
		private final Double key;
        private final PredGraph<V, E> value;

        Element(PredGraph<V, E> value, Double key) {
            this.key = key;
            this.value = value;
        }


        public int compareTo(Element<V, E> o) {
            return this.key.compareTo(o.key);
        }
    }

}

