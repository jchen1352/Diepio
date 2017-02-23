import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

public class DiepIOMap {

	private List<GameObject> objects;
	private Tank playerTank;

	//private static final Dimension MAP_DIMENSION = new Dimension(400,300);
	private static final int NUM_SHAPES = 100;
	private static final String BACKGROUND_IMAGE_FILE_NAME = "";
	private static final int NUM_OPPONENTS = 100;

	private Dimension panelDimension;
	private static Image backgroundImage;

	public DiepIOMap(Dimension panelDimension) {
		this.panelDimension = panelDimension;
		objects = new ArrayList<GameObject>();
		openBackgroundImage();
		addTank();
		playerTank = (Tank) objects.get(0);
		addShapes();
	}

	public void addGameObject(GameObject go) {
		objects.add(go);
	}
	
	private void addTank() {
		addGameObject(new Tank(new Location(30,30), 30, 30, this));
	}
	
	public void openBackgroundImage() {
		try {		
			URL backgroundImageURL = getClass().getResource(BACKGROUND_IMAGE_FILE_NAME);
			if (backgroundImageURL != null) {
				backgroundImage = ImageIO.read(backgroundImageURL);
				// img = img.getScaledInstance(img.getWidth(null) , img.getHeight(null), Image.SCALE_DEFAULT);
			}
		} catch (IOException e) {
			System.err.println("Could not open image ( " + BACKGROUND_IMAGE_FILE_NAME + " )");
			e.printStackTrace();
		}
	}
	
	int xStart, yStart, xEnd, yEnd;
	public void mouseMoved(MouseEvent e) {
		mouseMoved(e.getX(), e.getY());
	}
	
	public void mouseMoved(int x, int y) {
		xEnd = x;
		yEnd = y;
		xStart = (int) (playerTank.location().getX());
		yStart = (int) (playerTank.location().getY());
		playerTank.aimWeapon(Math.atan2(yEnd - yStart, xEnd - xStart));
	}

	public void tick() {
		for (GameObject go : objects) {
			go.move();
		}
		for (GameObject go : objects) {
			go.checkCollision();
			go.checkHealth();
		}

		removeFromObjects();
	}
	
	public void playerShoot() {
		addGameObject(playerTank.shoot());
	}
	
	public void playerUpdateMotion(Set<Double> directions) {
		playerTank.updateMotion(directions);
	}

	public Dimension dimensions() {
		return panelDimension;
	}

	public void removeFromObjects() {
		int i = 0;
		while (i < objects.size()) {
			if (objects.get(i).shouldRemove()) {
				objects.remove(i);
			}
			else {
				i++;
			}
		}
//		while (iter.hasNext()) {
//			GameObject gameObject = iter.next();
//			for (GameObject go : removeObjects) {
//				if (gameObject.equals(go)) {
//					iter.remove();
//					return;
//				}
//			}
//		}
	}

	public void addShapes() {
		for (int i = 0; i < NUM_SHAPES; i++) {
			switch ((int) (Math.random()*3)) {
				case 0:
					objects.add(new Shape(new Location((int) (Math.random() * panelDimension.width), (int) (Math.random() * panelDimension.height)), Type.SQUARE, this));
					break;
				case 1:
					objects.add(new Shape(new Location((int) (Math.random() * panelDimension.width), (int) (Math.random() * panelDimension.height)), Type.TRIANGLE, this));
					break;
				case 2:
					objects.add(new Shape(new Location((int) (Math.random() * panelDimension.width), (int) (Math.random() * panelDimension.height)), Type.PENTAGON, this));
					break;
			}
		}
	} 

	public List<GameObject> objects() {
		return objects;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		for (GameObject go : objects) {
			go.draw(g);
		}




		// g.translate();

		//	Hypotenuse
		g.drawLine((int)xStart, (int)yStart, (int)xEnd, (int)yEnd);

		//	Horizontal line
		g.drawLine(xStart, yStart, xEnd, yStart);

		//	Vertical line
		g.drawLine(xEnd, yStart, xEnd, yEnd);

		g.drawImage(backgroundImage, 0, 0, null);

	}

}
