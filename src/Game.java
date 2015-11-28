import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game{
	//static Boolean quit = false;
	JFrame fr;
	JFrame addsc;
	JPanel pan;
	
	JButton newGame;
	JButton highScores;
	JButton add;
	
	JTextField hscore;
	JTextField myhscore;
	JTextField getName;
	HighScores scores = new HighScores();
	int scor;
	
	Game() throws ClassNotFoundException, IOException{
		init();
		newGame=new JButton("New Game");
		newGame.addActionListener(new NewGameListener());
		pan=new JPanel();
		highScores=new JButton("Highscores");
		highScores.addActionListener(new HighScoreListener());
		pan.add(newGame);
		pan.add(highScores);
		//newGame.setSize(500, 30);
		fr.add(pan);
		fr.pack();
	}
	
	void init() throws ClassNotFoundException, IOException{
		fr = new JFrame("SpaceInvaders");
		pan=new JPanel();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);
		deserialize();
	}
	
	void newGame(){
		//fr.removeAll();
		//fr.dispose();
		fr=new JFrame("Game");
		//init();
		//pan.removeAll();
		//init();
		ArrayList<Item> items = new ArrayList<Item>();
		SpaceShip ss = null;
		try {
			//items.add(new Enemy(300, 300, -2, 0, "icon.jpg"));
			ss =  new SpaceShip(10, 300, "spaceship.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Drawer d = new Drawer(items, ss, this);
		//JFrame fr = new JFrame();
		//pan=new Drawer(items, ss);
		
		fr.addKeyListener(ss);
		//System.out.println("remove");
		fr.addKeyListener(d);
		
		fr.setBounds(0, 0, 600, 600);
		
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLayout();
		fr.add(d);

		//fr.revalidate();
		//fr.repaint();
		fr.setVisible(true);
	}
	
	void addHighScore(int score){
		//init();
		scor=score;
		addsc = new JFrame("Add");
		JPanel pann=new JPanel();
		addsc.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addsc.setVisible(true);
		myhscore=new JTextField(3);
		myhscore.setEditable(false);
		myhscore.setText(Integer.toString(score));
		//hscore=new JTextField(10);
		getName = new JTextField(10);
		add = new JButton("Add");
		add.addActionListener(new OKListener());
		pann.add(myhscore);
		pann.add(getName);
		pann.add(add);
		addsc.add(pann);
		addsc.pack();
		
	}
	
	void serialize() throws IOException{
		FileOutputStream fileOut = new FileOutputStream("scores.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(scores);
        out.close();
        fileOut.close();
	}
	
	void deserialize() throws IOException, ClassNotFoundException{
		FileInputStream fileIn = new FileInputStream("scores.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        scores = (HighScores) in.readObject();
        in.close();
        fileIn.close();
	}
	
	class OKListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			scores.addScore(getName.getText(), scor);
			scores.print();
			try {
				serialize();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			addsc.dispose();
		}
	}
	
	class HighScoreListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			scores.print();
		}
	}
	
	class NewGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			newGame();
		}
		
	}
}
