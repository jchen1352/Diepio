import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Set;

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