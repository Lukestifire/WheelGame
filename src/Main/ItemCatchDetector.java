package Main;

import Assets.Items;
import Assets.Wheel;




/*
    The ItemCatchDetector class checks when the wheel catches an item.
    It then tells the item it has been caught and tells the scoreboard which item was caught

 */

/*

public class ItemCatchDetector {
    private final double PI = 3.14159;
    private int wheelSegmentID = 10;




//    GamePanel gp;
    public ItemCatchDetector() {
//        this.gp = gp;
    }

    public void checkCatch(Items item) {
        wheelSegmentID = Wheel.whichSegmentUp();

        // Cartesian Cordinates of center of wheel
        double wheelOriginX = Wheel.wheelPosition + (Wheel.wheelSizeX/2);
        double wheelOriginY = (GamePanel.SCREEN_HEIGHT -
                (GamePanel.SCREEN_HEIGHT - (GamePanel.SCREEN_HEIGHT/3)));

        // Polar Cordinates of wheel
        double radiusOfWheel = Math.sqrt((Wheel.wheelSizeY/2)*(Wheel.wheelSizeY/2));
        double thetaOfWheelHigh = Wheel.theta/2 * (PI / 180);
        double thetaOfWheelLow =  -Wheel.theta/2 * (PI / 180);

        // Cartesian Cordinates item relative to wheel
        double itemPositionX = item.getXPosition() - wheelOriginX;
        double itemPositionY =  ((wheelOriginY) - (GamePanel.SCREEN_HEIGHT - item.getYPosition())  );

        // Polar Coordinates of Item relative to wheel
        double radiusOfItem = Math.sqrt(itemPositionX*itemPositionX +
                itemPositionY*itemPositionY);
        double thetaOfItem = (Math.atan2(itemPositionY, itemPositionX)) - PI/2;
        System.out.println(thetaOfItem);

        if ( radiusOfItem <= radiusOfWheel &&
                thetaOfItem <= thetaOfWheelHigh && thetaOfItem >= thetaOfWheelLow
                ) {
//            item.setIsCaught(true);

        }
//        System.out.println(item.getIsCaught());
    }

}
*/