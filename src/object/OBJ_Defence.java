package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Defence extends Entity {

    GamePanel gp;
    public static final String objName = "Defence";

    public OBJ_Defence(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        value = 2;
        type = type_pickupOnly;
        down1 = setup("/objects/defence01",gp.tileSize - 12, gp.tileSize - 12);
        image1 = setup("/objects/defence01",gp.tileSize - 12, gp.tileSize - 12);
        image2 = setup("/objects/defence02",gp.tileSize - 12, gp.tileSize - 12);
        image3 = setup("/objects/defence03",gp.tileSize - 12, gp.tileSize - 12);
        /*image4 = setup("/objects/defence011",gp.tileSize, gp.tileSize);
        image5 = setup("/objects/defence021",gp.tileSize, gp.tileSize);*/
        idle = false;
    }
}
