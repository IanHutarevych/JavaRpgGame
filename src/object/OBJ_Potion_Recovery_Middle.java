
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Recovery_Middle extends Entity {

    GamePanel gp;

    public OBJ_Potion_Recovery_Middle(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Recover Potion 2";
        value = 2;
        down1 = setup("/objects/recoveryPotionMiddle",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nRecover mana by " + value + ".";
        price = 10;
        stackable = true;
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "You drunk the " + name + "!\n" + "Your mana has been recovered by " + value + ".";
    }
    public boolean use(Entity e) {

        e.mana += value;
        gp.playSE(2);
        return false;
    }
}
