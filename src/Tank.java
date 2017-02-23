import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;
import java.awt.Color;

public class Tank extends GameObject {

	private Weapon weapon;
	private boolean isOpponent;
	private OpponentLogic opponentLogic;

	public Tank(Location location, double width, double height, DiepIOMap map, boolean isOpponent) {
		super(location, width, height, map);
		weapon = new Weapon(direction, this);
		health = 100;
		this.isOpponent = isOpponent;

		if (isOpponent) {
			this.opponentLogic = new OpponentLogic(map, this);
		}

		// System.out.println(opponentLogic);

	}

	public void shoot() {
		// return weapon.shoot(new Location(location), 5);	
		map.addGameObject(weapon.shoot(new Location(location), 5));
	}

	public boolean isOpponent() {
		return isOpponent;
	}

	public void up() {
		speed = 1;
		direction = -Math.PI/2;
		this.move();
	}

	public void left() {
		speed = 1;
		direction = Math.PI;
		this.move();
	}

	public void down() {
		speed = 1;
		direction = Math.PI/2;
		this.move();
	}
	
	public void right() {
		speed = 1;
		direction = 0;
		this.move();
	}
	
	public void stop() {
		direction -= Math.PI;
		speed = 0;
	}

	public void stopUp() {
		if (direction == -Math.PI/2) {
			speed = 0;
		}
	}
	
	public void stopLeft() {
		if (direction == Math.PI) {
			speed = 0;
		}
	}
	
	public void stopDown() {
		if (direction == Math.PI/2) {
			speed = 0;
		}
	}
	
	public void stopRight() {
		if (direction == 0) {
			speed = 0;
		}
	}

	public void aimWeapon(double direction) {
		weapon.aim(direction);
	}

	public Location location() {
		return location;
	}

	public void opponentMove() {
		opponentLogic.opponentMove();
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