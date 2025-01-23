package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Archangel_Sword extends Entity {
    public OBJ_Archangel_Sword(GamePanel gp) {
        super(gp);

        type = type_michael_sword;
        name = "Arch.Michael`s sword";
        down1 = setup("/objects/Archangel_Michael_Sword",gp.tileSize, gp.tileSize);
        attackValue = 3;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nA holy mythical artifact \nthat crushes darkness";
        price = 150;
        knockBackPower = 2;
        motion1_duration = 3;
        motion2_duration = 15;
    }
}
