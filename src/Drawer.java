import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Drawer extends JPanel implements ActionListener{
	ArrayList<Item> pics;
	SpaceShip ship;
	Timer timer;
	
	public Drawer(ArrayList<Item> items, SpaceShip sh){
		pics = items;
		ship=sh;
		setBackground(new Color(255,255,255));
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
	public void actionPerformed(ActionEvent arg0) {
		for(Item pic : pics){
			pic.move();
		}
		repaint();
	}

}
