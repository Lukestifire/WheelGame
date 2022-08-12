package Assets;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Items {
    private int x = GamePanel.SCREEN_WIDTH /2;
    protected int y = 0;
    private int speed;
    private int angle;

    public BufferedImage item;

    public int getXPosition() {
        return x;
    }
    public int getYPosition() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setXposition(int x) {
        this.x = x;
    }

    public void setYposition(int y) {
        this.y = y;
    }

    public void update() {
        y += y + getSpeed();
    }


    public void draw(Graphics2D g2) {
    }
}
