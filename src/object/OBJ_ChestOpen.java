package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_ChestOpen extends Entity {


    public OBJ_ChestOpen(GamePanel gp) {

        super(gp);

        name = "chest_open";
        down1 = setup("/objects/chest_open",gp.tileSize, gp.tileSize);
        collision = true;
    }
}
