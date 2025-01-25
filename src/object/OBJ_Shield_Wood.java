package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        type = type_shieldWood;

        name = "Wood shield";
        down1 = setup("/objects/shield_wood",gp.tileSize, gp.tileSize);
        defenceValue = 3;
        description = "[" + name + "]\nProtected you.";
        price = 12;
    }
}
