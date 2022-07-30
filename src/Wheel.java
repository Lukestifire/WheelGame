import java.awt.*;



public class Wheel {
    // ---------------------- Attributes ------------------------
    static int wheelPosition; // always an x value
    static final int wheelSizeX = GamePanel.UNIT_SIZE * 7;
    static final int wheelSizeY = GamePanel.UNIT_SIZE * 7;


    // --------------------- Constructors -----------------------------
    public Wheel() {
        wheelPosition = (GamePanel.SCREEN_WIDTH/2)- (wheelSizeX/2);
    }



    // -------------------- ----Display ---------------------------------
    public static void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(wheelPosition, GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/4), wheelSizeX, wheelSizeY);
    }

    /* TODO - turn into four sided object */



    // --------------------- Controls -------------------------------
    public static void move() {
        switch(GamePanel.direction) {
            case 'S':
                break;
            case 'R':
                wheelPosition += GamePanel.UNIT_SIZE;
                break;
            case 'L':
                wheelPosition -= GamePanel.UNIT_SIZE;
                break;

        }
    }

    /* TODO  -  add spin function. Should rotate one "slice" for each up/dwn press*/
}
