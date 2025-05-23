package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gp;
    public static final String objName = "Key";

    public OBJ_Key(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = type_key;

        //type = type_obstacle;
        name = objName;
        down1 = setup("/objects/Key1",gp.tileSize, gp.tileSize);
        down2 = setup("/objects/Key2",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIt opens the door.";
        price = 25;
        stackable = true;
        idle = false;


    }
}