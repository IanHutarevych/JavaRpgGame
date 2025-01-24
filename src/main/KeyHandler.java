package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, ePressed, shotKeyPressed, spacePressed;

    // DEBUG
    boolean showDebugText = false;


    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // TITLE STATE
         if (gp.gameState == gp.titleState) {
             titleState(code);
        }
        // PLAY STATE
        else if (gp.gameState == gp.playState) {
            playState(code);
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        // CHARACTER STATE
         else if (gp.gameState == gp.characterState) {
             characterState(code);
         }
         // OPTIONS STATE
         else if (gp.gameState == gp.optionsState) {
             optionState(code);
         }
         // GAME OVER STATE
         else if (gp.gameState == gp.gameOverState) {
             try {
                 gameOverState(code);
             } catch (IOException ex) {
                 throw new RuntimeException(ex);
             }
         }
         // TRADE STATE
         else if (gp.gameState == gp.tradeState){
             tradeState(code);
         }
         // MAP STATE
         else if (gp.gameState == gp.mapState){
             mapState(code);
         }

    }

    private void mapState(int code) {
        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.playState;
        }
    }

    private void tradeState(int code) {

        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSE(9);
            }

            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSE(9);
            }
        }
        if (gp.ui.subState == 1){
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
        if (gp.ui.subState == 2){
            playerInventory( code);
            if (code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
    }
    private void gameOverState(int code) throws IOException {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_ENTER){
            if (gp.ui.commandNum == 0){
                gp.gameState= gp.playState;
                gp.resetGame(false);
                gp.playMusic(0);
            }
            else if (gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
    }
    private void optionState(int code) {
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState){
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            gp.playSE(9);
            if (gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            gp.playSE(9);
            if (gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            if (gp.ui.subState == 0){
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0){
                    gp.se.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            if (gp.ui.subState == 0){
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5){
                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }
    }
    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }
    public void titleState(int code){
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN
        ) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                //gp.playMusic(0);
            }
            if (gp.ui.commandNum == 1) {
                // add later
                gp.sl.load();
                gp.gameState = gp.playState;
            }
            if (gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }
    public void playState(int code){
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            //gp.stopMusic();
            gp.gameState = gp.optionsState;
        }
        if (code == KeyEvent.VK_E) {
            ePressed = true;
        } if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_M)  {
            gp.gameState = gp.mapState;
        }
        if (code == KeyEvent.VK_SPACE)  {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_J)  {
            if (!gp.map.miniMapOn){
                gp.map.miniMapOn = true;
            }
            else {
                gp.map.miniMapOn = false;
            }
        }


        // DEBUG
        if (code == KeyEvent.VK_T) {
            if (!showDebugText) {
                showDebugText = true;
                gp.tileM.drawPath = true;
            } else {
                showDebugText = false;
                gp.tileM.drawPath = false;
            }
        }
        else if (code == KeyEvent.VK_R) {
            switch (gp.currentMap){
                case 0: gp.tileM.loadMap("/maps/world01.txt", 0); break;
                case 1: gp.tileM.loadMap("/maps/shop.txt",1); break;
                case 2: gp.tileM.loadMap("/maps/pier.txt", 2); break;
            }
        }
    }
    public void pauseState(int code){
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code){
        if (code == KeyEvent.VK_C || code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
        playerInventory(code);
    }
    public void playerInventory(int code){
        if (code == KeyEvent.VK_W) {
            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
                gp.playSE(9);
            }
        }
    }

    public void npcInventory(int code){
        if (code == KeyEvent.VK_W) {
            if (gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSE(9);
            }
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_E) {
            ePressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
