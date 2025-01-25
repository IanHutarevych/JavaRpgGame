package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Metal extends Entity{
    public OBJ_Shield_Metal(GamePanel gp) {
        super(gp);

        type = type_shieldMetal;
        name = "Knight`s shield";
        down1 = setup("/objects/shield_metal",gp.tileSize, gp.tileSize);
        defenceValue = 5;
        description = "[" + name + "]\nProtected you.";
        price = 35;
    }
}
