package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Boots  extends Entity {

    public static final String objName = "Hermes Boots";

    public OBJ_Boots(GamePanel gp) {

        super(gp);

        name = objName;
        down1 = setup("/objects/boots",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nAn artifact from myths.";
        price = 10;
        idle = false;
    }

}
