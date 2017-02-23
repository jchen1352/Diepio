import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Set;

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
	
	public void updateMotion(Set<Double> directions) {
		if (directions.isEmpty()) {
			speed = 0;
		}
		else {
			double dx = 0;
			double dy = 0;
			for (Double d : directions) {
				dx += Math.cos(d);
				dy += Math.sin(d);
			}
			if (Math.abs(dx)<.001 && Math.abs(dy)<.001) {
				speed = 0;
			}
			else {
				//System.out.println("dx,dy is "+dx+", "+dy);
				direction = Math.atan2(dy, dx);
				speed = 1;
			}
		}
	}

	public void up() {
		speed = 1;
		direction = -Math.PI/2;
		//this.move();
	}

	public void left() {
		speed = 1;
		direction = Math.PI;
		//this.move();
	}

	public void down() {
		speed = 1;
		direction = Math.PI/2;
		//this.move();
	}
	
	public void right() {
		speed = 1;
		direction = 0;
		//this.move();
	}
	
	public void stopUp() {
		stop();
//		if (direction == -Math.PI/2) {
//			speed = 0;
//		}
	}
	
	public void stopLeft() {
		stop();
//		if (direction == Math.PI) {
//			speed = 0;
//		}
	}
	
	public void stopDown() {
		stop();
//		if (direction == Math.PI/2) {
//			speed = 0;
//		}
	}
	
	public void stopRight() {
		stop();
//		if (direction == 0) {
//			speed = 0;
//		}
	}
	
	public void stop() {
		speed = 0;
	}

	public void aimWeapon(double direction) {
		weapon.aim(direction);
	}

	public Location location() {
		return location;
	}

	@Override
	public void checkOffScreen() {
		direction -= Math.PI;	// Turn the tank around
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
