package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PickaxeNormal extends Entity {

    public static final String objName = "Pickaxe";

    public OBJ_PickaxeNormal(GamePanel gp) {
        super(gp);

        type = type_pickaxe_normal;
        name = objName;
        down1 = setup("/objects/pickaxe",gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nA sturdy pickaxe for \nmining and crushing.";
        price = 75;
        knockBackPower = 10;
        motion1_duration = 10;
        motion2_duration = 30;
        idle = false;
    }
}
