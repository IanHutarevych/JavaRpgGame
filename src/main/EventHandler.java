package main;

import entity.Entity;
import object.OBJ_DoorOpen;
import object.OBJ_Key;

import java.awt.image.BufferedImage;

public class EventHandler{

    GamePanel gp;
    EventRect[][][] eventRect;
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    BufferedImage keyImage;


    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventMaster = new Entity(gp);

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image1;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 12;
            eventRect[map][col][row].y = 10;
            eventRect[map][col][row].width = 18;
            eventRect[map][col][row].height = 24;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;


            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
                if (row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
        setDialogue();
    }

    public void checkEvent() {

        // check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize){
            canTouchEvent = true;
        }

        if (canTouchEvent){
            if (hit(0,24, 25, "any")) {receiveMessage1();}
            else if (hit(0,24, 38, "any")) {receiveMessage2(gp.dialogueState);}
            else if (hit(0,34, 34, "any")) {receiveMessage3(gp.dialogueState);}
            else if (hit(2,14, 18, "any")) {receiveMessage4(gp.dialogueState);}
            else if (hit(2,10, 14, "any")) {receiveMessage4(gp.dialogueState);}
            else if (hit(0,35, 37, "right")) {healingPool(gp.dialogueState);}
            else if (hit(0,36, 36, "down")) {healingPool(gp.dialogueState);}
            else if (hit(0,37, 37, "left")) {healingPool(gp.dialogueState);}

            else if (hit(2,29, 29, "right")) {healingPool(gp.dialogueState);}
            else if (hit(2,30, 28, "down")) {healingPool(gp.dialogueState);}
            else if (hit(2,31, 29, "left")) {healingPool(gp.dialogueState);}
            else if (hit(2,30, 30, "up")) {healingPool(gp.dialogueState);}

            else if (hit(0,36, 38, "up")) {healingPool(gp.dialogueState);}

            else if (hit(0,35, 33, "any")) {slowlySpeed(35, 33,gp.playState);}
            else if (hit(0,35, 30, "any")) {slowlySpeed(35, 30,gp.playState);}
            else if (hit(0,35, 29, "any")) {normalSpeed(35, 29,gp.playState);}
            else if (hit(0,35, 34, "any")) {normalSpeed(35, 34,gp.playState);}

            else if (hit(0,4, 23, "any")) {openDoor(4, 22, gp.playState);}

            else if (hit(0,28, 19, "any")) {teleportEnter(1, 23, 20); }
            else if (hit(0,27, 20, "any")) {teleportEnter(1, 23, 20); }
            else if (hit(0,26, 19, "any")) {teleportEnter(1, 23, 20); }
            else if (hit(1, 23, 20, "any")) {teleportEnter(0, 27, 20); }
            else if (hit(2, 20, 43, "any")) {teleport(0, 23, 17); }
            else if (hit(0, 23, 17, "any")) {teleport(2, 20, 43); }

            else if (hit(2, 23, 10, "up")) {teleport(2, 17, 11); }
            else if (hit(2, 17, 10, "up")) {teleport(2, 23, 11); }

            else if (hit(1, 23, 18, "any")) {speak(gp.npc[1][0]); }
        }


    }

    private void speak(Entity entity) {
        if (gp.keyH.enterPressed){
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }

    public boolean hit(int map ,int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    private void damagePit(int gameState) {

        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster,0 );
        gp.player.life -= 1;

        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;

    }
    private void receiveMessage1() {
        eventMaster.startDialogue(eventMaster,2 );
        canTouchEvent = false;
    }
    private void receiveMessage2(int gameState) {
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster,3);
        canTouchEvent = false;
    }
    private void receiveMessage3(int gameState) {
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster,4);
        canTouchEvent = false;
    }
    private void receiveMessage4(int gameState) {
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster,5);
        canTouchEvent = false;
    }
    public void healingPool(int gameState) {
        if (gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            eventMaster.startDialogue(eventMaster,1);
            if (gp.player.life < gp.player.maxLife ){
                gp.player.life += 1;

            }
            if (gp.player.mana < gp.player.maxMana) {
                gp.player.mana = gp.player.maxMana;
            }
            gp.aSetter.setMonster();
            gp.sl.save();

        }
        gp.keyH.enterPressed = false;
    }
    public void teleport(int map, int col, int row) {
            gp.player.attackCanceled = true;

            gp.gameState = gp.transitionState;

            tempMap = map;
            tempCol = col;
            tempRow = row;

            canTouchEvent = false;
            gp.playSE(9); // ;skd;oAWNGJOADBJO;GAK'FAL;BJALMNG'PKANOBMWPkr

    }
    public void teleportEnter(int map, int col, int row) {

            if (gp.keyH.enterPressed) {
                gp.player.attackCanceled = true;

                gp.gameState = gp.transitionState;

                tempMap = map;
                tempCol = col;
                tempRow = row;

                canTouchEvent = false;
                gp.playSE(9); // ;skd;oAWNGJOADBJO;GAK'FAL;BJALMNG'PKANOBMWPkr
            }
    }
    public void slowlySpeed(int x, int y, int gameState) {
        gp.gameState = gameState;
        gp.player.speed = 1;
    }
    public void normalSpeed(int x, int y, int gameState) {
        gp.gameState = gameState;
        gp.player.speed = gp.player.defSpeed;
    }
    public void openDoor(int col, int row, int gameState) {
        if (gp.keyH.enterPressed) {

            gp.player.attackCanceled = true;


            boolean keyFound = false;
            for (int j = 0; j < gp.player.inventory.size(); j++) {
                if (gp.player.inventory.get(j) instanceof OBJ_Key) {
                    gp.player.inventory.remove(j);
                    keyFound = true;
                    break;
                }
            }


            if (keyFound) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals("door_close") &&
                            gp.obj[gp.currentMap][i].worldX == col * gp.tileSize &&
                            gp.obj[gp.currentMap][i].worldY == row * gp.tileSize) {


                        gp.obj[gp.currentMap][i] = new OBJ_DoorOpen(gp);
                        gp.obj[gp.currentMap][i].worldX = col * gp.tileSize;
                        gp.obj[gp.currentMap][i].worldY = row * gp.tileSize;

                        break;
                    }
                }
            }

            canTouchEvent = false;
            gp.keyH.enterPressed = false;
        }
    }
    public void setDialogue() {
        eventMaster.dialogues[0][0] = "You fall into a pit";
        eventMaster.dialogues[1][0] = "\"Waters of the ancient well flow through you, mending \nyour wounds, restoring your essence. The echoes of \nyour journey are etched into the threads of fate.\"";
        eventMaster.dialogues[2][0] = "← Ruins  \nShop ↓";
        eventMaster.dialogues[3][0] = "Mysterious Well →\nShop   ↓";
        eventMaster.dialogues[4][0] = "Sticky Swamp. Awful...";
        eventMaster.dialogues[5][0] = "A sand town";
    }
}
