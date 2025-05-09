package main;

import data.Progress;
import entity.Entity;
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
            else if (hit(0,27, 25, "any")) {receiveMessage2(gp.dialogueState);}
            else if (hit(0,34, 34, "any")) {receiveMessage3(gp.dialogueState);}
            else if (hit(2,29, 23, "any")) {receiveMessage4(gp.dialogueState);}
            else if (hit(2,10, 14, "any")) {receiveMessage4(gp.dialogueState);}

            else if (hit(0,33, 30, "right")) {healingPool(gp.dialogueState);}
            else if (hit(0,34, 29, "down")) {healingPool(gp.dialogueState);}
            else if (hit(0,35, 30, "left")) {healingPool(gp.dialogueState);}
            else if (hit(0,34, 31, "up")) {healingPool(gp.dialogueState);}

            else if (hit(2,21, 19, "right")) {healingPool(gp.dialogueState);}
            else if (hit(2,22, 18, "down")) {healingPool(gp.dialogueState);}
            else if (hit(2,23, 19, "left")) {healingPool(gp.dialogueState);}
            else if (hit(2,22, 20, "up")) {healingPool(gp.dialogueState);}

            else if (hit(15,21, 24, "left")) {sleep();}



            /*else if (hit(0,35, 33, "any")) {slowlySpeed(35, 33,gp.playState);}
            else if (hit(0,35, 30, "any")) {slowlySpeed(35, 30,gp.playState);}
            else if (hit(0,35, 29, "any")) {normalSpeed(35, 29,gp.playState);}
            else if (hit(0,35, 34, "any")) {normalSpeed(35, 34,gp.playState);}*/

            else if (hit(0,11, 23, "up")) {teleportEnter(1, 23, 20, gp.indoor); } // to merchant
            else if (hit(0,12, 23, "up")) {teleportEnter(1, 23, 20, gp.indoor); } // to merchant
            else if (hit(1, 23, 20, "any")) {teleportEnter(0, 12, 23, gp.outside); } // from merchant

            else if (hit(2, 21, 24, "up")) {teleportEnter(4, 25, 27, gp.indoor); } // to indoor1
            else if (hit(4, 25, 28, "down")) {teleport(2, 21, 24, gp.outside); } // from indoor1

            else if (hit(2, 14, 11, "up")) {teleportEnter(5, 25, 27, gp.indoor); } // to indoor2
            else if (hit(5, 25, 28, "down")) {teleport(2, 14, 11, gp.outside); } // from indoor2

            else if (hit(2, 22, 28, "up")) {teleportEnter(6, 25, 28, gp.indoor); } // to indoor3
            else if (hit(6, 25, 28, "down")) {teleport(2, 22, 28, gp.outside); } // from indoor3

            else if (hit(2, 15, 36, "up")) {teleportEnter(7, 25, 28, gp.indoor); } // to indoor4
            else if (hit(7, 25, 28, "down")) {teleport(2, 15, 36, gp.outside); } // from indoor4

            else if (hit(7, 25, 23, "up")) {teleportEnter(8, 24, 25, gp.indoor); }
            else if (hit(8, 24, 25, "down")) {teleportEnter(7, 25, 23, gp.indoor); }

            else if (hit(2, 16, 41, "up")) {teleportEnter(9, 26, 27, gp.indoor); } // to indoor6
            else if (hit(2, 15, 41, "up")) {teleportEnter(9, 23, 27, gp.indoor); } // to indoor6
            else if (hit(9, 26, 28, "down")) {teleport(2, 16, 41, gp.outside); } // from indoor6
            else if (hit(9, 23, 28, "down")) {teleport(2, 15, 41, gp.outside); } // from indoor6
            //else if (hit(9, 26, 24, "up")) {teleportEnter(8, 24, 25, gp.indoor); }
            //else if (hit(8, 24, 25, "down")) {teleportEnter(9, 26, 24, gp.indoor); }

            else if (hit(2, 36, 16, "up")) {teleportEnter(10, 26, 27, gp.indoor); } // to indoor7
            else if (hit(2, 35, 16, "up")) {teleportEnter(10, 23, 27, gp.indoor); } // to indoor7
            else if (hit(10, 26, 28, "down")) {teleport(2, 36, 16, gp.outside); } // from indoor7
            else if (hit(10, 23, 28, "down")) {teleport(2, 35, 16, gp.outside); } // from indoor7


            else if (hit(2, 17, 20, "up")) {teleportEnter(11, 26, 27, gp.indoor); } // to indoor8
            else if (hit(2, 16, 20, "up")) {teleportEnter(11, 23, 27, gp.indoor); } // to indoor8
            else if (hit(11, 26, 28, "down")) {teleport(2, 17, 20, gp.outside); } // from indoor8
            else if (hit(11, 23, 28, "down")) {teleport(2, 16, 20, gp.outside); } // from indoor8
            else if (hit(11, 26, 24, "up")) {teleportEnter(13, 24, 25, gp.indoor); }
            else if (hit(13, 24, 25, "down")) {teleportEnter(11, 26, 24, gp.indoor); }

            else if (hit(2, 25, 23, "up")) {teleportEnter(3, 24, 43, gp.dungeon); } // to dungeon
            else if (hit(3, 24, 43, "up")) {teleportEnter(2, 25, 23, gp.outside); } // from dungeon

            else if (hit(2, 40, 24, "any")) {teleport(0, 9, 24, gp.outside); } // from sand to tree
            else if (hit(0, 9, 24, "any")) {teleport(2, 40, 24, gp.outside); } // from tree to sand

            else if (hit(2, 38, 19, "up")) {teleportEnter(2, 37, 7, gp.outside); } // cave
            else if (hit(2, 37, 7, "up")) {teleportEnter(2, 38, 19, gp.outside); } // cave

            else if (hit(2, 25, 8, "up")) {teleportEnter(2, 16, 8, gp.outside); } // cave
            else if (hit(2, 16, 8, "up")) {teleportEnter(2, 25, 8, gp.outside); } // cave

            else if (hit(14, 15, 22, "up")) {teleportEnter(3, 26, 16, gp.dungeon); }
            else if (hit(3, 26, 16, "down")) {teleport(14, 15, 22, gp.outside); }

            else if (hit(3, 24, 40, "down")) {teleport(2, 32, 8, gp.outside); }
            else if (hit(2, 32, 8, "up")) {teleportEnter(3, 24, 40, gp.dungeon); }

            else if (hit(2, 36, 37, "up")) {teleportEnter(2, 30, 29, gp.outside); } // cave
            else if (hit(2, 30, 29, "up")) {teleportEnter(2, 36, 37, gp.outside); } // cave

            else if (hit(14, 25, 26, "up")) {teleportEnter(15, 26, 27, gp.indoor); } // cave
            else if (hit(15, 26, 27, "down")) {teleportEnter(14, 25, 26, gp.outside); } // cave

            else if (hit(10, 26, 24, "right")) {teleportEnter(16, 24, 9, gp.dungeon); }
            else if (hit(16, 24, 8, "up")) {teleport(10, 26, 24, gp.indoor); }

            else if (hit(6, 25, 25, "any")) {speak(gp.npc[6][0]); }
            else if (hit(16, 24, 25, "any")) {skeletonLord(); }
        }


    }

    private void speak(Entity entity) {
        if (gp.keyH.enterPressed){
            gp.gameState = gp.tradeState;
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
    public void teleport(int map, int col, int row, int area) {
            gp.player.attackCanceled = true;

            gp.gameState = gp.transitionState;
            gp.nextArea = area;
            tempMap = map;
            tempCol = col;
            tempRow = row;

            canTouchEvent = false;
            gp.playSE(9); // ;skd;oAWNGJOADBJO;GAK'FAL;BJALMNG'PKANOBMWPkr

    }
    public void teleportEnter(int map, int col, int row, int area) {

            if (gp.keyH.enterPressed) {
                gp.player.attackCanceled = true;

                gp.gameState = gp.transitionState;
                gp.nextArea = area;
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
    /*public void openDoor(int col, int row, int gameState) {
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
    }*/
    public void setDialogue() {
        eventMaster.dialogues[0][0] = "You fall into a pit";
        eventMaster.dialogues[1][0] = "\"Waters of the ancient well flow through you, mending \nyour wounds, restoring your essence. The echoes of \nyour journey are etched into the threads of fate.\"";
        eventMaster.dialogues[2][0] = "← Ruins  \nShop ↓";
        eventMaster.dialogues[3][0] = "Shop ←\nMysterious Well ↓";
        eventMaster.dialogues[4][0] = "Sticky Swamp. Awful...";
        eventMaster.dialogues[5][0] = "A sand town";
    }
    public void sleep() {
        if (gp.keyH.enterPressed) {
            gp.player.attackCanceled = true;
            gp.playSE(17);
            gp.gameState = gp.sleepState;
            // playSE in future
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.player.getSleepingImage(gp.player.idleLeft1);
        }
    }
    public void skeletonLord() {
        if (!gp.bossBattleOn && !Progress.skeletonLordDefeated) {
            gp.gameState = gp.cutSceneState;
            gp.csManager.sceneNum = gp.csManager.skeletonLord;
        }
    }
}
