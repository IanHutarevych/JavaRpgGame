package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Swamp3 extends Entity {


    GamePanel gp;

    public OBJ_Swamp3(GamePanel gp) {
        super(gp);

        name = "swamp3";
        down1 = setup("/objects/swamp3",gp.tileSize, gp.tileSize);
    }

}
