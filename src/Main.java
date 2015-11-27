
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) throws IOException{
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Item(300, 300, -2, 0, "icon.jpg"));
		SpaceShip ss =  new SpaceShip(10, 300, "spaceship.png");
		Drawer d = new Drawer(items, ss);
		//i.setLayout(null);
		//i.setBounds(300-25, 300-25, 50, 50);
		//i.repaint();
		//System.out.println(i.getLocation());
		JFrame fr = new JFrame();
		fr.addKeyListener(ss);
		//fr.setBackground(new Color(1,0,1));
		//fr.setLayout(new BorderLayout());
		//fr.getContentPane().setLayout(null);
		fr.setBounds(0, 0, 600, 600);
		/*JPanel p = new JPanel();
		fr.setContentPane(p);
		fr.getContentPane().setLayout(null);*/
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//fr.setSize(600, 600);
		fr.add(d);
		//fr.add(s);
		fr.setVisible(true);
		//fr.pack();
	}
}
