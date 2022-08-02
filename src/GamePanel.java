import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    // ------------------- DATA FIELDS ------------------------------

    // game window constants
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; // Sets up "Game Resolution"
    static final int DELAY = 20;
    final int FPS = 60;  // Frames Per Second

    // initiate Key Handler so game panel can hear keyboard
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Game resolution grid
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    // Game Panel Background set and resized
    BufferedImage backGroundRaw = ImageIO.read(new File("Background.png"));
    Image backGround = backGroundRaw.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_SMOOTH);





    int playerSpeed = 12;









// ---------------------- Game Panel Constructor ------------------------------

    GamePanel() throws IOException {
//        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));   // Sets size of game panel
        this.setBackground(Color.black);                                    // sets background color of game panel
        this.setFocusable(true);                                            // Makes it so game panel hears keyboard inputs
        this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		                           // sets up listener for game panel
    }



    // ------------------- Game Panel Methods ----------------------------------

    // Initiates play mode -- Draws elements in starting position
    public void startGameThread() {
        gameThread = new Thread(this);
        new Wheel();
        gameThread.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g.drawImage(backGround, 0,0,null);
        Wheel.draw(g2);

    }

    // Draw all objects onto Game Panel
    public void draw(Graphics g) {

    }


//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(running) {
//            Wheel.move();
//        }
//        repaint();
//    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;



        while(gameThread != null) {

            System.out.println("The game loop is running");

            long currentTime = System.nanoTime();
            System.out.println("Current Time: " + currentTime);
//			long currentTime2 = System.currentTimemillis();


            // 1 UDATE: update information such as character positions
            update();

            // 2 DRAW: draw the screen with the updated information
            repaint();  // calls "paintComponent() method I guess"



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
            Wheel.wheelAngle -= Wheel.theta;
        }
        else if (keyH.downPressed 	== true && KeyHandler.spinOnce == true) {
            KeyHandler.spinOnce = false;
            Wheel.wheelAngle += Wheel.theta;
        }
        else if (keyH.rightPressed 	== true) {
            Wheel.wheelPosition += playerSpeed;
        }
        else if (keyH.leftPressed 	== true) {
            Wheel.wheelPosition -= playerSpeed;
        }

    }





}

