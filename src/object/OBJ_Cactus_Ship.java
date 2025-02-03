package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cactus_Ship extends Entity {

    public static final String objName = "CactusShip";

    public OBJ_Cactus_Ship(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/objects/cactusShip1",gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 15;
        solidArea.y = 15;
        solidArea.width = 16;
        solidArea.height = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        idle = false;
    }
}
