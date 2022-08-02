import java.awt.*;

//------------------------------- WHEEL! -----------------------------------

public class Wheel {
    // ---------------- Wheel Characteristics -----------------------------
    static int wheelPosition;   // Keeps track of x position of wheel on screen
    static int wheelAngle;      // Keeps track of theta -- angle of the wheel on screen
    static int theta = 90;           // Angle in degrees each direction change spins the wheel

    // Controls overall size of wheel
    static final int wheelSizeX = GamePanel.UNIT_SIZE * 7;
    static final int wheelSizeY = GamePanel.UNIT_SIZE * 7;

    // --------------- Wheel Segment Characteristics ----------------------

    // Segment 1


    // Segment 2


    // Segment 3


    // segment 4


    // --------------------- Constructors ---------------------------------
    public Wheel() {
        wheelPosition = (GamePanel.SCREEN_WIDTH/2)- (wheelSizeX/2);
    }



    // -------------------- ----Display ------------------------------------
    public static void draw(Graphics2D g2) {

        // Draws segment 1

        g2.setColor(Color.green);
//        g.setStroke();
        g2.fillArc(wheelPosition,GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),wheelSizeX, wheelSizeY, wheelAngle + 45 ,90);


        // Draws Segment 2

        g2.setColor(Color.white);
        g2.fillArc(wheelPosition,GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),wheelSizeX, wheelSizeY, wheelAngle + 135,90);


        // Draws Segment 3

        g2.setColor(Color.red);
        g2.fillArc(wheelPosition,GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),wheelSizeX, wheelSizeY, wheelAngle + 225,90);


        // Draws segment 4
        g2.setColor(Color.blue);
        g2.fillArc(wheelPosition,GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3),wheelSizeX, wheelSizeY, wheelAngle + 315,90);

    }

    /* TODO - turn into four sided object */



    // --------------------- Controls ----------------------------------------
//    public static void move() {
//        switch(GamePanel.direction) {
//            case 'S':
//                break;
//            case 'R':
//                wheelPosition += GamePanel.UNIT_SIZE;
//                break;
//            case 'L':
//                wheelPosition -= GamePanel.UNIT_SIZE;
//                break;
//        }
//        switch(GamePanel.spin) {
//            case 'N':
//                break;
//            case 'U':
//                wheelAngle -= theta;
//                break;
//            case 'D':
//                wheelAngle += theta;
//                break;
//        }

//        if (GamePanel.spin == 'U' && GamePanel.spinOnce == true) {
//            wheelAngle -= theta;
//            GamePanel.spinOnce = false;
//        }
//        else if (GamePanel.spin == 'D' && GamePanel.spinOnce == true) {
//            wheelAngle += theta;
//            GamePanel.spinOnce = false;
//        }
//        else {
//            GamePanel.spin = 'N';
//        }

//    }

    /* TODO  -  add spin function. Should rotate one "slice" for each up/dwn press*/
}
