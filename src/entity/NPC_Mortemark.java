package entity;

import main.GamePanel;
import object.*;

public class NPC_Mortemark extends Entity {

    public NPC_Mortemark(GamePanel gp) {
        super(gp);

        direction = "down";

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        idle = false;

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {
            down1 = setup("/npc/skeleton1", gp.tileSize, gp.tileSize);
            down2 = setup("/npc/skeleton2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogues[0][0] = "“Oh, another one who is alive... for now. Welcome to \nmy abode of bones and treasure! I am Mortemark, \nkeeper of the ancient and seller of the unique. Have \nyou come to buy or just to admire my brilliant smile?";
        dialogues[1][0] = "I'll see you again... or I'll see you first.";
        dialogues[2][0] = "Oh dear, your pockets seem lighter than my ribcage.";
        dialogues[3][0] = "Selling what you're wearing? Bold, but impractical.";
    }
    public void setItems(){
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Shield_Metal(gp));
        inventory.add(new OBJ_Potion_Health_Small(gp));
        inventory.add(new OBJ_Potion_Health_Middle(gp));
        inventory.add(new OBJ_Potion_Health_Big(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Archangel_Sword(gp));
        inventory.add(new OBJ_Potion_Recovery_Small(gp));
        inventory.add(new OBJ_Potion_Recovery_Middle(gp));
        inventory.add(new OBJ_Potion_Recovery_Big(gp));
    }

    public void speak(){
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
