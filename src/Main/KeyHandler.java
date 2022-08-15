package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// ---------------------------------- Key Handler ----------------------------------
/*
    This class implements the KeyListener interface so that, when the GamePanel class
    is set to "focusable", the keyboard input codes can be used to update the position of the wheel.
    -Left and right will tell the wheel to move left and right.
    -'Up' and 'W' will spin the wheel clockwise and 'down' and 'S' will spin the wheel counterclockwise.
 */

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public static boolean spinOnce = true;


    @Override
    public void keyTyped(KeyEvent e) {
        // This method is required by "key Listener" but is unused in this program
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if((code == KeyEvent.VK_UP || code == KeyEvent.VK_W) && spinOnce == true) {
            upPressed = true;

        }
        if((code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) && spinOnce == true) {
            downPressed = true;

        }
        if(code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            upPressed = false;
            spinOnce = true;
        }
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = false;
            spinOnce = true;
        }
        if(code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

    }

}