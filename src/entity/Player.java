package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends Enity{

    GamePanel gp;
    KeyHandler keyH;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        x = 100; // same values as "playerX and Y, just moved here for simplicities sake
        y = 100;
        direction = "bird";
    }
    public void getPlayerImage() {
    	
    	try {
    		//introducing the bird from the file into the program
    		birdie = ImageIO.read(getClass().getResourceAsStream("/player/birdie.png"));
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    		
    	}
    }
    public void update() {

        if (keyH.upPressed) {
            y -= playerSpeedup;
            direction = "bird";
            playerSpeedDown = 1;

        }
        //so I added these lines below so while downpressed is true and the speed is less than the max gravity will occur
        if (keyH.downPressed && playerSpeedDown < MAXSPEED) {
            playerSpeedDown += gravity;
        }
        //this basically acts as a floor at Y level 600
        if (keyH.downPressed) {
            if (y < 400) {
                y += playerSpeedDown;
            }


        }
    }
    public void draw(Graphics2D g2) {
    	
    	BufferedImage image = null;
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    	switch(direction) {
    	case "bird":
    		image = birdie;
    			break;
    		
    	}
    	
    
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }   



}
