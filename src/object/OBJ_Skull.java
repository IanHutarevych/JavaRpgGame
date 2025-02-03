package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Skull extends Entity {

    public static final String objName = "Skull";

    public OBJ_Skull(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/objects/skull",gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 9;
        solidArea.y = 12;
        solidArea.width = 30;
        solidArea.height = 33;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        idle = false;
    }
}
