package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BillboardSand extends Entity {

    public static final String objName = "billboardSand";

    public OBJ_BillboardSand(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/objects/billboardSand",gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 8;
        solidArea.y = 21;
        solidArea.width = 32;
        solidArea.height = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
