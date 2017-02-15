import java.awt.Graphics;

public class Shape extends GameObject {

	public enum Type {
		SQUARE(10,10),
		TRIANGLE(15,15),
		PENTAGON(20,20);
		
		public final double width;
		public final double height;
		private Type(double width, double height) {
			this.width = width;
			this.height = height;
		}
	};
	
	private Type type;
	
	public Shape(Location location, Type type) {
		super(location, type.width, type.height);
	}
	
	@Override
	public void checkOffScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}