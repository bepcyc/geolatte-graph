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
import java.util.ArrayList;
import java.util.List;

/**
 * Offers a number of static factory methods to create spatial indexes.
 */
public class SpatialIndexes implements Serializable {

    private static final long serialVersionUID = 4794405414932492783L;


	/**
     * Creates a builder for a spatial index.
     *
     * @param extent     The extent of the index. Nodes can only be within or on the edges of the extent.
     * @param resolution The grid resolution: size of a single cell. Must be a positive number. Should be smaller than
     *                   the extent size.
     * @param <N>        Type of the domain nodes.
     * @return A builder for grid indexes.
     */
    public static <N extends Locatable> SpatialIndexBuilder<N> createGridIndexBuilder(Extent extent, int resolution) {
        return new GridIndexBuilder<N>(extent, resolution);
    }


    // Implementation Spatial Index Builders
    private static class GridIndexBuilder<N extends Locatable> implements SpatialIndexBuilder<N> {

        private final float resolution;
        private final Extent extent;

        //the resolution values satisfy the property:
        // value / resolution provides the cell number
        private final List<Locatable>[][] grid;
        private final int xNumCells, yNumCells;

        @SuppressWarnings("unchecked")
        private GridIndexBuilder(Extent extent, float resolution) {
            this.extent = extent;
            if (resolution < 1) {
                throw new IllegalArgumentException("Resolution must be larger than 1");
            }
            this.resolution = resolution;

            // calculate number of cells, add one cell more to allow points on outer edges to be included
            this.xNumCells = (int) ((this.extent.getMaxX() - this.extent.getMinX()) / this.resolution) + 1;
            this.yNumCells = (int) ((this.extent.getMaxY() - this.extent.getMinY()) / this.resolution) + 1;

            this.grid = new List[this.xNumCells][this.yNumCells];

        }

        public boolean isWithinBounds(Locatable nd) {

            if (nd.getX() < this.extent.getMinX() || nd.getX() > this.extent.getMaxX()) {
                return false;
            }
            if (nd.getY() < this.extent.getMinY() || nd.getY() > this.extent.getMaxY()) {
                return false;
            }
            return true;
        }

        public void insert(Locatable node) {
            if (!isWithinBounds(node)) {
                throw new RuntimeException("Tried insert object that lies out of bounds: " + node);
            }



            //calculate x/y cell index
            int xCellIdx = (int) ((node.getX() - this.extent.getMinX()) / this.resolution);
            int yCellIdx = (int) ((node.getY() - this.extent.getMinY()) / this.resolution);

            List<Locatable> cell = this.grid[xCellIdx][yCellIdx];
            if (cell == null) {
                cell = new ArrayList<Locatable>();
                this.grid[xCellIdx][yCellIdx] = cell;

            }
            cell.add(node);

        }

        public SpatialIndex<N> build() throws BuilderException {
            GridIndex<N> index = new GridIndex<N>(this.extent, this.resolution);
            index.setGrid(toCompressedArray(this.grid));
            return index;
        }

        private Object[][][] toCompressedArray(List<Locatable>[][] grid) {
            Object[][][] newGrid = new Object[this.xNumCells][this.yNumCells][];
            for (int xi = 0; xi < grid.length; xi++) {
                List<Locatable>[] xar = grid[xi];
                for (int yi = 0; yi < xar.length; yi++) {
                    List<Locatable> cell = xar[yi];
                    if (cell != null) {
                        newGrid[xi][yi] = cell.toArray();
                    }
                }
            }
            return newGrid;
        }

    }
}
