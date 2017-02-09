import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MovingObjectsPanel extends JPanel {
	
	final Dimension defaultDim;// = new Dimension(800,600);
	private DiepIOMap gm;
	private Timer t;

	public MovingObjectsPanel() {
		this( new Dimension(800,600));
	}
	public MovingObjectsPanel(Dimension dim) {
		defaultDim = dim;
		gm = new DiepIOMap();
		this.setPreferredSize(defaultDim);
		makeGameMap();
		t.start();
		setUpKeyMappings();
		setUpClickListener();
	}
	private void makeGameMap() {
		gm = new DiepIOMap();
		t = new Timer(10, new ActionListener() {// fires off every 10 ms
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gm.tick();// I tell the GameMap to tick... do what
					// you do every time the clock goes off.
				repaint();// naturally, we want to see the new view
			}	
		});
	}

	private void setUpClickListener() {
		this.requestFocusInWindow();
		this.addMouseListener(new MouseListener() {

			//	If you want to detect mouse dragging, then use a mouseMotionListener
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
				clickedAt(click);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				clickRelease(arg0);
			}

		});
	}

	protected void clickedAt(MouseEvent click) {
		gm.playerShoot();
		repaint();
	}

	protected void clickRelease(MouseEvent arg0) {
		gm.playerShoot();
		repaint();
	}
	
	private void setUpKeyMappings() {
		// maps keys with actions...
		//  The code below maps a KeyStroke to an action to be performed
		// In this case I mapped the space bar key to the action named "shoot"
		// Whenever someone hits the Space Bar the action shoot is sent out

		// this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_M , 0, false),"shoot");
		// this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0, true),"stopShoot");

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W , 0, false),"forward");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true),"stopForward");

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false),"left");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true),"stopLeft");

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false),"reverse");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true),"stopReverse");

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false),"right");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true),"stopRight");
			
		// this.getActionMap().put("shoot",new AbstractAction(){
		// 	@Override
		// 	public void actionPerformed(ActionEvent e) {
		// 		// TODO Auto-generated method stub
		// 		gm.playerShoot();
		// 	}
		// });	
		// this.getActionMap().put("stopShoot",new AbstractAction(){
		// 	@Override
		// 	public void actionPerformed(ActionEvent e) {
		// 		// TODO Auto-generated method stub
		// 		gm.playerShoot();
		// 	}
		// });

		this.getActionMap().put("forward",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gm.playerForward();
			}
		});	
		this.getActionMap().put("stopForward",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// gm.playerForward();
				gm.playerForward();
			}
		});		

		this.getActionMap().put("left",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gm.playerLeft();
			}
		});		
		this.getActionMap().put("stopLeft",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gm.playerLeft();
			}
		});	

		this.getActionMap().put("reverse",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gm.playerReverse();
			}
		});		
		this.getActionMap().put("stopReverse",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gm.playerReverse();
			}
		});	

		this.getActionMap().put("right",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gm.playerRight();
			}
		});
		this.getActionMap().put("stopRight",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gm.playerRight();
			}
		});

		this.requestFocusInWindow();		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		gm.draw(g);
	}

}
