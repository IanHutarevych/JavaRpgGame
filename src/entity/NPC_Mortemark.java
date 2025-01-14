package entity;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Potion_Health_Midle;
import object.OBJ_Shield_Metal;
import object.OBJ_Sword_Normal;

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

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {
            down1 = setup("/npc/skeleton1", gp.tileSize, gp.tileSize);
            down2 = setup("/npc/skeleton2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogues[0] = "“Oh, another one who is alive... for now. Welcome to my \nabode of bones and treasure! I am Mortemark, keeper of \nthe ancient and seller of the unique. Have you come to \nbuy or just to admire my brilliant smile?”";
        /*dialogues[1] = "";
        dialogues[2] = "";
        dialogues[3] = "";*/

    }
    public void setItems(){
        inventory.add(new OBJ_Potion_Health_Midle(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Shield_Metal(gp));
        inventory.add(new OBJ_Axe(gp));
    }

    public void speak(){

        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;

    }
}
