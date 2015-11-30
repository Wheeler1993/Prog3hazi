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
	ArrayList<Enemy> enemys;
	ArrayList<Item> bullets;
	ArrayList<Item> enemybullets;
	SpaceShip ship;
	Timer timer;
	double n=100, i;
	int shooted=0;
	Game fr;
	//JFrame frr = new JFrame();
	
	public Drawer(ArrayList<Enemy> items, SpaceShip sh, Game f){
		enemys = items;
		ship=sh;
		fr=f;
		bullets = new ArrayList<Item>();
		enemybullets = new ArrayList<Item>();
		setBackground(new Color(255,255,255));
		timer=new Timer(16, this);
		timer.start();
		//fr.add(this);
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
		for(Item i : enemybullets){
			//System.out.println("rem");
			g.drawImage(i.pic, i.x, i.y, this);
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
			int yy= (int) (Math.random()*550);
			Enemy en = new Enemy(600, yy, -2, 0, "icon.jpg");
			if(Math.random()>0.8){en.shootable=true;System.out.println("remo");}
			enemys.add(en);
	}
	
	void step(){
		for(Item pic : bullets){
			pic.move();
		}
		for(Item pic : enemys){
			pic.move();
		}
		for(Item i : enemybullets){
			i.move();
		}
	}
	
	void deleteItems(){
		ArrayList<Item> toremovebullets = new ArrayList<Item>();
		ArrayList<Item> toremoveenemys = new ArrayList<Item>();
		ArrayList<Item> toremoveenemybullets = new ArrayList<Item>();
		for(Item i : bullets){
			if(i.x>610 || i.y>610){
				toremovebullets.add(i);
				//System.out.println("remove");
			}
			for(Item j : enemys){
			if(Math.abs(j.x-i.x)<20 && Math.abs(j.y+10-i.y)<30){
				toremovebullets.add(i);
				toremoveenemys.add(j);
				shooted++;
			}
				//System.out.println("remove");
			}
			
		}
		for(Item i : enemybullets){
			if(i.x<-10){
				toremoveenemybullets.add(i);
			}
			for(Item j : bullets){
				if(Math.abs(j.x-i.x)<20 && Math.abs(j.y+10-i.y)<30){
					toremoveenemybullets.add(i);
					toremovebullets.add(j);
				}
			}
		}
		enemybullets.removeAll(toremoveenemybullets);
		bullets.removeAll(toremovebullets);
		enemys.removeAll(toremoveenemys);
	}
	void shoot() throws IOException{
		for(Enemy i : enemys){
			i.shoot(enemybullets);
		}
	}
	void checkGameOver(){
		for(Item j : enemys){
			if(Math.abs(j.x-ship.x)<30 && Math.abs(j.y-ship.y)<30 || j.x<-20 || j.y<-20){
				//System.out.println("remove");
				//System.exit(0);
				//fr.dispatchEvent(new WindowEvent(fr, WindowEvent.WINDOW_CLOSING));
				fr.addHighScore(shooted);
				timer.stop();
				fr.fr.dispose();
			}
		}
		for(Item j : enemybullets){
			if(Math.abs(j.x-ship.x)<30 && Math.abs(j.y-20-ship.y)<30 || j.x<-20 || j.y<-20){
				//System.out.println("remove");
				//System.exit(0);
				//fr.dispatchEvent(new WindowEvent(fr, WindowEvent.WINDOW_CLOSING));
				fr.addHighScore(shooted);
				timer.stop();
				fr.fr.dispose();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		++i;
		try {
			if(i>n){
				if(n>40){
				n-=1;
				}
			dropEnemys();
			i=Math.random()*20;
		}
			
			shoot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		step();
		deleteItems();
		checkGameOver();
		repaint();
	}

}
