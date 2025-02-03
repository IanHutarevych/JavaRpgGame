package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Billboard extends Entity {

    public static final String objName = "billboard";

    public OBJ_Billboard(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/objects/billboard",gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 19;
        solidArea.y = 21;
        solidArea.width = 9;
        solidArea.height = 6;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        idle = false;

    }

}
