public class OpponentLogic {

	private DiepIOMap map;
	private Tank opponentTank;

	private int numShots;

	public OpponentLogic(DiepIOMap map, Tank opponentTank) {
		this.map = map;
		this.numShots = 0;
		this.opponentTank = opponentTank;
	}

	public void opponentMove() {
		GameObject closestObject = map.objects().get(0);
		double closestDistance = opponentTank.location.distanceTo(closestObject.location);

		for (int i = 0; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);

			if ((go.equals(opponentTank)) || (go instanceof Bullet)) { continue; }

			double distance = opponentTank.location.distanceTo(go.location);
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
		} else if (closestDistance > 200) {
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