package main;

import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaStar;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    public Font maruMonica;
    BufferedImage heart01, heart02, heart03, manaFull, manaEmpty, coin;

    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialog = "";
    public int commandNum = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;
    int charIndex = 0;
    String combinedText = "";


    public UI(GamePanel gp) {
        this.gp = gp;


        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart01 = heart.image1;
        heart02 = heart.image2;
        heart03 = heart.image3;
        Entity star = new OBJ_ManaStar(gp);
        manaFull = star.image1;
        manaEmpty = star.image2;
        Entity bronzeCoin = new OBJ_Coin_Bronze(gp);
        coin = bronzeCoin.down1;

    }
    public void addMessage(String text) {

        message.add(text);
        messageCounter.add(0);

    }
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.WHITE);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMessage();
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPausedScreen();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        // CHARACTER STATE
        if (gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        // OPTIONS STATE
        if (gp.gameState == gp.optionsState){
            drawOptionScreen();
        }
        // GAME  OVER STATE
        if (gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        // TRANSITION STATE
        if (gp.gameState == gp.transitionState){
            drawTransition();
        }
        // TRADE STATE
        if (gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
        // SLEEP STATE
        if (gp.gameState == gp.sleepState){
            drawSleepScreen();
        }
    }
    private void drawSleepScreen() {
        counter++;

        if (counter < 120){
            gp.eManager.lightning.filterAlpha += 0.01f;
            if (gp.eManager.lightning.filterAlpha > 1f){
                gp.eManager.lightning.filterAlpha = 1f;
            }
        }
        if (counter >= 120){
            gp.eManager.lightning.filterAlpha -= 0.01f;
            if (gp.eManager.lightning.filterAlpha <= 0){
                gp.eManager.lightning.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lightning.dayState = gp.eManager.lightning.day;
                gp.eManager.lightning.dayCounter = 0;
                gp.gameState = gp.playState;
                gp.player.getImage();
            }
        }
    }
    private void drawTradeScreen() {

        switch (subState) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;

    }
    public void trade_select(){
        drawDialogueScreen();

        // DRAW WINDOW
        int x = (int)(gp.tileSize * 14);
        int y = (int)(gp.tileSize * 4.5);
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x,y,width,height);

        // DRAW TEXT
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if (commandNum == 0){
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed){
                subState = 1;
            }
        }
        y += gp.tileSize;

        g2.drawString("Sell", x, y);
        if (commandNum == 1){
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed){
                subState = 2;
            }
        }
        y += gp.tileSize;

        g2.drawString("Leave", x, y);
        if (commandNum == 2){
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed){
                commandNum = 0;
                gp.gameState = gp.dialogueState;

                String[] leaveDialogues = {
                        "I'll see you again... or I'll see you first.",
                        "Don't forget to come back when you're poorer... \nand dead.",
                        "There are rumors that you won't survive long without my \ngoods... But that's not my business anymore.",
                        "Go carefully, adventurer, and don't forget to come back. \nYour soul is always welcome here.",
                        "Don't let your bones become a commodity in my \ncollection. Good night."
                };

                Random random = new Random();
                int randomIndex = random.nextInt(leaveDialogues.length);
                currentDialog = leaveDialogues[randomIndex];

            }
        }

    }
    public void trade_buy(){

        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        // DRAW NPC INVENTORY
        drawInventory(npc, true);

        // DRAW HINT WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+24, y+60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your coins: " + gp.player.coin, x+24, y+60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol,npcSlotRow);
        if (itemIndex < npc.inventory.size()){

            x = (int) (gp.tileSize*5.5);
            y = (int) (gp.tileSize*5.5);
            width = (int) (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8 , 32,32,null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlightToRightText(text,gp.tileSize*8-20);
            g2.drawString(text, x, y+34);

            // BUY AN ITEM
            if (gp.keyH.enterPressed){
                if (npc.inventory.get(itemIndex).price > gp.player.coin){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    // Randomize one of Mortemark's responses
                    String[] insufficientFundsDialogs = {
                            "Oh dear, your pockets seem lighter than my ribcage.",
                            "Not enough coin? Come back when you’re wealthier... or \nmore desperate.",
                            "Tsk tsk, adventurer. Even skeletons have standards, you \nknow.",
                            "Short on gold? Looks like your fortune's as bare as my \nbones.",
                            "No coin, no goods. Even I don’t trade in IOUs."
                    };
                    currentDialog = insufficientFundsDialogs[(int) (Math.random() * insufficientFundsDialogs.length)];
                    drawDialogueScreen();
                }
                else {
                    if (gp.player.canObtainItem(npc.inventory.get(itemIndex))){
                        gp.player.coin -= npc.inventory.get(itemIndex).price;
                    }
                    else {
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        String[] fullInventoryDialogs = {
                                "Your bags are fuller than my crypt. Make some space, \nwill you?",
                                "No room for my treasures? A tragic oversight.",
                                "Hmm, perhaps it's time to part with something less... \nshiny.",
                                "Your inventory's bursting at the seams. Skeletons like \nme prefer to travel light.",
                                "Full already? Maybe you should’ve invested in a bigger \nbackpack instead."
                        };
                        currentDialog = fullInventoryDialogs[(int) (Math.random() * fullInventoryDialogs.length)];
                    }
                }
            }
        }
    }
    public void trade_sell(){

        // DRAW PLAYERS INVENTORY
        drawInventory(gp.player, true);
        int x;
        int y;
        int width;
        int height;

        // DRAW HINT WINDOW
        x = gp.tileSize * 2;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+24, y+60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your coins: " + gp.player.coin, x+24, y+60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol,playerSlotRow);
        if (itemIndex < gp.player.inventory.size()){

            x = (int) (gp.tileSize*12);
            y = (int) (gp.tileSize*5.5);
            width = (int) (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8 , 32,32,null);

            int price = gp.player.inventory.get(itemIndex).price/3*2;
            String text = "" + price;
            x = getXforAlightToRightText(text,gp.tileSize*14 + 5);
            g2.drawString(text, x, y+34);

            // SELL AN ITEM
            if (gp.keyH.enterPressed){
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || gp.player.inventory.get(itemIndex) == gp.player.currentShield){
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    String[] equippedItemDialogs = {
                            "Selling what you're wearing? Bold, but impractical.",
                            "You can't sell that while it's on you. Even I don't buy \nused gear... often.",
                            "Hmm, might want to take that off first, unless you enjoy \nbartering half-dressed.",
                            "Keep it on, adventurer. I’m not running a fitting room \nhere.",
                            "No trades for equipped items. Try not to lose your pants \nin the process."
                    };
                    currentDialog = equippedItemDialogs[(int) (Math.random() * equippedItemDialogs.length)];
                }
                else {
                    if (gp.player.inventory.get(itemIndex).amount > 1){
                        gp.player.inventory.get(itemIndex).amount--;
                    } else {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coin += price;
                }
            }
        }
    }
    private void options_endGameConfirmation(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialog = "Quit the game and \n return to the title screen?";

        for (String line : currentDialog.split("\n")){
            g2.drawString(line,textX, textY);
            textY += 40;
        }

        //YES
        String text = "Yes";
        textX = getXforCenterText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                subState = 0;
                gp.gameState = gp.titleState;
                try {
                    gp.resetGame(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //NO
        text = "No";
        textX = getXforCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                subState = 0;
                commandNum = 4;
            }
        }
    }
    public void options_fullScreenNotification(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialog = "the change will take \neffect after restarting \nthe game.";

        for (String line : currentDialog.split("\n")){
            g2.drawString(line, textX, textY);
            textY+= 40;
        }

        //BACK
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                subState = 0;
            }
        }

    }
    public void options_top(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXforCenterText(text);
        textY = frameY +gp.tileSize;
        g2.drawString(text, textX, textY);

        //FULL SCREEN ON\OFF
        textX = frameX + gp.tileSize;
        textY +=gp.tileSize*2;
        g2.drawString("Full Screen", textX, textY);
        if (commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                if (!gp.fullScreenOn){
                    gp.fullScreenOn = true;
                } else {
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (commandNum == 1){
            g2.drawString(">", textX-25, textY);
        }

        //SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if (commandNum == 2){
            g2.drawString(">", textX-25, textY);
        }

        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (commandNum == 3){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                subState = 2;
                commandNum = 0;
            }
        }

        // END GAME
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if (commandNum == 4){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                subState = 3;
                commandNum = 0;
            }
        }

        // BACK
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 5){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        // FULL SCREEN CHECK BOX
        textX = frameX + (int)(gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, gp.tileSize/2, gp.tileSize/2);
        if (gp.fullScreenOn){
            g2.fillRect(textX, textY, gp.tileSize/2, gp.tileSize/2);
        }

        //MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24); // 120/5 = 24
        int volumeWidth = 24*gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24*gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();

    }
    public void options_control(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Control";
        textX = getXforCenterText(text);
        textY = frameY +gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Shot/Cast", textX, textY); textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY);

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);

        // BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }
    }
    private void drawInventory(Entity entity, boolean cursor) {

        int frameX;
        int frameY;
        int frameWidth;
        int frameHeight;
        int slotCol;
        int slotRow;

        if (entity == gp.player){
            frameX = gp.tileSize*12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;

        } else {
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        // FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;

        // DRAW PLAYER`S ITEMS
        for (int i = 0; i < entity.inventory.size(); i++) {

            // EQUIP CURSOR
            if (entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield || entity.inventory.get(i) == entity.currentLight) {
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);


            }

            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            // DISPLAY AMOUNT
            if (entity.inventory.get(i).amount > 1 && entity == gp.player) {
                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;
                String s = "" + entity.inventory.get(i).amount;
                amountX = getXforAlightToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                // SHADOW
                g2.setColor(new Color(60,60,60));
                g2.drawString(s, amountX, amountY);
                // NUMBER
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX-3, amountY-3);


            }

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // CURSOR
        if (cursor) {
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            // DRAW CURSOR
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10,10);

            // DESCRIPTION FRAME
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize*3;



            // DRAW DES TEXT
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex = getItemIndexOnSlot(slotCol,slotRow);

            if (itemIndex < entity.inventory.size()){

                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

                for (String line: entity.inventory.get(itemIndex).description.split("\n")){

                    g2.drawString(line, textX, textY);
                    textY += 32;
                }

            }
            // DRAW COIN WINDOW
            int x = (int) (gp.tileSize*15.5);
            int y = (int) (gp.tileSize*5.5);
            int width = (int) (gp.tileSize*2.5);
            int height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8 , 32,32,null);

            String text = "" + gp.player.coin;
            x = getXforAlightToRightText(text,gp.tileSize*18-20);
            g2.drawString(text, x, y+34);
        }
    }
    private void drawMessage() {

        int messageX = (int)(gp.tileSize*1.5);
        int messageY = gp.tileSize*2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);

                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i,counter);
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }

    }
    private void drawPlayerLife() {

        //gp.player.life = 7;

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // MAX LIFE
        while (i < gp.player.maxLife/2) {
            g2.drawImage(heart03, x, y, null);
            i++;
            x += gp.tileSize + 5;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart02, x, y, null);
            i++;
            if (i < gp.player.life){
                g2.drawImage(heart01, x, y, null);
            }
            i++;
            x += gp.tileSize + 5;
        }

        // DRAW Max MANA
        x = gp.tileSize / 2;
        y = (int)(gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.maxMana) {
            g2.drawImage(manaEmpty, x, y, null);
            i++;
            y += gp.tileSize;
        }

// DRAW MANA
        x = gp.tileSize / 2;
        y = (int)(gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.mana) {
            g2.drawImage(manaFull, x, y, null);
            i++;
            y += gp.tileSize;
        }
    }
    private void drawTransition() {
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        if (counter == 50){ // the transition is done
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
            gp.changeArea();
        }

    }
    private void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        // GAME OVER Text
        text = "GAME OVER";
        // Shadow
        g2.setColor(Color.BLACK);
        x = getXforCenterText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x - 4, y - 4);

        // Retry Text
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenterText(text);
        y += gp.tileSize * 4;
        // Shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x - 4, y - 4);

        if (commandNum == 0) {
            g2.setColor(Color.WHITE);
            g2.drawString(">", x - 40 - 4, y - 4); // Тінь для стрілки
        }

        // Quit Text
        text = "Quit";
        x = getXforCenterText(text);
        y += 55;
        // Shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x - 4, y - 4);

        if (commandNum == 1) {
            g2.setColor(Color.WHITE);
            g2.drawString(">", x - 40 - 4, y - 4); // Тінь для стрілки
        }
    }
    private void drawOptionScreen() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState){
            case 0: options_top(frameX, frameY);break;
            case 1: options_fullScreenNotification(frameX,frameY);break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }
        gp.keyH.enterPressed = false;
    }
    private void drawCharacterScreen() {

        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight= gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 38;

        // NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defence", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        /*g2.drawString("Coin", textX, textY);*/
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);

        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value;



        value = String.valueOf(gp.player.level);
        textX = getXforAlightToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlightToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlightToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlightToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlightToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlightToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defence);
        textX = getXforAlightToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp  + "/" + gp.player.nextLevelUp);
        textX = getXforAlightToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlightToRightText(value, tailX);
        /*g2.drawString(value, textX, textY);*/
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 15, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 15, null);


    }
    public void drawTitleScreen() {

        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "In The Woods";
        int x = getXforCenterText(text);
        int y = gp.tileSize*3;

        // SHADOW
        g2.setColor(Color.darkGray);
        g2.drawString(text, x+5 , y+5);

        // MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // CHARACTER IMAGE
        x = gp.screenWidth/2- (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.goldClever.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXforCenterText(text);
        y += gp.tileSize*3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);
        }

    }
    public void drawPausedScreen (){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        String text = "PAUSED";
        int x = getXforCenterText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

    }
    public void drawDialogueScreen() {

        // WINDOW
        int x = gp.tileSize*3;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*6);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null){

            //currentDialog = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];

             char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();

             if (charIndex < characters.length){

                 gp.playSE(13);
                 String s = String.valueOf(characters[charIndex]);
                 combinedText = combinedText + s;
                 currentDialog = combinedText;

                 charIndex++;
             }

            if (gp.keyH.enterPressed){

                charIndex = 0;
                combinedText = "";

                if (gp.gameState == gp.dialogueState){

                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }

        }
        else {
            npc.dialogueIndex = 0;

            if (gp.gameState == gp.dialogueState) {
                gp.gameState = gp.playState;
            }
        }


        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow) {
        int ItemIndex = slotCol + slotRow *5;
        return ItemIndex;
    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }
    public int getXforCenterText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
    public int getXforAlightToRightText(String text, int tailX) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }
}
