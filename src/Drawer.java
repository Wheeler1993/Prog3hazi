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
import java.lang.Math;

public class Drawer extends JPanel implements ActionListener, KeyListener{
	ArrayList<Item> enemys;
	ArrayList<Item> bullets;
	SpaceShip ship;
	Timer timer;
	double n=100, i;
	
	public Drawer(ArrayList<Item> items, SpaceShip sh){
		enemys = items;
		ship=sh;
		bullets = new ArrayList<Item>();
		setBackground(new Color(0,255,255));
		timer=new Timer(16, this);
		timer.start();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Item pic : enemys){
			g.drawImage(pic.pic, pic.x, pic.y, this);
			//System.out.println(pic.x);
			//System.out.println(pic.y);
		}
		for(Item pic : bullets){
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
						bullets.add(new Bullet(ship.x+10, ship.y+10, 5, 0, "bullet.gif"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println("Space");
				}break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {}
		
	void dropEnemys() throws IOException{
		//System.out.println("remove");
			int yy= (int) (Math.random()*600);
			Enemy en = new Enemy(600, yy, -2, 0, "icon.jpg");
			enemys.add(en);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		++i;
		if(i>n){
			n-=1;
		try {
			dropEnemys();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		i=Math.random()*20;
		}
		for(Item pic : bullets){
			pic.move();
		}
		for(Item pic : enemys){
			pic.move();
		}
		ArrayList<Item> toremovebullets = new ArrayList<Item>();
		ArrayList<Item> toremoveenemys = new ArrayList<Item>();
		for(Item i : enemys){
			if(i.x>610 || i.y>610 || i.x<-10 || i.y<-10){
				toremoveenemys.add(i);
			}
		}
		for(Item i : bullets){
			for(Item j : enemys){
			if(j.x>610 || j.y>610 || j.x<-20 || j.y<-20){
				toremovebullets.add(i);}
			else if(i.x>610 || i.y>610 || i.x<-10 || i.y<-10){
				toremoveenemys.add(i);
			}
			else if(Math.abs(j.x-i.x)<20 && Math.abs(j.y+10-i.y)<30){
				toremovebullets.add(i);
				toremoveenemys.add(j);
			}
				//System.out.println("remove");
			}
			
		}
		bullets.removeAll(toremovebullets);
		enemys.removeAll(toremoveenemys);
		repaint();
	}

}
