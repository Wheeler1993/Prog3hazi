
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;


public class Item /*implements ActionListener*/{
	BufferedImage pic;
	int x, y;
	int dirx, diry;
	
	public Item(int xx, int yy, int dirxx, int diryy, String picc) throws IOException{
	    x=xx; y=yy; dirx=dirxx; diry= diryy;
		
	    pic=ImageIO.read(new File(picc));
		
	}
	
	void move(){
		x+=dirx;
		y+=diry;
	}
	
	/*@Override
	public void actionPerformed(ActionEvent arg0) {
		x+=dirx;
		y+=diry;
		//System.out.println(x);
	}*/
	
}
