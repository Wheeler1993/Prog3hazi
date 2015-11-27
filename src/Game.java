import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Game{
	static Boolean quit = false;
	JFrame fr;
	JPanel pan;
	JButton newGame;
	JButton highScores;
	
	Game(){
		init();
		//newGame();
		newGame=new JButton("New Game");
		newGame.addActionListener(new NewGame());
		pan=new JPanel();
		highScores=new JButton("Highscores");
		pan.add(newGame);
		pan.add(highScores);
		//newGame.setSize(500, 30);
		fr.add(pan);
	}
	
	void init(){
		fr = new JFrame("SpaceInvaders");
		pan=new JPanel();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);
		fr.setSize(600, 600);
	}
	
	void newGame(){
		//fr.removeAll();
		fr=new JFrame("awdaw");
		//init();
		//pan.removeAll();
		//init();
		ArrayList<Item> items = new ArrayList<Item>();
		SpaceShip ss = null;
		try {
			items.add(new Enemy(300, 300, -2, 0, "icon.jpg"));
			ss =  new SpaceShip(10, 300, "spaceship.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Drawer d = new Drawer(items, ss);
		//JFrame fr = new JFrame();
		//pan=new Drawer(items, ss);
		
		fr.addKeyListener(ss);
		//System.out.println("remove");
		fr.addKeyListener(d);
		
		fr.setBounds(0, 0, 600, 600);
		
		//fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLayout();
		fr.add(d);

		//fr.revalidate();
		//fr.repaint();
		fr.setVisible(true);
		

	}
	
	class NewGame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			newGame();
		}
		
	}
}
