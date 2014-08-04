package org.geolatte.graph;

import java.io.Serializable;

/**
 * <p>
 * No comment provided yet for this class.
 * </p>
 *
 * @author Bert Vanhooff
 * @author <a href="http://www.qmino.com">Qmino bvba</a>
 * @since SDK1.5
 */
public class LocatedInternalNodeWrapper<N extends Locatable, E> extends InternalNodeWrapper<N, E> implements Serializable {

	private static final long serialVersionUID = 8517192078659969110L;

	LocatedInternalNodeWrapper(N node) {
        super(node);
    }

    public float getX() {
        return getWrappedNode().getX();
    }

    public float getY() {
        return getWrappedNode().getY();
    }
}
