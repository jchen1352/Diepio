public class Location {

	
	public double x,y;
	
	public Location(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public Location(Location other) {
		x=other.x;
		y=other.y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void addX(double x) {
		this.x += x;
	}
	
	public void addY(double y) {
		this.y += y;
	}
	
	/**
	 * Adds a vector defined by magnitude and direction to itself.
	 * @param magnitude
	 * @param direction
	 */
	public void addVector(double magnitude, double direction) {
		addX(magnitude*Math.cos(direction));
		addY(magnitude*Math.sin(direction));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
}