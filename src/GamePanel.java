import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

    // ------------------- DATA FIELDS ------------------------------

    // set up game window
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 20;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    Timer timer;
    boolean running;
    public static char direction = 'S';


// ---------------------- Constructor ------------------------------

    GamePanel() {
//        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_HEIGHT,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {

        new Wheel();
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
        Wheel.draw(g);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            Wheel.move();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_RIGHT)  {
                direction = 'R';
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                direction = 'L';
            }

        }
        public void keyReleased(KeyEvent e) {
            if ((direction == 'R') && (e.getKeyCode() != 39)) {

            }
            else if ((direction == 'L') && (e.getKeyCode() != 37)) {

            }
            else {
                direction = 'S';
            }


        }


    }

}

