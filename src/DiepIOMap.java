import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.util.Iterator;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.geom.AffineTransform;
import java.awt.Color;

public class DiepIOMap {

	private static List<GameObject> objects;
	private static List<GameObject> removeObjects;
	private static Tank playerTank;

	private static final Dimension MAP_DIMENSION = new Dimension(4000,3000);
	private static final int NUM_SHAPES = 450;
	private static final String BACKGROUND_IMAGE_FILE_NAME = "";
	private static final int NUM_OPPONENTS = 100;

	private static Dimension panelDimension;
	private static Image backgroundImage;

	public DiepIOMap(Dimension panelDimension) {
		if (objects == null) {
			objects = new ArrayList<GameObject>();
			openBackgroundImage();
			addTank();
			playerTank = (Tank) objects.get(0);
			removeObjects = new ArrayList<>();
			addShapes();
			this.panelDimension = panelDimension;
		}
	}

	public DiepIOMap() {
		if (objects == null) {
			objects = new ArrayList<GameObject>();
			openBackgroundImage();
			addTank();
			playerTank = (Tank) objects.get(0);
			removeObjects = new ArrayList<>();
			addShapes();
		}
	}

	public void addGameObject(GameObject go) {
		objects.add(go);
	}
	
	private void addTank() {
		addGameObject(new Tank(new Location(30,30), 30, 30));
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

		removeFromObjects();
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

	public void removeFromObjects() {
		if (removeObjects.size() <= 0) { return; } 
		Iterator<GameObject> iter = objects.iterator();
		while (iter.hasNext()) {
			GameObject gameObject = iter.next();
			for (GameObject go : removeObjects) {
				if (gameObject.equals(go)) {
					iter.remove();
					return;
				}
			}
		}
	}

	public void addToRemoveObjects(GameObject go) {
		removeObjects.add(go);
	}

	public void addShapes() {
		for (int i = 0; i < NUM_SHAPES; i++) {
			switch ((int) (Math.random() * 3)) {
				case 0:
					objects.add(new Shape(new Location((int) (Math.random() * MAP_DIMENSION.width), (int) (Math.random() * MAP_DIMENSION.height)), Type.SQUARE));
					break;
				case 1:
					objects.add(new Shape(new Location((int) (Math.random() * MAP_DIMENSION.width), (int) (Math.random() * MAP_DIMENSION.height)), Type.TRIANGLE));
					break;
				case 2:
					objects.add(new Shape(new Location((int) (Math.random() * MAP_DIMENSION.width), (int) (Math.random() * MAP_DIMENSION.height)), Type.PENTAGON));
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
