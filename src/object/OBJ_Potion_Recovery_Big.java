
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Recovery_Big extends Entity {

    GamePanel gp;
    public static final String objName = "Recover Potion 3";

    public OBJ_Potion_Recovery_Big(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 3;
        down1 = setup("/objects/recoveryPotionBig",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nRecover mana by " + value + ".";
        price = 15;
        stackable = true;
        idle = false;
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
