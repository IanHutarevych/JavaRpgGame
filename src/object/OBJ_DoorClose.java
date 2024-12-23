package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_DoorClose extends Entity {


    public OBJ_DoorClose(GamePanel gp) {

        super(gp);

        name = "door_close";
        down1 = setup("/objects/door_close",gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
