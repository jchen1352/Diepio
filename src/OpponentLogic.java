
public class OpponentLogic {

	private static DiepIOMap map;
	private Tank opponentTank;

	private int numShots;

	public OpponentLogic(DiepIOMap map, Tank opponentTank) {
		if (this.map == null) {
			this.map = map;
		}
		numShots = 0;
		this.opponentTank = opponentTank;
	}

	public void opponentMove() {
		GameObject closestObject = map.objects().get(0);
		double closestDistance = Math.sqrt(Math.pow(closestObject.location.getX() - opponentTank.location.getX(), 2) + Math.pow(closestObject.location.getY() - opponentTank.location.getY(), 2));

		for (int i = 0; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);

			if ((go.equals(opponentTank)) || (go instanceof Bullet)) { continue; }

			double distance = Math.sqrt(Math.pow(go.location.getX() - opponentTank.location.getX(), 2) + Math.pow(go.location.getY() - opponentTank.location.getY(), 2));	
			if (distance < closestDistance) {
				closestDistance = distance;
				closestObject = go;
			}
		}

		double closestX = closestObject.location.getX();
		double closestY = closestObject.location.getY();

		double opponentX = opponentTank.location.getX();
		double opponentY = opponentTank.location.getY();

		double angle = Math.atan2(closestY - opponentY, closestX - opponentX);

		if (closestDistance < 200 && (shouldShoot())) {
			opponentTank.aimWeapon(angle);
			opponentTank.shoot();
			numShots++;
		} else if (closestDistance > 200 && (!shouldShoot())) {
			opponentTank.speed = 1;
			opponentTank.direction = angle;
			opponentTank.move();
		
		}
	}

	private boolean shouldShoot() {
		if (numShots == (int)(Math.random() * 20)) {
			numShots = 0;
			return true;
		}
		return false;
	}

}

