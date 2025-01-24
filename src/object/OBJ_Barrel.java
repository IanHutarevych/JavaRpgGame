package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Barrel extends Entity{
    GamePanel gp;

    public OBJ_Barrel(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Barrel";
        image1 = setup("/objects/barrel_close",gp.tileSize, gp.tileSize);
        image2 = setup("/objects/barrel_open",gp.tileSize, gp.tileSize);
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
            sb.append("You open a barrel and find a " + loot.name + "!");

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
    public void setLoot(Entity loot) {
        this.loot = loot;
    }
}
