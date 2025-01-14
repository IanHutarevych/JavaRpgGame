package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {


    public OBJ_Key(GamePanel gp) {

        super(gp);

        name = "Key";
        down1 = setup("/objects/Key1",gp.tileSize, gp.tileSize);
        down2 = setup("/objects/Key2",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIt opens the door.";
        price = 25;

    }

}