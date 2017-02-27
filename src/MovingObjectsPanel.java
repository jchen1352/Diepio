import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class MovingObjectsPanel extends JPanel {
	
	final Dimension defaultDim;// = new Dimension(800,600);
	private DiepIOMap gm;
	private Timer t;
	//This set stores the directions corresponding to key presses.
	//For example, if we press 'W', -Math.PI will be added to this set.
	private Set<Double> inputDirections;

	public MovingObjectsPanel(Dimension dim) {
		defaultDim = dim;
		System.out.println(defaultDim);
		this.setPreferredSize(defaultDim);
		
		inputDirections = new HashSet<Double>();
		makeGameMap();
		t.start();
		setupMouseMotionListener();
		setUpKeyMappings();
		setUpClickListener();
		
	}

	private void makeGameMap() {
		gm = new DiepIOMap(defaultDim);
		t = new Timer(11, new ActionListener() {// fires off every 10 ms
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isShowing()) {
					Point mousePosition = MouseInfo.getPointerInfo().getLocation();
					Point panelPosition = getLocationOnScreen();
					gm.mouseMoved(mousePosition.x - panelPosition.x, mousePosition.y - panelPosition.y);
				}
				
				gm.tick();// I tell the GameMap to tick... do what
					// you do every time the clock goes off.
				
				
				repaint();// naturally, we want to see the new view
			}	
		});
	}

	private void setupMouseMotionListener() {
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
       			gm.mouseMoved(e);
       			repaint();
    		}

    		@Override
   			public void mouseDragged(MouseEvent e) {
   				gm.mouseMoved(e);
       			repaint();
    		}
		});
	}

	private void setUpClickListener() {
		this.requestFocusInWindow();
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}
			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent click) {
				if (gm.lostGame()) {
					gm = new DiepIOMap(defaultDim);
					return;	
				}
				// gm.playerShoot();
				gm.playerShoot(true);
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				gm.playerShoot(false);
				repaint();		
			}

		});
	}
	
	private void setUpKeyMappings() {

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false),"up");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true),"stopUp");

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false),"left");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true),"stopLeft");

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false),"down");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true),"stopDown");

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false),"right");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true),"stopRight");

		this.getActionMap().put("up",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				inputDirections.add(-Math.PI/2);
				gm.playerUpdateMotion(inputDirections);
			}
		});		

		this.getActionMap().put("left",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				inputDirections.add(Math.PI);
				gm.playerUpdateMotion(inputDirections);
			}
		});		

		this.getActionMap().put("down",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				inputDirections.add(Math.PI/2);
				gm.playerUpdateMotion(inputDirections);
			}
		});		

		this.getActionMap().put("right",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				inputDirections.add(0.0);
				gm.playerUpdateMotion(inputDirections);
			}
		});
		
		this.getActionMap().put("stopUp",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				inputDirections.remove(-Math.PI/2);
				gm.playerUpdateMotion(inputDirections);
			}
		});		

		this.getActionMap().put("stopLeft",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				inputDirections.remove(Math.PI);
				gm.playerUpdateMotion(inputDirections);
			}
		});		

		this.getActionMap().put("stopDown",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				inputDirections.remove(Math.PI/2);
				gm.playerUpdateMotion(inputDirections);
			}
		});		

		this.getActionMap().put("stopRight",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				inputDirections.remove(0.0);
				gm.playerUpdateMotion(inputDirections);
			}
		});
		this.requestFocusInWindow();		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// g2.scale(1, -1);
		// g2.translate(0, -getHeight());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		gm.draw(g2);
	}

}