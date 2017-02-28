
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Color;

public class Shape extends GameObject {

	private Location location;
	private Type type;

	public Shape(Location location, Type type, DiepIOMap map) {
		super(location, type.width, type.height, map);
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
			shape.addPoint((int) (location.x + (int) (type.width) * Math.cos(i * 2 * Math.PI / type.numSides)),
					(int) (location.y + (int) (type.height) * Math.sin(i * 2 * Math.PI / type.numSides)));
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

		Rectangle bound = getBoundingRect();
		g.setColor(Color.GREEN);
		g.fillRect(bound.x, bound.y + 31, (int) (health) / 3, 4);
	}

	@Override
	public void checkCollision() {
		for (int i = 0; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);
			if (this == go) {
				// System.out.println("No colliding with yourself");
				continue;
			}

			if (go instanceof Bullet) {
			}
			if (getBoundingRect().intersects(go.getBoundingRect())) {
				health -= 5;
				// System.out.println("Shape at "+location+" took damage");
				if (health <= 0) {
					if (go instanceof Bullet) {
						Bullet b = (Bullet) go;
						b.getOwner().incNumKills();
					}
				}
			}
		}
	}
}