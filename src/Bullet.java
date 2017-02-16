import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {

	public Bullet(Location location, double direction, double speed, double width, double height) {
		super(location, direction, speed, width, height, null, null);
		health = 5;
	}
	
	@Override
	public void checkOffScreen() {
		if (!location.inMap(new Rectangle(mapDimensions))) {
			map.addToRemoveObjects(this);
		}
	}

	@Override
	public void checkCollision() {

	}

	@Override
	public void draw(Graphics g) {
		Rectangle bound = getBoundingRect();
		g.fillOval(bound.x, bound.y, bound.width, bound.height);
	}

}