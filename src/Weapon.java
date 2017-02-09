public class Weapon {

	private double direction; //the direction the weapon is facing
	
	public Weapon(double direction) {
		this.direction = direction;
	}
	
	/**
	 * Shoots a bullet from the given location in the given direction
	 * with the given speed.
	 * @param location the location to spawn the bullet
	 * @param direction the direction the bullet will travel
	 * @param speed how fast the bullet will travel
	 * @return the bullet that was created
	 */
	public Bullet shoot(Location location, double direction, double speed) {
		return new Bullet(location, direction, speed, 5, 5);
	}
	
}
