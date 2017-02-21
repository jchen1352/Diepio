
public class OpponentLogic {

	private static DiepIOMap map;

	public OpponentLogic() {
		if (map == null) {
			map = new DiepIOMap();
		}
	}

	public void moveTank(Tank tank) {
		GameObject closestObject = (Tank) (map.objects().get(0));
		int closestDistance = -1;

		for (GameObject go : map.objects()) {

			if (go.equals(tank)) { continue; }

			int distance = (int)(Math.sqrt(Math.pow((int)(tank.location.getX() - go.location.getX()), 2) + Math.pow((int)(tank.location.getY() - go.location.getY()), 2)));

			if (distance == -1) {
				closestDistance = distance;
			} else {
				if (closestDistance < distance) {
					closestDistance = distance;
					closestObject = go;
				}
			}
		}

		// playerTank.aimWeapon(Math.atan2(yEnd - yStart, xEnd - xStart));

		double tankX = tank.location.getX();
		double tankY = tank.location.getY();

		double goX = closestObject.location.getX();
		double goY = closestObject.location.getY();

		double direction = Math.atan2(goY - tankY, goX - tankX);

		if (closestDistance < 400) {
			tank.aimWeapon(direction);
			tank.shoot();
		} else {
			tank.speed = 1;
			tank.direction = direction;
			tank.move();
		}
	}


}