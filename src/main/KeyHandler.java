package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed;
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_SPACE) {
			upPressed = true;
			downPressed = false;
		}

	}
	@Override
	public void keyReleased(KeyEvent e) {
 
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_SPACE) {
			upPressed = false;
			downPressed = true;

		}

		
	}

}
