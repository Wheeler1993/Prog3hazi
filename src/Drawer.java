import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Drawer extends JPanel implements ActionListener, KeyListener{
	ArrayList<Item> pics;
	SpaceShip ship;
	//ArrayList<Bullet> bullets;
	Timer timer;
	
	public Drawer(ArrayList<Item> items, SpaceShip sh){
		pics = items;
		ship=sh;
		//bullets = new ArrayList<Bullet>();
		setBackground(new Color(255,255,255));
		//addKeyListener(new SpaceListener());
		timer=new Timer(16, this);
		timer.start();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Item pic : pics){
			g.drawImage(pic.pic, pic.x, pic.y, this);
			//System.out.println(pic.x);
			//System.out.println(pic.y);
		}
		g.drawImage(ship.pic, ship.x, ship.y, this);
		
	}


		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyChar()){
			case ' ': {
					try {
						pics.add(new Bullet(ship.x+10, ship.y+10, 5, 0, "bullet.gif"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println("Space");
				}break;
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(Item pic : pics){
			pic.move();
		}
		ArrayList<Item> toremove = new ArrayList<Item>();
		for(Item i : pics){
			if(i.x>610 || i.y>610 || i.x<-10 || i.y<-10){
				toremove.add(i);
				//System.out.println("remove");
			}
		}
		pics.removeAll(toremove);
		repaint();
	}

}
