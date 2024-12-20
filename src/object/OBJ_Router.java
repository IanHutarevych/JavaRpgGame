package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Router  extends Entity {

    public OBJ_Router(GamePanel gp) {
        super(gp);

        name = "router";
        down1 = setup("/objects/router");
        collision = true;

        solidArea.x = 19;
        solidArea.y = 21;
        solidArea.width = 9;
        solidArea.height = 6;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


    }
}
