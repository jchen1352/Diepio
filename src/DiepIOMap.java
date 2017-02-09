import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;


public class DiepIOMap {

	private List<GameObject> objects;
	private Tank playerTank;
	
	Image backgroundImage;
	
	public DiepIOMap() {
		playerTank = new Tank();
		objects = new ArrayList<GameObject>();
		openBackgroundImage();
		addTank();
	}

	public void addGameObject(GameObject go) {
		objects.add(go);
	}
	
	private void addTank() {
		this.addGameObject(new Tank());
		
	}
	
	public void openBackgroundImage() {
		// TODO Auto-generated method stub
	}

	public void tick() {

	}
	
	public void playerShoot() {
		playerTank.shoot();
	}

	public void playerLeft() {
		playerTank.left();
	}

	public void playerRight() {
		playerTank.right();
	}

	public void playerForward() {
		playerTank.forward();
	}

	public void playerReverse() {
		playerTank.reverse();
	}

	public void draw(Graphics g) {
		// playerTank.draw(g);

		// for (GameObject go : objects) {
		// 	go.draw(g);
		// }
	}

}
