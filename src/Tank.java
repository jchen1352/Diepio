import java.awt.Graphics;
import java.awt.Rectangle;


public class Tank extends GameObject {

	private Weapon weapon;
	
	public Tank(Location location, double width, double height) {
		super(location, width, height);
		weapon = new Weapon(direction);
	}

	public Bullet shoot() {
		return weapon.shoot(new Location(location), 5);		
	}

	public void forward() {
		speed = 1;
		direction = -1.57;
		this.move();
	}

	public void left() {
		speed = 1;
		direction = 3.14;
		this.move();
	}

	public void reverse() {
		speed = 1;
		direction = 1.57;
		this.move();
	}
	
	public void right() {
		speed = 1;
		direction = 0;
		this.move();
	}

	public void aimWeapon(double direction) {
		weapon.aim(direction);
	}

	public Location location() {
		return location;
	}

	@Override
	public void checkOffScreen() {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics g) {
		Rectangle bound = getBoundingRect();
		g.fillRect(bound.x, bound.y, bound.width, bound.height);
	}

}
