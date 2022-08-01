import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

    // ------------------- DATA FIELDS ------------------------------

    // set up game window
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; // Sets up "Game Resolution"
    static final int DELAY = 20;

    // Game resolution grid
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    // Game Panel Background set and resized
    BufferedImage backGroundRaw = ImageIO.read(new File("Background.png"));
    Image backGround = backGroundRaw.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_SMOOTH);


    // Timer
    Timer timer;
    boolean running;



    // Controls
    public static char direction = 'S';
    public static char spin = 'N';
    public static boolean spinOnce = true;









// ---------------------- Game Panel Constructor ------------------------------

    GamePanel() throws IOException {
//        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));   // Sets size of game panel
        this.setBackground(Color.black);                                    // sets background color of game panel
        this.setFocusable(true);                                            // Makes it so game panel hears keyboard inputs
        this.addKeyListener(new MyKeyAdapter());                            // sets up listener for game panel
        startGame();
    }



    // ------------------- Game Panel Methods ----------------------------------

    // Initiates play mode -- Draws elements in starting position
    public void startGame() {
        new Wheel(); // Creates Wheel Object

        // Game is running
        running = true;
        timer = new Timer(DELAY,this );
        timer.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // Draw all objects onto Game Panel
    public void draw(Graphics g) {
        g.drawImage(backGround, 0,0,null);
        Wheel.draw(g);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            Wheel.move();
        }
        repaint();
    }



     //  ------------------- Controls -------------------------
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            // -------- Move Left and Right Controls -----
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)  {
                direction = 'R';
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                direction = 'L';
            }

            // --- Spin Clockwise and counterclockwise ---
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                spin = 'U';
            }
            else if ( e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                spin = 'D';
            }
            else {
                spin = 'N';
            }



        }
        public void keyReleased(KeyEvent e) {

            // When traveling right, only releasing the right arrow stops the wheel moving right.
            if ((direction == 'R') && (e.getKeyCode() != 39)) {
            }
            // When traveling left, only releasing the left arrow stops the wheel moving left.
            else if ((direction == 'L') && (e.getKeyCode() != 37)) {

            }
            else {
                direction = 'S';
            }



            if (spin == 'U' || spin == 'D' ) {

            }
            else {
                spin = 'N';
                spinOnce = true;
            }




        }


    }





}

