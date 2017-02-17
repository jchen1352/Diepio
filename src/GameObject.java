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

	protected static Dimension mapDimensions;
	protected static DiepIOMap map;

	public GameObject(Location location, double direction, double speed, double width, double height,
			Color color, Image img) {
		this.location = location;
		this.direction = direction;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.color = color;
		this.img = img;

		if (map == null) {
			map = new DiepIOMap();	
		}
		mapDimensions = map.dimensions();
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

		checkCollision();
	}

	public void checkCollision() {
		for (int i = 1; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);

			/*
			 * Check if the GameObject is a bullet by checking if the GameObject is originating 
			 * from within 'this', if it is then return becuase we don't want a tank to shoot itself
			 */
			if (this.getBoundingRect().contains(new Point(go.getBoundingRect().x, go.getBoundingRect().y))) { return; }

			if (this.getBoundingRect().intersects(go.getBoundingRect())) {
				this.health -= 5;
				go.health -=5;

				if (this.health == 0) {
					map.addToRemoveObjects(this);
				}  
				if (go.health == 0) {
					map.addToRemoveObjects(go);
				}
				return;
			} 
		}
	}

	public abstract void checkOffScreen();

	public Rectangle getBoundingRect() {
		return new Rectangle((int) (location.getX()-width/2),
				(int) (location.getY()-height/2),
				(int) width, (int) height);
	}
	
	public abstract void draw(Graphics2D g);
}


