package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gp;
    Entity loot;
    boolean opened = false;

    public OBJ_Chest(GamePanel gp, Entity loot) {

        super(gp);
        this.gp = gp;
        this.loot = loot;

        type = type_obstacle;
        name = "chest_close";
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


    }
    public void interact(){
        gp.gameState = gp.dialogueState;

        if (opened == false){
            gp.playSE(3);

            StringBuilder sb = new StringBuilder();
            sb.append("You open a chest and find a " + loot.name + "!");
            //gp.ui.currentDialog = "You open a chest and find a " + loot.name + "!";

            if (!gp.player.canObtainItem(loot)) {
                sb.append("\nYou have only " + gp.player.inventory.size() + " inventory!");
            }
            else {
                sb.append("\nYou obtain the  " + loot.name + "!");
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialog = sb.toString();
        } else {
            gp.ui.currentDialog = "It`s empty!";
        }
    }
}
