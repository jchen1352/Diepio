import java.awt.Graphics;


public class Tank extends GameObject {

	private Weapon weapon;
	
	public Tank(Location location, double width, double height) {
		super(location, width, height);
		weapon = new Weapon(direction);
	}
	
	public Bullet shoot(){
		return weapon.shoot(new Location(location), direction, 5);
	}
	
	
	@Override
	public void checkOffScreen() {

	}


	@Override
	public void draw(Graphics g) {
		g.fillRect((int)location.getX(), (int)location.getY(), (int)width, (int)height);
	}

}
