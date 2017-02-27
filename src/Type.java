public enum Type {
	SQUARE(20,20, 4),
	TRIANGLE(20,20, 3),
	PENTAGON(15,15, 5);
		
	public final double width;
	public final double height;
	public final int numSides;
	
	private Type(double width, double height, int numSides) {
		this.width = width;
		this.height = height;
		this.numSides = numSides;
	}
};