public class Weapon {

	private double direction; //the direction the weapon is facing
	private Location location;
	private Tank owner;
	private double maxCooldown;
	private double currentCooldown;
	private boolean isFiring;
	
	public Weapon(double direction, Tank owner) {
		this.direction = direction;
		this.owner = owner;
		this.maxCooldown = 10;
		this.currentCooldown = maxCooldown;
		this.isFiring = false;
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
	
	public void tickCooldown() {
		if (currentCooldown > 0) {
			currentCooldown--;
		}
	}
	
	public double getCooldown() {
		return currentCooldown;
	}
	
	public void resetCooldown() {
		currentCooldown = maxCooldown;
	}
	
	public boolean readyToFire() {
		return currentCooldown == 0 && isFiring;
	}
	
	public boolean getFiringStatus() {
		return isFiring;
	}
	
	public void setFiringStatus(boolean shouldFire) {
		isFiring = shouldFire;
	}
	
}