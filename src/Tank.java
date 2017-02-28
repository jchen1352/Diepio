import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Set;

import javax.swing.JOptionPane;

public class Tank extends GameObject {

	private Weapon weapon;
	private boolean isOpponent;
	private OpponentLogic opponentLogic;
	private int numKills;

	
	public Tank(Location location, double width, double height, DiepIOMap map, boolean isOpponent) {
		super(location, width, height, map);
		weapon = new Weapon(direction, this);
		health = 100;
		this.isOpponent = isOpponent;
		numKills = 0;

		if (isOpponent) {
			this.opponentLogic = new OpponentLogic(map, this);
			weapon.setFiringStatus(true);
		}

		// System.out.println(opponentLogic);

	}

	public void shoot() {
		// return weapon.shoot(new Location(location), 5);
		if (weapon.readyToFire()) {
			map.addGameObject(weapon.shoot(new Location(location), getBulletSpeed()));
			weapon.resetCooldown();
		}
	}

	public void incNumKills() {
		numKills++;
	}

	public double getBulletSpeed(){
		return 5 + (int)numKills/5;
	}
	

	public void updateShooting(boolean shouldShoot) {
		weapon.setFiringStatus(shouldShoot);
	}

	public boolean isOpponent() {
		return isOpponent;
	}

	public void updateMotion(Set<Double> directions) {
		if (directions.isEmpty()) {
			speed = 0;
		} else {
			double dx = 0;
			double dy = 0;
			for (Double d : directions) {
				dx += Math.cos(d);
				dy += Math.sin(d);
			}
			if (Math.abs(dx) < .001 && Math.abs(dy) < .001) {
				speed = 0;
			} else {
				// System.out.println("dx,dy is "+dx+", "+dy);
				direction = Math.atan2(dy, dx);
				speed = 1;
//				System.out.println("Dy: " + dy + " dx: " + dx);
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
		direction -= Math.PI; // Turn the tank around
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		Rectangle bound = getBoundingRect();
		// g.drawRect(bound.x, bound.y, bound.width, bound.height);
		if (!isOpponent) {
			g.setColor(Color.CYAN);
		}
		g.fillRect(bound.x, bound.y, bound.width, bound.height);

		g.setColor(Color.GREEN);
		g.fillRect(bound.x, bound.y + 31, (int) (health) / 3, 4);
		// g.drawRect(bound.x, bound.y + 32, bound.width, 5);

	}

	@Override
	public void checkCollision() {
		for (int i = 0; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);
			if (this == go)
				continue;
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
			if (health <= 0) {
				b.getOwner().incNumKills();
				if (!b.getOwner().isOpponent)
					System.out.println("MY NUMBER KILLS IS: " + b.getOwner().numKills);
			}
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
		weapon.tickCooldown();
	}

}