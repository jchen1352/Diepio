import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {

	protected double speed;// 0 - 10
	protected double direction; // in radians
	protected double width; //horizontal width of the object
	protected double height; // vertical height of the object
	private double health; // 0 - 100
	private Color color;
	private Image img;
	protected Location location;

	public GameObject(Location location, double direction, double speed, double width, double height,
			Color color, Image img) {
		this.location = location;
		this.direction = direction;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.color = color;
		this.img = img;
	}
	
	public GameObject(Location location, double width, double height) {
		this(location, 0, 0, width, height, null, null);
	}
	
	public void move() {
		//System.out.println("before move, location is "+location+" and speed is "+speed);
		location.addVector(speed, direction);
		//System.out.println("after move, location is "+location+" and speed is "+speed);

		checkOffScreen();
		// maybe "push" back onto the screen change direction if
		// this object goes off the screen
	}

	public abstract void checkOffScreen();

	public Rectangle getBoundingRect() {
		
		return new Rectangle((int) location.getX(), (int) location.getY(), (int) width, (int) height);
	}
	
	public abstract void draw(Graphics g);

}
