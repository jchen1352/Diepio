
public class OpponentLogic {

	private static DiepIOMap map;
	private Tank oppponentTank;

	public OpponentLogic(DiepIOMap map, Tank oppponentTank) {
		if (this.map == null) {
			this.map = map;
		}
		this.oppponentTank = oppponentTank;
	}

	public void opponentMove() {
		GameObject closestObject = map.objects().get(0);
		double closestDistance = Math.sqrt(Math.pow(closestObject.location.getX() - oppponentTank.location.getX(), 2) + Math.pow(closestObject.location.getY() - oppponentTank.location.getY(), 2));

		for (int i = 0; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);
			
			if (go.equals(oppponentTank)) { continue; }

			double distance = Math.sqrt(Math.pow(go.location.getX() - oppponentTank.location.getX(), 2) + Math.pow(go.location.getY() - oppponentTank.location.getY(), 2));	
			if (distance < closestDistance) {
				closestDistance = distance;
				closestObject = go;
			}
		}

		double closestX = closestObject.location.getX();
		double closestY = closestObject.location.getY();

		double opponentX = oppponentTank.location.getX();
		double opponentY = oppponentTank.location.getY();

		double angle = Math.atan2(closestX - opponentY, closestY - opponentX);

		if (closestDistance < 80) {

			oppponentTank.aimWeapon( angle );
			oppponentTank.shoot();

		} else {

			oppponentTank.speed = 1;
			oppponentTank.direction = angle;
			oppponentTank.move();
		}

	}
}

