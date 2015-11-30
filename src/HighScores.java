import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HighScores implements Serializable {
	ArrayList<Score> scores = new ArrayList<Score>();
	
	
	public void addScore(String name, int score) {
        //loadScoreFile();
        scores.add(new Score(name, score));
       // updateScoreFile();
	}
	
	void print(){
		sort();
		JFrame fr = new JFrame("Scores");
		JPanel pan = new JPanel();
		fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		fr.setVisible(true);
		int j=1;
		for(Score i : scores){
			JTextField num = new JTextField(2);
			JTextField name = new JTextField(8);
			//JTextField score = new JTextField(3);
			name.setText(i.name+": "+Integer.toString(i.score));
			//score.setText(Integer.toString(i.score));
			num.setText(Integer.toString(j++));
			num.setEditable(false);
			pan.add(num);
			pan.add(name);
			//pan.add(score);
		}
		fr.add(pan);
		fr.pack();
	}
	
	private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
}
	
}
