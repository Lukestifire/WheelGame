package Main;

import Assets.Wheel;
import Assets.Items;
import Assets.testItem1;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 20;
    public static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; // Sets up "Game Resolution"
    public final int FPS = 60;  // Frames Per Second

    // initiate Key Handler so game panel can hear keyboard
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Assets.Wheel wheel = new Wheel();

    // Game resolution grid
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    // Game Panel Background set and resized
//    BufferedImage backGroundRaw = ImageIO.read(new File("res/OutrunGif.gif"));
//    Image backGround = backGroundRaw.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_SMOOTH);

    // Sets speed of player can move the wheel
    int playerSpeed = 12;



//    Icon bgRaw = new ImageIcon("res/OutrunGif.gif");
//    BufferedImage bg


    ItemCatchDetector catchItem = new ItemCatchDetector();
    Items testItem = new testItem1();


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
    }

    // "paintComponent()
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
//        g2.drawImage(backGround, 0,0,null);
        Assets.Wheel.draw(g2);
        testItem.draw(g2);

    }



    // The Run function keeps track of time to update feedback and redraw the screen
    // 60 times per second
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;



        while(gameThread != null) {

//            System.out.println("The game loop is running");

            long currentTime = System.nanoTime();
//            System.out.println("Current Time: " + currentTime);
            System.out.println(Wheel.segmentUp);
            System.out.println(Wheel.wheelAngle);


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
        else if (keyH.rightPressed 	== true && Wheel.wheelPosition < (SCREEN_WIDTH - Wheel.wheelSizeX/1.5)) {
            Wheel.wheelPosition += playerSpeed;
        }
        else if (keyH.leftPressed 	== true && Wheel.wheelPosition > (-Wheel.wheelSizeX / 2)) {
            Wheel.wheelPosition -= playerSpeed;
        }

        testItem.update();
        catchItem.checkCatch(testItem);

    }





}

