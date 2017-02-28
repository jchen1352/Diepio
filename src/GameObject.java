import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;

public abstract class GameObject {

	protected double speed;// 0 - 10
	protected double direction; // in radians
	protected double width; //horizontal width of the object
	protected double height; // vertical height of the object
	protected double health; // 0 - 100
	private Color color;
	private Image img;
	protected Location location;
	private boolean shouldRemove;
	protected DiepIOMap map;

	public GameObject(Location location, double direction, double speed, double width, double height,
			Color color, Image img, DiepIOMap map) {
		this.location = location;
		this.direction = direction;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.color = color;
		this.img = img;
		this.shouldRemove = false;
		this.map = map;
	}

	public GameObject(Location location, double width, double height, DiepIOMap map) {
		this(location, 0, 0, width, height, null, null, map);
	}

	public double health() {
		return health;
	}

	public void move() {
		if (willMoveOffscreen()) {
			checkOffScreen();
		} else {
			location.addVector(speed, direction);
		}
	}

	private boolean willMoveOffscreen() {
		Location loc = new Location(location);
		loc.addVector(speed, direction);
		return !loc.inMap(new Rectangle(map.dimensions()));
	}

	public abstract void checkCollision();

	public abstract void checkOffScreen();

	public Rectangle getBoundingRect() {
		return new Rectangle((int) (location.getX()-width/2),
				(int) (location.getY()-height/2),
				(int) width, (int) height);
	}

	public abstract void draw(Graphics2D g);

	public void markRemove() {
		shouldRemove = true;
	}

	public boolean shouldRemove() {
		return shouldRemove;
	}
	
	public void checkHealth() {
		if (health <= 0) {
			markRemove();
//			System.out.println("Thing at "+location+" died");
		}
	}
	
	public DiepIOMap getMap() {
		return map;
	}
}