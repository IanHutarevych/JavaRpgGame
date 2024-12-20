package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ChestClose extends Entity {


    public OBJ_ChestClose(GamePanel gp) {

        super(gp);

        name = "chest_close";
        down1 = setup("/objects/chest_close");
        collision = true;
    }
}
