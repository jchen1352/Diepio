public class Weapon {

	private double direction; //the direction the weapon is facing
	private Location location;
	private Tank owner;
	
	public Weapon(double direction, Tank owner) {
		this.direction = direction;
		this.owner = owner;
	}
	
	/**
	 * Shoots a bullet from the given location in the given direction
	 * with the given speed.
	 * @param location the location to spawn the bullet
	 * @param speed how fast the bullet will travel
	 * @return the bullet that was created
	 */
	public Bullet shoot(Location location, double speed) {
		return new Bullet(location, this.direction, speed, 5, 5, owner, owner.getMap());
	}

	public void aim(double direction) {
		this.direction = direction;
	}
	
}