import java.awt.Graphics;


public class Bullet extends GameObject {

	public Bullet(Location location, double direction, double speed, double width, double height) {
		super(location, direction, speed, width, height, null, null);
	}
	
	@Override
	public void checkOffScreen() {

	}

	@Override
	public void draw(Graphics g) {
		g.fillOval((int)location.getX(), (int)location.getY(), (int)width, (int)height);
	}

}
