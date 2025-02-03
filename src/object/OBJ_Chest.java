package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gp;

    public static final String objName = "Chest";

    public OBJ_Chest(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image1 = setup("/objects/chest_close",gp.tileSize, gp.tileSize);
        image2 = setup("/objects/chest_open",gp.tileSize, gp.tileSize);
        down1 = image1;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        idle = false;


    }
    public void setDialogue() {
        dialogues[0][0] = "You open a chest and find a " + loot.name + "!\n... But you cannot carry anymore!";
        dialogues[0][1] = "You open a chest and find a " + loot.name + "!\nYou obtain the  \" + loot.name + \"!\"";
        dialogues[2][0] = "It`s empty.";
    }
    public void setLoot(Entity loot) {
        this.loot = loot;

        setDialogue();
    }
    public void interact(){

        if (!opened){
            gp.playSE(3);

            if (!gp.player.canObtainItem(loot)) {
                startDialogue(this,0);
            }
            else {
                startDialogue(this,1);
                down1 = image2;
                opened = true;
            }
        } else {
            startDialogue(this,2);
        }
    }
}
