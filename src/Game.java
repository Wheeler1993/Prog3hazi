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
	JFrame fr;
	JFrame addsc;
	JPanel pan;
	
	JButton newGame;
	JButton highScores;
	JButton exit;
	JButton add;
	
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
		exit = new JButton("Exit");
		exit.addActionListener(new ExitListener());
		pan.add(newGame);
		pan.add(highScores);
		pan.add(exit);
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
		fr=new JFrame("Game");
		ArrayList<Enemy> items = new ArrayList<Enemy>();
		SpaceShip ss = null;
		try {
			//items.add(new Enemy(300, 300, -2, 0, "icon.jpg"));
			ss =  new SpaceShip(10, 300, "spaceship.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Drawer d = new Drawer(items, ss, this);
		
		fr.addKeyListener(ss);
		fr.addKeyListener(d);
		
		fr.setBounds(0, 0, 600, 600);
		
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fr.add(d);

		fr.setVisible(true);
	}
	
	void addHighScore(int score){
		scor=score;
		addsc = new JFrame("Add");
		JPanel pann=new JPanel();
		addsc.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addsc.setVisible(true);
		myhscore=new JTextField(3);
		myhscore.setEditable(false);
		myhscore.setText(Integer.toString(score));
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
	class ExitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
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
