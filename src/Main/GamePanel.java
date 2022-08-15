package Main;

import Assets.Wheel;
import Assets.Items;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage; // Nour
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import javax.sound.sampled.*;
import javax.swing.*;

// -------------------------- Game Panel ------------------------
/*
    This is the GamePanel CLass. It is the central hub that all the components talk to.
        -It sets up the "JPanel" i.e. the Java swing component that all the graphics are
        drawn on.
        -It contains the run() method that keeps track of frames / sec and updates
        the graphics.
        -It receives info from the "Key Listener" class to translate player input to movement.
*/

public class GamePanel extends JPanel implements Runnable, ActionListener {
    JButton restartButton;

    // ------------------- DATA FIELDS ------------------------------

    // game window constants
    // Score board width
    public static final int SCORE_BOARD_WIDTH = 200;
    public static final int SCREEN_WIDTH = 1000 + SCORE_BOARD_WIDTH ;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 20;
    public static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; // Sets up "Game Resolution"

    public final int FPS = 60;  // Frames Per Second

    public boolean gameOver = false;


    KeyHandler keyH = new KeyHandler(); // Initiate Key Handler so game panel can listen to keyboard
    Thread gameThread;
    Assets.Wheel wheel = new Wheel();
    Assets.Items ball = new Items();
    GameScore gameScore = new GameScore();

    // Game resolution grid is kept track of with two arrays
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    // Game Panel Background set and resized
    BufferedImage topBackGround = ImageIO.read(new File("res/topPaintBG.png")); // Nour
    BufferedImage bottomBackGround = ImageIO.read(new File("res/bottomPaintBG.png")); // Nour

    // Sets speed of player can move the wheel
    int playerSpeed = 15;

    // ---------------------- Game Panel Constructor ------------------------------

    GamePanel() throws IOException {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));   // Sets size of game panel
        this.setBackground(Color.white);                                    // sets background color of game panel
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

    // When called, stop thread and display "GAMEOVER" screen
    public void stopGameThread() throws InterruptedException {
        repaint();
        gameThread.sleep(5000000);
    }


    // paintComponent() uses graphics to draw to the game panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        gameScore.draw(g2);
        Wheel.draw(g2);
        Assets.Items.paint(g2); //Thanh
        g2.drawImage(topBackGround, 0, 0, null); // Nour
        g2.drawImage(bottomBackGround, 0, SCREEN_HEIGHT - 84, null); // Nour
    }

    // The Run function keeps track of time to update feedback and redraw the screen
    // 60 times per second
    public static int timer = 0; // Thanh
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;



        Random ran = new Random(); //Thanh

        while(gameThread != null) {
            long currentTime = System.nanoTime();
            System.out.println(Wheel.whichSegmentUp());
            System.out.println(GameScore.getEndGameState());
            System.out.println(Wheel.wheelAngle);

            //THANH OPEN
            timer += ran.nextInt(40);

            //Balls Speed
            Assets.Items.ballYBlue += Assets.Items.ballSpeedYBlue;
            Assets.Items.ballYRed += Assets.Items.ballSpeedYRed;
            Assets.Items.ballYGreen += Assets.Items.ballSpeedYGreen;
            Assets.Items.ballYBlack += Assets.Items.ballSpeedYBlack;

            //Square
            if(Assets.Items.yes) {
                Assets.Items.sqY +=  Assets.Items.sqSpeed;
            }
            if(Assets.Items.sqY > 600 || Items.sqCaught == true) {
                timer = 0;
                Assets.Items.yes = false;
                Assets.Items.sqY = 0;
                Assets.Items.sqX = ran.nextInt(800);
            }

            //Location of falling items
            if(Assets.Items.ballYBlue > 600 || Items.blueCaught == true){ //Restart ball position to the top
                Assets.Items.ballYBlue = -800;
                Assets.Items.ballXBlue = ran.nextInt(100,900);
            }
            if(Assets.Items.ballYRed > 600 || Items.redCaught == true) {
                Assets.Items.ballYRed =-400;
                Assets.Items.ballXRed = ran.nextInt(100,900);
            }
            if(Assets.Items.ballYGreen > 600 || Items.greenCaught == true) {
                Assets.Items.ballYGreen= -1000;
                Assets.Items.ballXGreen = ran.nextInt(100,900);
            }
            if(Assets.Items.ballYBlack > 600 || Items.blackCaught == true) {
                Assets.Items.ballYBlack = -1200;
                Assets.Items.ballXBlack = ran.nextInt(100,900);
            }
            //Items Not on top of each other
            while((Assets.Items.ballXBlue == Assets.Items.ballXRed) || (Assets.Items.ballXGreen == Assets.Items.ballXBlack) || (Assets.Items.ballXBlue == Assets.Items.ballXGreen) ||
                    (Assets.Items.ballXBlue == Assets.Items.ballXBlack)) {
                Assets.Items.ballXRed = ran.nextInt(800);
                Assets.Items.ballXBlue = ran.nextInt(800);
                Assets.Items.ballXGreen = ran.nextInt(800);
                Assets.Items.ballXBlack = ran.nextInt(800);
            }

            if (Items.greenCaught || Items.blueCaught || Items.redCaught || Items.blackCaught) {
                try {
                    GamePanel.playSound("res/dingSound.wav");
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                }
            }
            // item caught flags reset for "GameScore Class"
            Items.greenCaught = false;
            Items.blueCaught = false;
            Items.redCaught = false;
            Items.blackCaught = false;
            Items.ballMissed = false;

            // If lives are at or below 0 stop the game -- display "GAMEOVER"
            if (GameScore.getEndGameState() == true) {
                try {
                    stopGameThread();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

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
        if (keyH.upPressed == true && KeyHandler.spinOnce == true) {
            KeyHandler.spinOnce = false;
            wheel.wheelAngle -= Wheel.theta;
        } else if (keyH.downPressed == true && KeyHandler.spinOnce == true) {
            KeyHandler.spinOnce = false;
            Wheel.wheelAngle += Wheel.theta;
        } else if (keyH.rightPressed == true) { //&& Wheel.wheelPosition < (SCREEN_WIDTH)
            Wheel.wheelPosition += playerSpeed;
        } else if (keyH.leftPressed == true) { //&& Wheel.wheelPosition > (-Wheel.wheelSizeX / 2)
            Wheel.wheelPosition -= playerSpeed;
        }


        gameScore.update();
    }


    // Ding sound for user feedback when an item is successfully caught.
    public static void playSound(String soundFile) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File f = new File("./" + soundFile);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}



