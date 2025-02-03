
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Health_Middle extends Entity {



    GamePanel gp;
    public static final String objName = "Health Potion 2";

    public OBJ_Potion_Health_Middle(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 4;
        down1 = setup("/objects/healthPotionMiddle",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals life by " + value + ".";
        price = 10;
        stackable = true;
        idle = false;
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "You drunk the " + name + "!\n" + "Your life has been recovered by " + value + ".";
    }
    public boolean use(Entity e) {

        e.life += value;
        gp.playSE(2);
        return false;
    }
}
