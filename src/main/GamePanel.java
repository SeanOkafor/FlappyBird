package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale; // 48x48
	final int maxScreenCol = 12;
	final int maxScreenRow = 17;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	//FPS
	
	int FPS = 60;
	
	
	KeyHandler keyH = new KeyHandler();			
	Thread gameThread;
	
	int playerX = 100;
	int playerY = 350;
	int playerSpeedup = 10;

	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}


	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			// 1 Update: update information such as character positions
			update();
			 // 2 Draw: draw the screen with the updated information
			repaint();
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
	}
	public void update() {
		Timer timer = new Timer();

		if (keyH.upPressed == true) {
			playerY -= playerSpeedup;

		}
		if (keyH.downPressed == true) {

			timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					int playerSpeeddown = 0;

					if (keyH.downPressed) {
						// Increase the speed by 1
						playerSpeeddown += 1;
						// Update the player's position
						playerY += playerSpeeddown;
					}
				}
			}, 0, 100);
		}

		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.cyan);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();
	}

}
