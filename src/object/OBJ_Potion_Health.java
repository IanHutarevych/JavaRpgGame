package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Health extends Entity {


    int value = 5;
    GamePanel gp;

    public OBJ_Potion_Health(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Health Potion ";
        down1 = setup("/objects/healthPotion",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals life by " + value + ".";
    }
    public void use(Entity e) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialog = "You drunk the " + name + "!\n" + "Your life has been recovered by " + value + ".";
        e.life += value;
        if (gp.player.life > gp. player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
    }
}
