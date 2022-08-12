package Assets;

import java.awt.*;

public class testItem1 extends Items {


    public testItem1() {
        setSpeed(2);
    }


    int itemID = 78;
    int xSize = 10;
    int ySize = 10;

    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillArc(getXPosition(), getYPosition(), xSize, ySize,0,360 );
    }

    public void update() {
        y += getSpeed();
    }

}
