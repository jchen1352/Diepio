import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;
import java.awt.Color;

public class Tank extends GameObject {

	private Weapon weapon;

	public Tank(Location location, double width, double height, DiepIOMap map) {
		super(location, width, height, map);
		weapon = new Weapon(direction, this);
		health = 100;
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
		if (!location.inMap(new Rectangle(map.dimensions()))) {
			direction -= Math.PI;	// Turn the tank around
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		Rectangle bound = getBoundingRect();
		g.fillRect(bound.x, bound.y, bound.width, bound.height);
		// g.fillRect(bound.x, bound.y, bound.width, bound.height);
		Rectangle boundingRect = getBoundingRect();
		g.drawRect(boundingRect.x, boundingRect.y, boundingRect.width, boundingRect.height);
	}

	@Override
	public void checkCollision() {
		for (int i = 0; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);
			if (this==go) continue;
			if (go instanceof Bullet) {
				checkCollision((Bullet) go);
				continue;
			}
			if (getBoundingRect().intersects(go.getBoundingRect())) {
				health -= 5;
			}
		}
	}
	
	private void checkCollision(Bullet b) {
		if (b.isOwner(this)) {
			return;
		}
		if (getBoundingRect().intersects(b.getBoundingRect())) {
			health -= 5;
		}
	}

}
