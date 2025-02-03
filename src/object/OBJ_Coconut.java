package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coconut extends Entity{

    GamePanel gp;
    public static final String objName = "Coconut";

    public OBJ_Coconut(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 2;
        down1 = setup("/objects/coconut",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals life by " + value + ".";
        price = 10;
        stackable = true;
        idle = false;
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "You eat the " + name + "!\n" + "Your life has been recovered by " + value + ".";
    }
    public boolean use(Entity e) {
        startDialogue(this,0);
        e.life += value;
        gp.playSE(2);
        return false;
    }
}
