import java.awt.Graphics;
import java.awt.Rectangle;


public class Bullet extends GameObject {

	public Bullet(Location location, double direction, double speed, double width, double height) {
		super(location, direction, speed, width, height, null, null);
	}
	
	@Override
	public void checkOffScreen() {

	}

	@Override
	public void draw(Graphics g) {
		Rectangle bound = getBoundingRect();
		g.fillOval(bound.x, bound.y, bound.width, bound.height);
	}

}
