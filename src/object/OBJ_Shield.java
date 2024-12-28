package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield extends Entity {
    public OBJ_Shield(GamePanel gp) {
        super(gp);

        name = "Knight's shield";
        down1 = setup("/objects/shield",gp.tileSize, gp.tileSize);
        defenceValue = 3;
        description = "[" + name + "]\nProtected you.";
    }
}
