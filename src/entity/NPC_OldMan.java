package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {
    GamePanel gp;
    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage(){
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    public void setDialogue(){

        dialogues[0] = "Hello, traveler!";
        dialogues[1] = "I dont know what plot to write";
        dialogues[2] = "But i`ll create a good one plot \n ";
        dialogues[3] = "So now i can just say break a leg)";

    }

    @Override
    public void setAction(){

        actionLockCounter ++;

        if (actionLockCounter == 120){
            Random r = new Random();

            int i = r.nextInt(100)+1; // pick up a nimber from 1 tp 100


            // EASY AI FOR NPC
            if (i <= 25){
                direction = "up";
            } if (i <= 50 && i > 25){
                direction = "down";
            } if (i <= 75 && i > 50){
                direction = "left";
            } if (i > 75){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void speak(){
        super.speak();
    }
}
