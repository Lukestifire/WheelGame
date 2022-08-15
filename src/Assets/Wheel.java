package Assets;

import Main.GamePanel;

import java.awt.*;

//------------------------------- WHEEL -----------------------------------
/*
    The Wheel class handles the control and image of the wheel.
    -It keeps track of the x position
    -It draws all four segments, sets their color and records their position
    -It initially sets the wheel with the green segment in the up position and
    the center of the wheel in the center of the screen

*/



public class Wheel {
    // ---------------- Wheel Characteristics -----------------------------
    public static int wheelPosition;   // Keeps track of x position of wheel on screen
    public static int wheelAngle;      // Keeps track of theta -- angle of the wheel on screen
    public static int theta = 90;           // Angle in degrees each direction change spins the wheel
    public static String segmentUp = "";  // Which wheel segment is in the catch item position

    // Controls overall size of wheel
    public static final int wheelSizeX = GamePanel.UNIT_SIZE * 7;
    public static final int wheelSizeY = GamePanel.UNIT_SIZE * 7;




    // --------------------- Constructor ---------------------------------
    // Places the wheel in the center of the screen at the beginning of the game

    public Wheel() {
        wheelPosition = (GamePanel.SCREEN_WIDTH/2) - (wheelSizeX/2); // Start in center of screen
    }




    // ------------------------Display ------------------------------------
    // Draws the wheel and its components
    public static void draw(Graphics2D g2) {

        if (Math.abs(wheelAngle) == 360) {
            wheelAngle = 0;
        }

        if (wheelPosition + (wheelSizeX/2) >= GamePanel.SCREEN_WIDTH)
        {
            wheelPosition = 0;
        }

        else if (wheelPosition + (wheelSizeX/2) < 0 ) {
            wheelPosition = GamePanel.SCREEN_WIDTH - (wheelSizeX/2);
        }

        // Draws segment 1
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.black);
        g2.drawArc(wheelPosition, GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),
                wheelSizeX, wheelSizeY, wheelAngle + 45 ,90);
        g2.setColor(Color.green);
        g2.fillArc(wheelPosition, GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),
                wheelSizeX, wheelSizeY, wheelAngle + 45 ,90);


        // Draws Segment 2
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.black);
        g2.drawArc(wheelPosition, GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),
                wheelSizeX, wheelSizeY, wheelAngle + 135 ,90);
        g2.setColor(Color.black);
        g2.fillArc(wheelPosition, GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),
                wheelSizeX, wheelSizeY, wheelAngle + 135,90);


        // Draws Segment 3
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.black);
        g2.drawArc(wheelPosition, GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),
                wheelSizeX, wheelSizeY, wheelAngle + 225 ,90);
        g2.setColor(Color.red);
        g2.fillArc(wheelPosition, GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),
                wheelSizeX, wheelSizeY, wheelAngle + 225,90);


        // Draws segment 4
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.black);
        g2.drawArc(wheelPosition, GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),
                wheelSizeX, wheelSizeY, wheelAngle + 315 ,90);
        g2.setColor(Color.blue);
        g2.fillArc(wheelPosition, GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),
                wheelSizeX, wheelSizeY, wheelAngle + 315,90);

    }

    // Returns a string referring to the segment currently in the "catch" position
   public static String whichSegmentUp() {
        if (Math.abs(wheelAngle) == 0) {
            segmentUp = "GREEN";
            return segmentUp;
        }
        else if ((wheelAngle) == -90 || wheelAngle == 270) {
            segmentUp = "BLACK";
            return segmentUp;
        }
        else if (Math.abs(wheelAngle) == 180) {
            segmentUp = "RED";
            return segmentUp;
        }
        else if (wheelAngle == -270 || wheelAngle ==  90 ) {
            segmentUp = "BLUE";
            return segmentUp;
        }
        else return "-1";

   }


}
