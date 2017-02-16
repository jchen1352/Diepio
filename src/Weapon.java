public class Weapon {

	private double direction; //the direction the weapon is facing
	
	private Location location;

	public Weapon(double direction) {
		this.direction = direction;

	}
	
	/**
	 * Shoots a bullet from the given location in the given direction
	 * with the given speed.
	 * @param location the location to spawn the bullet
	 * @param speed how fast the bullet will travel
	 * @return the bullet that was created
	 */
	public Bullet shoot(Location location, double speed) {
		return new Bullet(this.location, this.direction, speed, 5, 5);
	}

	public void aim(double direction) {

		this.direction = direction;
	}
	
}