package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cactus extends Entity {

    public static final String objName = "Cactus";

    public OBJ_Cactus(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/objects/cactus",gp.tileSize, gp.tileSize);
        collision = true;
        idle = false;

        solidArea.x = 15;
        solidArea.y = 15;
        solidArea.width = 16;
        solidArea.height = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
