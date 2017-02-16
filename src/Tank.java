import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.List;

public class Tank extends GameObject {

	private Weapon weapon;

	public Tank(Location location, double width, double height) {
		super(location, width, height);
		weapon = new Weapon(direction);
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
	public void checkCollision() {
		for (int i = 1; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);

			// if (this == go) { return; }
			if (this.getBoundingRect().getBounds().intersects(go.getBoundingRect().getBounds())) { return; }

			if (this.getBoundingRect().intersects(go.getBoundingRect())) {
				this.health -= 5;
				go.health -= 5;

				System.out.println("this.health: " + this.health);
				System.out.println("go.health: " + go.health);

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


	@Override
	public void checkOffScreen() {
		if (!location.inMap(new Rectangle(mapDimensions))) {
			direction -= Math.PI;	// Turn the tank around
		}
	}

	@Override
	public void draw(Graphics g) {
		Rectangle bound = getBoundingRect();
		g.drawRect(bound.x, bound.y, bound.width, bound.height);
		// g.fillRect(bound.x, bound.y, bound.width, bound.height);
	}

}
