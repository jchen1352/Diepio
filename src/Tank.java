import java.awt.Graphics;


public class Tank extends GameObject {

	private Weapon weapon;
	
	public void shoot(){
		weapon.shoot(location, direction);	
	}
	
	
	@Override
	public void checkOffScreen() {
		// TODO Auto-generated method stub

	}


	@Override
	public void draw(Graphics g) {
		
	}

}
