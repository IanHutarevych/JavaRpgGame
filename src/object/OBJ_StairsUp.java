package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_StairsUp extends Entity {

    public static final String objName = "StairsUp";

    public OBJ_StairsUp(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/objects/stairsUp", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
