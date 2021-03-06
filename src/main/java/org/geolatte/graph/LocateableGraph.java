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

package org.geolatte.graph;

import java.util.List;

/**
 * Representation of a graph of internal nodes, which have a X/Y location.
 *
 * @param <N> The type that represents the nodes.
 * @param <E> The edge label type.
 */
public interface LocateableGraph<N, E> extends Graph<N, E> {

    /**
     * Gets the nodes at the given location.
     *
     * @param loc A location.
     * @return A list of nodes.
     */
    public List<InternalNode<N, E>> getNodesAt(Locatable loc);

    /**
     * Searches the given number of nodes closest to the given location.
     *
     * @param location    The reference location to search from.
     * @param number      The number of closest nodes to find.
     * @param maxDistance The maximum distance to search in.
     * @return A list of closest nodes.
     */
    public List<InternalNode<N, E>> getClosestNodes(Locatable location, int number, int maxDistance);

}
