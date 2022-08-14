package Assets;
import Main.GamePanel;

import javax.swing.JPanel;

import java.awt.*;
import java.util.Random;

public class Items extends JPanel{
    public static float ballRadius; // Ball's radius
    public final int UPDATE_RATE = 30;
    Random ra = new Random();

    public static float ballXRed; // Ball's center (x, y)
    public static float ballXGreen; // Ball's center (x, y)
    public static float ballXBlue; // Ball's center (x, y)
    public static float ballXBlack; // Ball's center (x, y)

    public static float ballSpeedYRed;
    public static float ballSpeedYGreen;
    public static float ballSpeedYBlue;
    public static float ballSpeedYBlack;

    public static float ballYRed;
    public static float ballYGreen;
    public static float ballYBlue;
    public static float ballYBlack;

    public static float sqX;
    public static float sqY;
    public static float sqSpeed;
    public static boolean yes;

    public static boolean redCaught;
    public static boolean blueCaught;
    public static boolean greenCaught;
    public static boolean blackCaught;
    public static boolean  sqCaught;

    public static boolean ballMissed;



    //Speed Order from slow to fast: Blue -> Red -> Green -> White

    public Items(){
        //If want ball to bigger or smaller, change radius
        ballRadius = 8;

        //Y location, start negative to give user some time to be prepared
        ballYRed = -400;
        ballYBlue = -200;
        ballYGreen = -100;
        ballYBlack =  -600;

        //Speed
        ballSpeedYRed = 1.2f;
        ballSpeedYBlue = 1.2f;
        ballSpeedYGreen= 1.3f;
        ballSpeedYBlack = 1;

        //X location, random
        ballXRed =  ra.nextInt(100, 900);
        ballXBlue = ra.nextInt(100, 900);
        ballXGreen = ra.nextInt(100, 900);
        ballXBlack = ra.nextInt(100, 900);

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
        g.setColor(Color.black);
        g.fillOval((int) ballXBlack, (int) ballYBlack,
                (int)(2 * ballRadius), (int)(2 * ballRadius));

        //Bomb aka square for now, if user touch this, then minus two points.
        if(GamePanel.timer > 3000) {
            yes = true;
            g.setColor(Color.orange);
            g.fillRect((int)sqX, (int)sqY, 20, 20);
        }
    }
}



