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

import java.io.Serializable;
import java.util.Arrays;

/**
 * <p>
 * A simple implementation of {@link EdgeWeight} that can store an arbitrary set of weights.
 * </p>
 *
 * @author Bert Vanhooff
 * @author <a href="http://www.qmino.com">Qmino bvba</a>
 * @since SDK1.5
 */
public class ArrayEdgeWeight implements EdgeWeight, Serializable {

	private static final long serialVersionUID = -3655825881525807357L;
	private final double[] weights;

    /**
     * Constructs an ArrayEdgeWeight using the given array of weights.
     *
     * @param weights An array of weights.
     */
    public ArrayEdgeWeight(double[] weights) {

        this.weights = Arrays.copyOf(weights, weights.length);
    }

    public double getValue(int weightIndex) {

        return weights[weightIndex];
    }
}
