package main;

import object.OBJ_ChestOpen;
import object.OBJ_DoorOpen;
import object.OBJ_Key;

import java.awt.image.BufferedImage;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    BufferedImage keyImage;


    public EventHandler(GamePanel gp) {
        this.gp = gp;

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 12;
            eventRect[col][row].y = 10;
            eventRect[col][row].width = 18;
            eventRect[col][row].height = 24;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;


            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }


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
            if (hit(26, 21, "any")) {damagePit(26, 21, gp.dialogueState);}
            if (hit(24, 25, "any")) {receiveMessage1(24, 25, gp.dialogueState);}
            if (hit(24, 38, "any")) {receiveMessage2(24, 38, gp.dialogueState);}
            if (hit(34, 34, "any")) {receiveMessage3(34, 34, gp.dialogueState);}
            if (hit(35, 37, "right")) {healingPool(35, 37,gp.dialogueState);}
            if (hit(36, 36, "down")) {healingPool(36, 36,gp.dialogueState);}
            if (hit(37, 37, "left")) {healingPool(37, 37,gp.dialogueState);}
            if (hit(36, 38, "up")) {healingPool(36, 38,gp.dialogueState);}

            if (hit(35, 33, "any")) {slowlySpeed(35, 33,gp.playState);}
            if (hit(35, 30, "any")) {slowlySpeed(35, 30,gp.playState);}
            if (hit(35, 29, "any")) {normalSpeed(35, 29,gp.playState);}
            if (hit(35, 34, "any")) {normalSpeed(35, 34,gp.playState);}

            if (hit(35, 29, "any")) {openChest(35, 28, gp.dialogueState);}
            if (hit(4, 23, "any")) {openDoor(4, 22, gp.playState);}
        }


    }




    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    private void damagePit(int col, int row, int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialog = "You fall into a pit";
        gp.player.life -= 1;

        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;

    }

    private void receiveMessage1(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialog = "← Ruins  \nShop ↓";
        canTouchEvent = false;
    }
    private void receiveMessage2(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialog = "Mysterious Well →\nShop   ↓";
        canTouchEvent = false;
    }
    private void receiveMessage3(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialog = "Sticky Swamp. Awful...";
        canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState) {
        if (gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.ui.currentDialog = "You healed by 1";
            if (gp.player.life < gp.player.maxLife){
                gp.player.life += 1;
            }

        }
        gp.keyH.enterPressed = false;
    }

    public void teleport(int gameState, int x, int y) {
        gp.gameState = gameState;
        gp.ui.currentDialog = "You teleported!";
        gp.player.worldX = gp.tileSize*x;
        gp.player.worldY = gp.tileSize*y;
    }

    public void slowlySpeed(int x, int y, int gameState) {
        gp.gameState = gameState;
        gp.player.speed = 1;
    }

    public void normalSpeed(int x, int y, int gameState) {
        gp.gameState = gameState;
        gp.player.speed = gp.player.defSpeed;
    }

    public void openChest(int col, int row, int gameState) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gameState;

            // Змінюємо об'єкт із закритого сундука на відкритий
            for (int i = 0; i < gp.obj.length; i++) {
                if (gp.obj[i] != null && gp.obj[i].name.equals("chest_close") &&
                        gp.obj[i].worldX == col * gp.tileSize &&
                        gp.obj[i].worldY == row * gp.tileSize) {

                    gp.obj[i] = new OBJ_ChestOpen(gp);
                    gp.obj[i].worldX = col * gp.tileSize;
                    gp.obj[i].worldY = row * gp.tileSize;
                    break;
                }
            }

            // Відображаємо повідомлення
            gp.ui.currentDialog = "You found a key!";
            gp.player.hasKey++;


            canTouchEvent = false;
        }
        gp.keyH.enterPressed = false;
    }

    public void openDoor(int col, int row, int gameState) {
        if (gp.player.hasKey >= 1 && gp.keyH.enterPressed) {
            for (int i = 0; i < gp.obj.length; i++) {
                if (gp.obj[i] != null && gp.obj[i].name.equals("door_close") &&
                        gp.obj[i].worldX == col * gp.tileSize &&
                        gp.obj[i].worldY == row * gp.tileSize) {

                    // Зміна текстури дверей одразу
                    gp.obj[i] = new OBJ_DoorOpen(gp);
                    gp.obj[i].worldX = col * gp.tileSize;
                    gp.obj[i].worldY = row * gp.tileSize;

                    // Зменшення кількості ключів
                    gp.player.hasKey--;

                    // Завершення перевірки для уникнення зайвих ітерацій
                    break;
                }
            }

            canTouchEvent = false; // Відключення повторних подій
            gp.keyH.enterPressed = false; // Скидання стану кнопки
        }
    }

}
