package main;

import entity.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48
	final int maxScreenCol = 7;
	final int maxScreenRow = 11;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels originally
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels originally
	public  BufferedImage bg;


	//FPS
	
	int FPS = 60;
	
	
	KeyHandler keyH = new KeyHandler();			
	Thread gameThread;
	Player player = new Player(this,keyH);
	
	//Created a new variable called gravity it basically acts as the acceleration
	//I added a max speed that the object can fall at

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
		//calling on player class to consistently update the game with live inputs

		player.update();



		
	}
	public void getBackgroundImage() {

		//introducing the background into the program
		try {
			bg = ImageIO.read(getClass().getResourceAsStream("/player/background.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}



	
	public void paintComponent(Graphics g) {
		//calling on previous method of the background
		getBackgroundImage();
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(bg, 0,0, 904, 504, null); //painting the background to the screen dimensions (subject to change)


		player.draw(g2); //drawing the bird into the game

		
		g2.dispose();
	}

}
