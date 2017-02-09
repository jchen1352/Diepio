import java.awt.Graphics;
import java.awt.Rectangle;


public class Tank extends GameObject {

	private Weapon weapon;
	
	private boolean shoot, forward, left, reverse, right;

	public Tank() {
		weapon = new Weapon(location, direction);
	}

	public void shoot(){
		shoot = !shoot;	//	Add the not operator so that we don't have to have a method that stops shooting
		System.out.println("Shoot: " + shoot);
		// weapon.shoot(location, direction);	
	}

	public void forward() {
		forward = !forward;
		System.out.println("Forward: " + forward);
	}

	public void left() {
		left = !left;
		System.out.println("Left: " + left);
	}

	public void reverse() {
		reverse = !reverse;
		System.out.println("Reverse: " + reverse);
	}
	
	public void right() {
		right = !right;
		System.out.println("Right: " + right);
	}

	@Override
	public void checkOffScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		Rectangle rect = getBoundingRect();
		g.drawRect(rect.getBounds().x, rect.getBounds().y, (int) rect.getWidth(), (int) rect.getHeight());
		g.fillRect(rect.getBounds().x, rect.getBounds().y, (int) rect.getWidth(), (int) rect.getHeight());
		
	}

}
