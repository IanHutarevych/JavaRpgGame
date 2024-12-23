package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Swamp1 extends Entity {


    public OBJ_Swamp1(GamePanel gp) {

        super(gp);

        name = "swamp1";
        down1 = setup("/objects/swamp1",gp.tileSize, gp.tileSize);
    }

}
