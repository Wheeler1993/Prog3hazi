import java.io.Serializable;
import java.util.Comparator;

public class Score implements Serializable {
	int score;
	String name;
	
	Score(String nam, int num){
		score=num;
		name=nam;
	}
	
	public class ScoreComparator implements Comparator<Score> {
        public int compare(Score score1, Score score2) {

            int sc1 = score1.score;
            int sc2 = score2.score;

            if (sc1 > sc2){
                return -1;
            }else if (sc1 < sc2){
                return +1;
            }else{
                return 0;
            }
        }
}
}
