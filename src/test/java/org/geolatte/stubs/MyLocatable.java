package org.geolatte.stubs;

import org.geolatte.graph.Locatable;

/**
 * <p>
 * No comment provided yet for this class.
 * </p>
 *
 * @author Bert Vanhooff
 * @author <a href="http://www.qmino.com">Qmino bvba</a>
 * @since SDK1.5
 */
public class MyLocatable implements Locatable {


    private double x;
    private double y;

    public MyLocatable(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The X-coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return The Y-coordinate
     */
    public double getY() {
        return this.y;
    }
}
