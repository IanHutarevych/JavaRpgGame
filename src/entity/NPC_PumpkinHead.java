package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_PumpkinHead extends Entity {

    public NPC_PumpkinHead(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        // COLLISION DETECTION
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        dialogueSet = -1;

        getImage();
        setDialogue();
    }
    public void getImage() {
        try {
            up1 = setup("/npc/pumpkinHeadUp1", gp.tileSize, gp.tileSize);
            up2 = setup("/npc/pumpkinHeadUp2", gp.tileSize, gp.tileSize);
            down1 = setup("/npc/pumpkinHeadDown1", gp.tileSize, gp.tileSize);
            down2 = setup("/npc/pumpkinHeadDown2", gp.tileSize, gp.tileSize);
            left1 = setup("/npc/pumpkinHeadLeft1", gp.tileSize, gp.tileSize);
            left2 = setup("/npc/pumpkinHeadLeft2", gp.tileSize, gp.tileSize);
            right1 = setup("/npc/pumpkinHeadRight1", gp.tileSize, gp.tileSize);
            right2 = setup("/npc/pumpkinHeadRight2", gp.tileSize, gp.tileSize);

            // Перевірка
            if (up1 == null || up2 == null || down1 == null || down2 == null ||
                    left1 == null || left2 == null || right1 == null || right2 == null) {
                throw new NullPointerException("Error: Missing sprite(s) for NPC 'Old Man'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDialogue(){

        dialogues[0][0] = "    Oh, you've arrived! Another traveler \nlooking for a relic? Or perhaps something \n        else has brought you here?";
        dialogues[0][1] = "    They say that this forest hides more \nthan just pine trees. If you listen and look \n  carefully, you will find the path to your \n                      destiny.";
        dialogues[0][2] = "    You will find a well in the clearing. They \nsay that the well remembers everyone who \n    sought answers in it. But remember:\n           knowledge has a price.";
        dialogues[0][3] = "    On the other side, there is a descent \nunder the roots of a pine tree. But don't \nrush - it's dangerous there without \n                    preparation.";

        dialogues[1][0] = "U are a piece of bread.";
        dialogues[1][1] = ".. of bread.";
    }

    @Override
    public void setAction(){

        if (onPath){

            int goalCol = 20;
            int goalRow = 43;


            searchPath(goalCol, goalRow);
        }
        else {
            actionLockCounter ++;

            if (actionLockCounter == 120){
                Random r = new Random();

                int i = r.nextInt(100)+1; // pick up a number from 1 tp 100


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
    }
    public void speak(){
        facePlayer();
        startDialogue(this,dialogueSet);

        dialogueSet++;

        if (dialogues[dialogueSet][0] == null){
            //dialogueSet = 0;

            dialogueSet--;
        }

        // OR YOU CAN WRITE SPECIFIC CONDITION example
        /*if (gp.player.life < gp.player.maxLife/3){
            dialogueSet = 1;
        }*/

        onPath = true;
    }
}
