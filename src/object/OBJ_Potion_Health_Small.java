package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Health_Small extends Entity {



    GamePanel gp;

    public OBJ_Potion_Health_Small(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Health Potion 1";
        value = 2;
        down1 = setup("/objects/healthPotionSmall",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals life by " + value + ".";
        price = 5;
        stackable = true;
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
