import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {

	private double speed;// 0 - 10
	protected double direction, // degrees or radians
			size, // 10 might be a good size
			health, // 0 - 100
			power;// not sure about this...
	private int level;//
	private Color color;
	private Image img;
	protected Location location;

	public void move() {
		location.setX(speed * Math.cos(direction));
		location.setY(speed * Math.sin(direction));
		checkOffScreen();
	}

	public abstract void checkOffScreen();

	public Rectangle getBoundingRect() {
		return new Rectangle((int) location.x(), (int) location.y(), (int) size, (int) size);
	}
	
	public abstract void draw(Graphics g);

}
