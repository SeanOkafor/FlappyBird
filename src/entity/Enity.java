package entity;

import java.awt.image.BufferedImage;

public class Enity {

    //player variables all kept here in one place

    public int x, y;
    int playerSpeedup = 9;
    double playerSpeedDown = 1;
    //Created a new variable called gravity it basically acts as the acceleration
    double gravity = 0.3;
    //I added a max speed that the object can fall at
    double MAXSPEED = 11;
    
    public BufferedImage birdie;
    
    public String direction;

}
