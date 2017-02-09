import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class MovingObjectsPanel extends JPanel {
	
	final Dimension defaultDim;// = new Dimension(800,600);
	DiepIOMap gm;
	
	private Timer timer;
	
	public MovingObjectsPanel() {
		this( new Dimension(800,600));
	}
	public MovingObjectsPanel(Dimension dim) {
		defaultDim = dim;
		this.setPreferredSize(defaultDim);
		makeGameMap();
		setupTimer();
		setUpKeyMappings();
	}
	
	private void setupTimer() {
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tick();
				repaint();
			}
		});
		timer.start();
	}
	
	private void makeGameMap() {
		gm = new DiepIOMap();
	}
	
	private void setUpKeyMappings() {
		// maps keys with actions...
		//  The code below maps a KeyStroke to an action to be performed
		// In this case I mapped the space bar key to the action named "shoot"
		// Whenever someone hits the Space Bar the action shoot is sent out

		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"shoot");

		//  This associates the command shoot with some action.  In this 
		// case, the action triggers a shoot command invoked on my GameMap.  In general, whatever 
		// goes in the actionPerformed method will be executed when a shoot command
		// is sent...
			
		this.getActionMap().put("shoot",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.playerShoot();
				for (int i = 0; i < gm.objects.size(); i++) {
					GameObject go = gm.objects.get(i);
					System.out.println(i+"- speed="+go.speed+", location="+go.location);
					if (go instanceof Bullet) {
						System.out.println("is a bullet");
					}
					if (go instanceof Tank) {
						System.out.println("is a tank");
					}
				}
				System.out.println("shooting");
			}
		});
		this.requestFocusInWindow();		
	}
	
	public void tick() {
		gm.tick();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gm.draw(g);
	}
	
}
