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

import org.geolatte.graph.InternalNode;

/**
 * <p>
 * A heuristic relaxer that takes into account:
 * - The path cost (from source to the current internalNode),
 * - A heuristic calculated by a given strategy.
 * <p/>
 * For example, the {@link DistanceHeuristicStrategy} can be used to take the straight-line distance to the target
 * internalNode as heuristic value.
 * </p>
 *
 * @param <N> The type of domain node.
 * @param <E> The edge label type.
 * @author Karel Measen
 * @author Bert Vanhooff
 * @author <a href="http://www.qmino.com">Qmino bvba</a>
 * @since SDK1.5
 */
class HeuristicRelaxer<N, E> extends DefaultRelaxer<N, E> {

    private static final long serialVersionUID = -4774499761441586839L;
	private final double heuristicWeight; // weight given to the heuristic component
    private final N destination;
    private final HeuristicStrategy<N> heuristicStrategy;

    /**
     * Constructs a heuristic relaxer
     *
     * @param heuristicWeight   The weight for the heuristic component.
     * @param destination       The final destination internalNode.
     * @param heuristicStrategy The strategy used to calculate the heuristic.
     */
    protected HeuristicRelaxer(double heuristicWeight, N destination, HeuristicStrategy<N> heuristicStrategy) {

        this.heuristicWeight = heuristicWeight;
        this.destination = destination;
        this.heuristicStrategy = heuristicStrategy;
    }

    protected double update(InternalNode<N, E> nd, double baseWeight) {

        return baseWeight + this.heuristicWeight * (heuristicStrategy.getValue(nd.getWrappedNode(), destination));
    }


}
