package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX, screenY;
    int standCounter = 0;
    public final int defSpeed = 4;
    public boolean attackCanceled = false;
    int SECounter = 0;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;



    public Player(GamePanel gp, KeyHandler keyH) {

        super (gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/3 + gp.tileSize * 2;
        screenY = gp.screenHeight/3 + gp.tileSize;

        // COLLISION DETECTION
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        // ATTACK AREA
        /*attackArea.width = 36;
        attackArea.height = 36;*/

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = defSpeed;
        direction = "down";

        // PLAYER STATES
        level = 1;
        maxLife = 10;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelUp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defence = getDefence();
    }
    public void setItems(){

        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Boots(gp));

    }
    private int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    private int getDefence() {
        return defence = dexterity * currentShield.defenceValue;
    }
    public void getPlayerImage(){



            up1 = setup("/player/up2", gp.tileSize, gp.tileSize);
            up2 = setup("/player/up1", gp.tileSize, gp.tileSize);
            down1 = setup("/player/down1", gp.tileSize, gp.tileSize);
            down2 = setup("/player/down2", gp.tileSize, gp.tileSize);
            left1 = setup("/player/left1", gp.tileSize, gp.tileSize);
            left2 = setup("/player/left2", gp.tileSize, gp.tileSize);
            right1 = setup("/player/right1", gp.tileSize, gp.tileSize);
            right2 = setup("/player/right2", gp.tileSize, gp.tileSize);

    }
    public void getPlayerAttackImage(){
        if (currentWeapon.type == type_sword) {
        attackUp1 = setup("/player/upAttack1",gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/player/upAttack2",gp.tileSize, gp.tileSize*2);

        attackDown1 = setup("/player/downAttack1",gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/player/downAttack2",gp.tileSize, gp.tileSize*2);

        attackLeft1 = setup("/player/leftAttack1",gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/player/leftAttack2",gp.tileSize*2, gp.tileSize);

        attackRight1 = setup("/player/rightAttack1",gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/player/rightAttack2",gp.tileSize*2, gp.tileSize);
        }
        if (currentWeapon.type == type_axe){
            attackUp1 = setup("/player/axe_up1",gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/axe_up2",gp.tileSize, gp.tileSize*2);

            attackDown1 = setup("/player/axe_down1",gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/axe_down2",gp.tileSize, gp.tileSize*2);

            attackLeft1 = setup("/player/axe_left1",gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/axe_left2",gp.tileSize*2, gp.tileSize);

            attackRight1 = setup("/player/axe_right1",gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/axe_right2",gp.tileSize*2, gp.tileSize);
        }
    }
    public void update(){

        if (attacking){
            attacking();
        }
        else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){
            if (keyH.upPressed){
                direction = "up";
                SECounter++;
                if (SECounter == 15) {
                   // gp.playSE(8);
                    SECounter = 0;
                }
            } else if (keyH.downPressed){
                direction = "down";
                SECounter++;
                if (SECounter == 15) {
                   // gp.playSE(8);
                    SECounter = 0;
                }
            } else if (keyH.leftPressed){
                direction = "left";
                SECounter++;
                if (SECounter == 15) {
                    //gp.playSE(8);
                    SECounter = 0;
                }
            } else if (keyH.rightPressed) {
                direction = "right";
                SECounter++;
                if (SECounter == 15) {
                   // gp.playSE(8);
                    SECounter = 0;
                }
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);


            // CHECK EVENT
            gp.eHandler.checkEvent();



            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.enterPressed){

                switch (direction){
                    case "up":worldY -= speed;break;
                    case "down":worldY += speed;break;
                    case "left": worldX -= speed;break;
                    case "right":worldX += speed;break;
                }
            }
            if (keyH.enterPressed && !attackCanceled){
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 10){
                if (spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;

            if (standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }

        if (gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30){

            // SET DEF COORDINATES, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            // ADD IT TO THE LIST
            gp.projectileList.add(projectile);
            gp.playSE(10);
            shotAvailableCounter = 0;
        }

        // This needs to be outside of key if statement!

        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
    }
    private void attacking() {
    spriteCounter++;

    if (spriteCounter <= 5){
        spriteNum = 1;
    }
    if (spriteCounter > 5 && spriteCounter<= 25){
        spriteNum = 2;

        // Save the current worldX, worldY, solidArea
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        switch (direction){
            case "up":worldY -= attackArea.height; break;
            case "down":worldY += attackArea.height; break;
            case "left":worldX -= attackArea.width; break;
            case "right":worldX += attackArea.width; break;
        }


        solidAreaWidth = attackArea.width;
        solidAreaHeight = attackArea.height;

        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        damageMonster(monsterIndex, attack);

        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;


        }
    if (spriteCounter > 25){
        spriteNum = 1;
        spriteCounter = 0;
        attacking = false;
    }
    }
    public void damageMonster(int i, int attack) {

        if (i != 999) {
            if (!gp.monster[i].invincible){
                gp.playSE(5);

                int damage = attack - gp.monster[i].defence;
                if (damage < 0){
                    damage = 0;
                }

                gp.monster[i].life -= damage;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0 ){
                    gp.monster[i].dying = true;
                    //gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp +" + gp.monster[i].exp);
                    exp += gp.monster[i].exp;
                    checkLvlUp();
                }
            }
        }
    }
    private void checkLvlUp() {

        if (exp >= nextLevelUp){
            level++;
            nextLevelUp = nextLevelUp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defence = getDefence();

            gp.playSE(7);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialog = "You are level " + level + " now!";
        }

    }
    private void contactMonster(int i) {
        if (i != 999){
            if (!invincible && !gp.monster[i].dying) {
                gp.playSE(6);
                int damage = gp.monster[i].attack - defence;
                if (damage < 0){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }
    public void pickUpObject(int i) {
        if (i != 999) {
            String text;
            if (!gp.obj[i].collision && !"door_open".equals(gp.obj[i].name)) {
                if (inventory.size() != maxInventorySize) {

                    inventory.add(gp.obj[i]);
                    gp.playSE(5); // coin sound
                    text = "Got a " + gp.obj[i].name + "!";

                } else {
                    text = "You cannot carry anymore";
                }
                gp.ui.addMessage(text);
                gp.obj[i] = null;

            }
        }
    }
    public void interactNPC(int i) {
        if (gp.keyH.enterPressed) {
            if (i != 999) {
                attackCanceled = true;

                if (gp.gameState == gp.dialogueState) {
                    gp.npc[i].speak();
                    if (gp.ui.currentDialog == null) {
                        gp.gameState = gp.playState;
                    }
                } else {
                    gp.gameState = gp.dialogueState;
                    gp.npc[i].speak();
                }
            }
        }
    }
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();

        if (itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defence = getDefence();
            }
            if (selectedItem.type == type_consumable) {

                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if (attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                if (attacking) {
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                if (attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                if (attacking) {
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                break;
        }
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // RESET alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


    }
}
