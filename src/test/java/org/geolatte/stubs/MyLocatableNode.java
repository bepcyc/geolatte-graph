package org.geolatte.stubs;

import org.geolatte.graph.Locatable;

public class MyLocatableNode extends MyNode implements Locatable {

        private final double x;
        private final double y;

        public MyLocatableNode(int id, double x, double y) {
            super(id);
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return this.x;
        }


        public double getY() {
            return this.y;
        }

        public Object getData() {
            return null;
        }

        public String toString() {
            return String.format("%d: (%f, %f)", getID(), this.x, this.y);
        }
        
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof MyLocatableNode)) return false;
	        if (!super.equals(o)) return false;
	
	        MyLocatableNode that = (MyLocatableNode) o;
	
	        if (Double.compare(that.x, x) != 0) return false;
	        if (Double.compare(that.y, y) != 0) return false;
	
	        return true;
	    }
	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			long temp;
			temp = x != +0.0d ? Double.doubleToLongBits(x) : 0;
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = y != +0.0d ? Double.doubleToLongBits(y) : 0;
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}
        
}