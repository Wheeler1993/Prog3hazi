import java.io.IOException;
import java.util.ArrayList;

public class Enemy extends Item {
	Boolean shootable = false;
	int counter=0;
	public Enemy(int xx, int yy, int dirxx, int diryy, String picc) throws IOException {
		super(xx, yy, dirxx, diryy, picc);
		// TODO Auto-generated constructor stub
	}
	
	void shoot(ArrayList<Item> enemybullets) throws IOException{
		//System.out.println("rem");
		counter++;
		if(counter>100 && shootable){
			System.out.println("remove");
			enemybullets.add(new Bullet(x-10, y+5, -5, 0, "enemybullet.png"));
			counter=0;
		}
		
	}
	
}
