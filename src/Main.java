
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) throws IOException{
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Enemy(300, 300, -2, 0, "icon.jpg"));
		
		SpaceShip ss =  new SpaceShip(10, 300, "spaceship.png");
		
		Drawer d = new Drawer(items, ss);
		JFrame fr = new JFrame();
		
		fr.addKeyListener(ss);
		fr.addKeyListener(d);
		
		fr.setBounds(0, 0, 600, 600);
		
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.add(d);

		fr.setVisible(true);
	}
}
