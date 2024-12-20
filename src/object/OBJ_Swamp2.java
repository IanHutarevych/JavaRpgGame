package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Swamp2 extends Entity {



    public OBJ_Swamp2(GamePanel gp) {

        super(gp);

        name = "swamp2";
        down1 = setup("/objects/swamp2");
    }

}
