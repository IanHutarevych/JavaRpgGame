
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Health_Big extends Entity {

    GamePanel gp;

    public OBJ_Potion_Health_Big(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Health Potion 3";
        value = 6;
        down1 = setup("/objects/healthPotionBig",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals life by " + value + ".";
        price = 15;
        stackable = true;
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "You drunk the " + name + "!\n" + "Your life has been recovered by " + value + ".";
    }
    public boolean use(Entity e) {
        startDialogue(this,0);
        e.life += value;
        gp.playSE(2);
        return false;
    }
}
