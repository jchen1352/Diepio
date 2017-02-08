import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {

	private double speed;// 0 - 10
	protected double direction, // degrees or radians
			x, y, // >= 0

			size, // 10 might be a good size
			health, // 0 - 100
			power;// not sure about this...
	private int level;//
	private Color color;
	private Image img;
	protected Location location;

	public void move() {
		x += speed * Math.cos(direction);

		checkOffScreen();
		// maybe "push" back onto the screen change direction if
		// this object goes off the screen
	}

	public abstract void checkOffScreen();

	public Rectangle getBoundingRect() {

		return new Rectangle((int) x, (int) y, (int) size, (int) size);
	}
	
	public abstract void draw(Graphics g);

}
