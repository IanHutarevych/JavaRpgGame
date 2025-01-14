package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Health_Midle extends Entity {



    GamePanel gp;

    public OBJ_Potion_Health_Midle(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Health Potion ";
        value = 5;
        down1 = setup("/objects/healthPotion",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals life by " + value + ".";
        price = 10;
    }
    public void use(Entity e) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialog = "You drunk the " + name + "!\n" + "Your life has been recovered by " + value + ".";
        e.life += value;
        gp.playSE(2);
    }
}
