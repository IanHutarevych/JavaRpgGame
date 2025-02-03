package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_StairsDown extends Entity {

    public static final String objName = "StairsDown";

    public OBJ_StairsDown(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/objects/stairsDown", gp.tileSize, gp.tileSize);
        collision = true;
        idle = false;

        /*solidArea.x = 9;
        solidArea.y = 12;
        solidArea.width = 30;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;*/
    }
}
