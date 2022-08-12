package Main;

import Assets.Items;
import Assets.Wheel;


/*
    The ItemCatchDetector class checks when the wheel catches an item.
    It then tells the item it has been caught and tells the scoreboard which item was caught

 */
public class ItemCatchDetector {
    private int wheelSegmentID = 10;




//    GamePanel gp;
    public ItemCatchDetector() {
//        this.gp = gp;
    }

    public void checkCatch(Items item) {
        wheelSegmentID = Wheel.whichSegmentUp();

    }

}
