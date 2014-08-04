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

import org.geolatte.graph.Locatable;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * <p>
 * Test for the {@link DistanceHeuristicStrategy} class.
 * </p>
 *
 * @author Bert Vanhooff
 * @author <a href="http://www.qmino.com">Qmino bvba</a>
 * @since SDK1.5
 */
public class DistanceHeuristicStrategyTest {

    @Test
    public void testGetValue() throws Exception {

        final double factor = 0.5f;
        DistanceHeuristicStrategy<Locatable> strategy = new DistanceHeuristicStrategy<Locatable>(factor);

        Locatable loc1 = Mockito.mock(Locatable.class);
        Mockito.when(loc1.getX()).thenReturn(0.0d);
        Mockito.when(loc1.getY()).thenReturn(0.0d);

        Locatable loc2 = Mockito.mock(Locatable.class);
        Mockito.when(loc2.getX()).thenReturn(1.0d);
        Mockito.when(loc2.getY()).thenReturn(1.0d);

        // => distance = sqrt(1*1 + 1*1)

        Assert.assertEquals(factor * Math.sqrt(2), strategy.getValue(loc1, loc2), 0.005);
    }
}
