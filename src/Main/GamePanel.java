package Main;

import Assets.Wheel;
import Assets.Items;
//import Assets.testItem1;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JPanel;



/*
    This is the GamePanel CLass. It is the central hub that all the components talk to.
        -It sets up the "JPanel" i.e. the swing component that all the graphics are
        drawn on.
        -It contains the run() method that keeps track of frames / sec and updates
        the graphics.
        -It receives info from the "Key Listener" class to translate player input to movement.
*/


public class GamePanel extends JPanel implements Runnable {

    // ------------------- DATA FIELDS ------------------------------

    // game window constants
    // Score board width
    public static final int SCORE_BOARD_WIDTH = 200;
    public static final int SCREEN_WIDTH = 1000 + SCORE_BOARD_WIDTH ;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 20;
    public static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; // Sets up "Game Resolution"

    public final int FPS = 60;  // Frames Per Second


    // initiate Key Handler so game panel can hear keyboard
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Assets.Wheel wheel = new Wheel();
    Assets.Items ball = new Items();
    GameScore gameScore = new GameScore();

    // Game resolution grid
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    // Game Panel Background set and resized
//    BufferedImage backGroundRaw = ImageIO.read(new File("res/OutrunGif.gif"));
//    Image backGround = backGroundRaw.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_SMOOTH);

    // Sets speed of player can move the wheel
    int playerSpeed = 8;



//    Icon bgRaw = new ImageIcon("res/OutrunGif.gif");
//    BufferedImage bg

//    Items testItem = new testItem1(1, 30);
//    ItemCatchDetector catchItem = new ItemCatchDetector();



// ---------------------- Game Panel Constructor ------------------------------

    GamePanel() throws IOException {
//        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));   // Sets size of game panel
        this.setBackground(Color.black);                                    // sets background color of game panel
        this.setFocusable(true);                                            // Makes it so game panel hears keyboard inputs
        this.setDoubleBuffered(true);                                       // Improves render quality
		this.addKeyListener(keyH);                                          // sets up listener for game panel

    }



    // ------------------- Game Panel Methods ----------------------------------

    // Initiates play mode -- Draws elements in starting position
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        if (GameScore.getEndGameState() == true) {
            gameThread.interrupt();
        }
    }

    // "paintComponent()
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
//        g2.drawImage(backGround, 0,0,null);
        gameScore.draw(g2);
        Wheel.draw(g2);
//        testItem.draw(g2);
        Assets.Items.paint(g2); //Thanh

    }



    // The Run function keeps track of time to update feedback and redraw the screen
    // 60 times per second

    public static int timer = 0; // Thanh
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        //Thanh
        Random ran = new Random();
        while(gameThread != null) {

//            System.out.println("The game loop is running");

            long currentTime = System.nanoTime();
//            System.out.println("Current Time: " + currentTime);
            System.out.println(Wheel.whichSegmentUp());
            System.out.println(SCREEN_WIDTH - Wheel.wheelSizeX/1.5 - SCORE_BOARD_WIDTH);
            System.out.println((Wheel.wheelPosition));
//THANH OPEN

            timer += ran.nextInt(40);

            //Balls Speed
            Assets.Items.ballYBlue += Assets.Items.ballSpeedYBlue;
            Assets.Items.ballYRed += Assets.Items.ballSpeedYRed;
            Assets.Items.ballYGreen += Assets.Items.ballSpeedYGreen;
            Assets.Items.ballYWhite += Assets.Items.ballSpeedYWhite;

            //Square
            if(Assets.Items.yes) {
                Assets.Items.sqY +=  Assets.Items.sqSpeed;
            }
            if(Assets.Items.sqY > 600) {
                timer = 0;
                Assets.Items.yes = false;
                Assets.Items.sqY = 0;
                Assets.Items.sqX = ran.nextInt(800);
            }

            //Location
            if(Assets.Items.ballYBlue > 600){ //Restart ball position to the top
                Assets.Items.ballYBlue = -800;
                Assets.Items.ballXBlue = ran.nextInt(100,900);
            }
            if(Assets.Items.ballYRed > 600) {
                Assets.Items.ballYRed =-500;
                Assets.Items.ballXRed = ran.nextInt(100,900);
            }
            if(Assets.Items.ballYGreen > 600 || Items.greenCaught == true) {
                Assets.Items.ballYGreen= -900;
                Assets.Items.ballXGreen = ran.nextInt(100,900);
            }
            if(Assets.Items.ballYWhite> 600) {
                Assets.Items.ballYWhite = -500;
                Assets.Items.ballXWhite = ran.nextInt(100,900);
            }
            //Not on top of each other
            while((Assets.Items.ballXBlue == Assets.Items.ballXRed) || (Assets.Items.ballXGreen == Assets.Items.ballXWhite) || (Assets.Items.ballXBlue == Assets.Items.ballXGreen) ||
                    (Assets.Items.ballXBlue == Assets.Items.ballXWhite)) {
                Assets.Items.ballXRed = ran.nextInt(800);
                Assets.Items.ballXBlue = ran.nextInt(800);
                Assets.Items.ballXGreen = ran.nextInt(800);
                Assets.Items.ballXWhite = ran.nextInt(800);
            }
            Items.greenCaught = false;
            Items.blueCaught = false;
            Items.redCaught = false;
            Items.whiteCaught = false;

            //CLOSE THANH

            // 1 UDATE: update information such as character positions
            update();

            // 2 DRAW: draw the screen with the updated information
            repaint();  // calls "paintComponent()" somehow

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

    //  ------------------- Controls -------------------------
    public void update() {
        if		(keyH.upPressed 	== true && KeyHandler.spinOnce == true) {
            KeyHandler.spinOnce = false;
            wheel.wheelAngle -= Wheel.theta;
        }
        else if (keyH.downPressed 	== true && KeyHandler.spinOnce == true) {
            KeyHandler.spinOnce = false;
            Wheel.wheelAngle += Wheel.theta;
        }
        else if (keyH.rightPressed 	== true && Wheel.wheelPosition < (SCREEN_WIDTH - SCORE_BOARD_WIDTH) ) {
            Wheel.wheelPosition += playerSpeed;
        }
        else if (keyH.leftPressed 	== true && Wheel.wheelPosition > (-Wheel.wheelSizeX / 2)) {
            Wheel.wheelPosition -= playerSpeed;
        }

        gameScore.update();
//        testItem.update();
//        catchItem.checkCatch(testItem);

    }





}

