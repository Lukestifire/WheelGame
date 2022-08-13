package Assets;
import Main.GamePanel;
import Main.Main;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.text.Position;

import java.awt.*;
import java.util.Random;

public class Items extends JPanel{
    public static float ballRadius; // Ball's radius
    public final int UPDATE_RATE = 30;
    Random ra = new Random();

    public static float ballXRed; // Ball's center (x, y)
    public static float ballXGreen; // Ball's center (x, y)
    public static float ballXBlue; // Ball's center (x, y)
    public static float ballXWhite; // Ball's center (x, y)

    public static float ballSpeedYRed;
    public static float ballSpeedYGreen;
    public static float ballSpeedYBlue;
    public static float ballSpeedYWhite;

    public static float ballYRed;
    public static float ballYGreen;
    public static float ballYBlue;
    public static float ballYWhite;

    public static float sqX;
    public static float sqY;
    public static float sqSpeed;
    public static boolean yes;

    public static boolean redCaught;
    public static boolean blueCaught;
    public static boolean greenCaught;
    public static boolean whiteCaught;


    //Speed Order from slow to fast: Blue -> Red -> Green -> White

    public Items(){
        //If want ball to bigger or smaller, change radius
        ballRadius = 8;

        //Y location, start negative to give user some time to be prepared
        ballYRed = -400;
        ballYBlue = -800;
        ballYGreen = -1000;
        ballYWhite =  -650;

        //Speed
        ballSpeedYRed = 2;
        ballSpeedYBlue = 1;
        ballSpeedYGreen= 2.5f;
        ballSpeedYWhite = 2.7f;

        //X location, random
        ballXRed =  ra.nextInt(100, 900);
        ballXBlue = ra.nextInt(100, 900);
        ballXGreen = ra.nextInt(100, 900);
        ballXWhite = ra.nextInt(100, 900);

        //square
        sqX = ra.nextInt(100, 900);
        sqY = -100;
        sqSpeed = 1;
        yes = false;



    }
    public static void paint(Graphics2D g) {

        //Blue ball
        g.setColor(Color.BLUE);
        g.fillOval((int)ballXBlue, (int) ballYBlue,
                (int)(2 * ballRadius), (int)(2 * ballRadius));

        //Red ball
        g.setColor(Color.RED);
        g.fillOval((int)ballXRed, (int) ballYRed ,
                (int)(2 * ballRadius), (int)(2 * ballRadius));

        //Green ball
        g.setColor(Color.GREEN);
        g.fillOval((int)ballXGreen, (int) ballYGreen,
                (int)(2 * ballRadius), (int)(2 * ballRadius));

        //White ball
        g.setColor(Color.WHITE);
        g.fillOval((int)ballXWhite, (int) ballYWhite,
                (int)(2 * ballRadius), (int)(2 * ballRadius));

        //Bomb aka square for now, if user touch this, then minus two points.
        if(GamePanel.timer > 3000) {
            yes = true;
            g.setColor(Color.orange);
            g.fillRect((int)sqX, (int)sqY, 20, 20);
        }
    }
}


















/*


package Assets;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Items {
    protected int itemID;
    protected boolean isCaught = false;
    protected int x = ((GamePanel.SCREEN_WIDTH - GamePanel.SCORE_BOARD_WIDTH * 2) /2);
    protected int y = 200;
    private int speed;
    private int angle;

    public BufferedImage item;

    public int getXPosition() {
        return x;
    }
    public int getYPosition() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    //--------------setter methods ----------
    public void setXposition(int x) {
        this.x = x;
    }

    public void setYposition(int y) {
        this.y = y;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public void setIsCaught(boolean isCaught) {
        this.isCaught = isCaught;
    }

    // ------------ getter methods ---------
    public int getXposition() {
        return this.x;
    }
    public int getYposition() {
        return this.y;
    }

    public int getItemID() {
        return itemID;
    }
    public boolean getIsCaught() {
        return isCaught;
    }


    public void update() {
        y += y + getSpeed();
    }



    public void draw(Graphics2D g2) {
    }
}
*/