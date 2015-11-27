import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpaceShip implements KeyListener {
	int x, y;
	BufferedImage pic;
	public SpaceShip(int xx, int yy, String picc) throws IOException{
		x=xx; y=yy;
		pic=ImageIO.read(new File(picc));
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()){
		case 'a': x-=20; break;
		case 'd': x+=20;break;
		case 'w': y-=20;break;
		case 's': y+=20;break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*@Override
	public void actionPerformed(ActionEvent arg0){
		
	}*/
}
