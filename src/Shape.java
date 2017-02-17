import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Color;

public class Shape extends GameObject {
	
	private Location location;
	private Type type;

	public Shape(Location location, Type type) {
		super(location, type.width, type.height);
		this.location = location;
		this.type = type;

		switch (this.type) {
			case SQUARE:
				health = 10;
				break;
			case TRIANGLE:
				health = 35;
				break;
			case PENTAGON:
				health = 60;
				break;
		}
	}

	@Override
	public void checkOffScreen() {
	}

	@Override
	public void draw(Graphics2D g) {		
		Polygon shape = new Polygon();
		for (int i = 0; i < type.numSides; i++) {
			shape.addPoint(
				(int)(location.x + (int)(type.width) * Math.cos(i * 2 * Math.PI / type.numSides)),
				(int)(location.y + (int)(type.height) * Math.sin(i * 2 * Math.PI / type.numSides)));
		}

		switch (type.numSides) {
			case 3:
				g.setColor(Color.ORANGE);
				break;
			case 4:
				g.setColor(Color.RED);
				break;
			case 5:
				g.setColor(Color.BLUE);
				break;
		}

		g.fillPolygon(shape);
	}

}