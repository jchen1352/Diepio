import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


public class DiepIOMap{

	public List<GameObject> objects;
	private Tank playerTank;
	
	Image backgroundImage;
	
	public DiepIOMap() {
		objects = new ArrayList<GameObject>();
		openBackgroundImage();
		addTank();
		playerTank = (Tank) objects.get(0);
	}

	public void addGameObject(GameObject go) {
		objects.add(go);
	}
	
	private void addTank() {
		addGameObject(new Tank(new Location(30,30), 30, 30));
	}
	
	public void openBackgroundImage() {

	}
	
	public void playerShoot() {
		addGameObject(playerTank.shoot());
	}
	
	public void draw(Graphics g) {
		for (GameObject go : objects) {
			go.draw(g);
		}
	}

	public void tick() {
		for (GameObject go : objects) {
			go.move();
		}
	}

}
