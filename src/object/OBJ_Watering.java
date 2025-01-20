package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Watering extends Entity {
    public OBJ_Watering(GamePanel gp) {
        super(gp);

        type = type_watering;
        name = "watering";
        down1 = setup("/objects/wateringcan",gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nPerfect for grooving the trees.";
        price = 10;
    }
}
