package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gp;

    public OBJ_Key(GamePanel gp) {

        super(gp);
        this.gp = gp;

        //type = type_obstacle;
        name = "Key";
        down1 = setup("/objects/Key1",gp.tileSize, gp.tileSize);
        down2 = setup("/objects/Key2",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIt opens the door.";
        price = 25;
        stackable = true;


    }
}