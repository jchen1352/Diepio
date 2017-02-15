import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.util.Iterator;

public class DiepIOMap {

	private static List<GameObject> objects;
	private static Tank playerTank;
	private static final Dimension MAP_DIMENSION = new Dimension(4000,3000);

	Image backgroundImage;
	
	public DiepIOMap() {
		if (objects == null) {
			objects = new ArrayList<GameObject>();
			openBackgroundImage();
			addTank();
			playerTank = (Tank) objects.get(0);
		}
	}

	public void addGameObject(GameObject go) {
		objects.add(go);
	}
	
	private void addTank() {
		addGameObject(new Tank(new Location(30,30), 30, 30));
	}
	
	public void openBackgroundImage() {

	}
	
	int xStart, yStart, xEnd, yEnd;
	public void mouseMoved(MouseEvent e) {
		xEnd = e.getX();
		yEnd = e.getY();

		xStart = (int) (playerTank.location().getX());
		yStart = (int) (playerTank.location().getY());
						
		playerTank.aimWeapon(Math.atan2(yEnd - yStart, xEnd - xStart));
	}

	public void tick() {
		for (GameObject go : objects) {
			go.move();
		}
	}
	
	public void playerShoot() {
		addGameObject(playerTank.shoot());
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

	public Dimension dimensions() {
		return MAP_DIMENSION;
	}

	public void removeFromObjects(GameObject go) {
		Iterator<GameObject> iter = objects.iterator();
		while (iter.hasNext()) {
			GameObject gameObject = iter.next();
			if (gameObject.equals(go)) {
				iter.remove();
			}
		}
		// objects.remgove(go);
	}

	public void draw(Graphics g) {
		for (GameObject go : objects) {
			go.draw(g);
		}

		//	Hypotenuse
		g.drawLine((int)xStart, (int)yStart, (int)xEnd, (int)yEnd);

		//	Horizontal line
		g.drawLine(xStart, yStart, xEnd, yStart);

		//	Vertical line
		g.drawLine(xEnd, yStart, xEnd, yEnd);

	}

}
