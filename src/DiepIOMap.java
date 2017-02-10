import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class DiepIOMap {

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

	int xStart, yStart, xEnd, yEnd;
	public void mouseMoved(MouseEvent e) {
		xEnd = e.getX();
		yEnd = e.getY();

		xStart = (int) (playerTank.location().getX());
		yStart = (int) (playerTank.location().getY());

//		double hypotenuseLength = Math.sqrt(((Math.pow(xEnd - xStart, 2)) + (Math.pow(yEnd - yStart, 2))));
//		// yEnd - yStart
//		double verticalLength = Math.sqrt(((Math.pow(xEnd - xEnd, 2)) + (Math.pow(yEnd - yStart, 2))));
//
//		double direction = Math.asin(verticalLength / hypotenuseLength);
		
		double direction = Math.atan2(yEnd-yStart, xEnd- xStart);

//		System.out.println("Hypotenuse: " + hypotenuseLength + " verticalLength: " + verticalLength + " Angle: " + direction);
		
		
		//	Hypotenuse
		// g.drawLine((int)xStart, (int)yStart, (int)xEnd, (int)yEnd);

		// Horizontal line
		// g.drawLine(xStart, yStart, xEnd, yStart);

		//	Vertical line

		// g.drawLine(xEnd, yStart, xEnd, yEnd);
		
		playerTank.aimWeapon(direction);
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
