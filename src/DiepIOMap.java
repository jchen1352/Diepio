import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


public class DiepIOMap{

	

	private List<GameObject> objects;
	private Tank playerTank;
	
	Image backgroundImage;
	
	public DiepIOMap() {
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
	
	public void playerShoot() {
		playerTank.shoot();
	}

}
