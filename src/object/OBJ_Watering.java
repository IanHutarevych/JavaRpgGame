package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Watering extends Entity {

    public static final String objName = "Watering";

    public OBJ_Watering(GamePanel gp) {
        super(gp);

        type = type_watering;
        name = objName;
        down1 = setup("/objects/wateringcan",gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nPerfect for grooving the \ntrees.";
        price = 10;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
