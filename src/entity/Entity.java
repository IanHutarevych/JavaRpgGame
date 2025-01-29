package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Entity {

    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,attackRight2;
    public BufferedImage image1, image2, image3;
    public BufferedImage guardUp, guardDown, guardLeft, guardRight;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public String[][] dialogues = new String[20][20];
    public Entity attacker;

    // STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean invincible = false;
    public boolean collisionOn = false;
    public boolean attacking = false;
    boolean idle = true;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean  offBalance = false;
    public Entity loot;
    public boolean opened = false;

    // COUNTER
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int spriteCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    public int offBalanceCounter = 0;

    // CHARACTER ATTRIBUTE
    public String name;
    public int defSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defence;
    public int exp;
    public int nextLevelUp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;
    public Entity currentLight;

    // ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int attackValue;
    public int defenceValue;
    public String description = "";
    public int useCost;
    public int value;
    public int price;
    public int knockBackPower;
    public int knockBackSkill;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;


    // TYPE
    public int type; // 0 - player, 1 - npc, 2 - monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shieldWood = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_michael_sword = 8;
    public final int type_obstacle = 9;
    public final int type_watering = 10;
    public final int type_light = 11;
    public final int type_shieldMetal = 12;
    public final int type_key = 13;



    public Entity(GamePanel gp) {
        this.gp = gp;

    }
    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        } else if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;


            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;


            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == type_monster) {
                if (gp.cChecker.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            } else { // Player

                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }


        if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void damagePlayer(int attack){
        if (!gp.player.invincible){

            int damage = attack - gp.player.defence;

            // get an opposite direction
            String canGuardDirection = getOppositeDirection(direction);
            if (gp.player.guarding && gp.player.direction.equals(canGuardDirection)){

                // parry
                if (gp.player.guardCounter < 10){
                    damage = 0;
                    //gp.playSE(1231);
                    setKnockBack(this, gp.player, knockBackPower, knockBackSkill);
                    offBalance = true;
                    spriteCounter =- 60;
                }
                else {
                    // normal guard
                    damage /= 3;
                    gp.playSE(9);
                }
            } else {
                // player can give damage
                gp.playSE(6);

                if (damage<1){
                    damage = 1;
                }
            }

            if (damage != 0){
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower, knockBackSkill);
            }

            gp.player.life -= damage;

            gp.player.invincible = true;
        }
    }
    public String getOppositeDirection(String direction){
        String oppositeDirection = "";

        switch (direction) {
            case "up": oppositeDirection = "down"; break;
            case "down": oppositeDirection = "up"; break;
            case "left": oppositeDirection = "right"; break;
            case "right": oppositeDirection = "left"; break;
        }
        return oppositeDirection;
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower, int knockBackSkill) {

        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower + knockBackSkill;
        target.knockBack = true;
    }
    public void setAction () {}
    public void damageReaction() {}
    public void speak() {


    }
    public void facePlayer() {
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void startDialogue (Entity entity, int setNum) {

        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }
    public void interact(){}
    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNode(startCol,startRow, goalCol, goalRow, this);

        if (gp.pFinder.search()){

            // Next worldX & worldY
            int nextX = gp.pFinder.pathList.getFirst().col * gp.tileSize;
            int nextY = gp.pFinder.pathList.getFirst().row * gp.tileSize;

            // Entity`s solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                // left or right
                if (enLeftX > nextX){
                    direction = "left";
                }
                if (enLeftX < nextX){
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX){
                // up or left
                direction = "up";
                checkCollision();
                if (collisionOn){
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX){
                // up or right
                direction = "up";
                checkCollision();
                if (collisionOn){
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX){
                // down or left
                direction = "down";
                checkCollision();
                if (collisionOn){
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX){
                // down or right
                direction = "down";
                checkCollision();
                if (collisionOn){
                    direction = "right";
                }
            }

            // If reaches the goal, stop the search
            int nextCol = gp.pFinder.pathList.getFirst().col;
            int nextRow = gp.pFinder.pathList.getFirst().row;
            if (nextCol == goalCol && nextRow == goalRow){
                onPath = false;
            }
        }
    }
    public void searchPathToPlayer(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNode(startCol,startRow, goalCol, goalRow, this);

        if (gp.pFinder.search()){

            // Next worldX & worldY
            int nextX = gp.pFinder.pathList.getFirst().col * gp.tileSize;
            int nextY = gp.pFinder.pathList.getFirst().row * gp.tileSize;

            // Entity`s solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                // left or right
                if (enLeftX > nextX){
                    direction = "left";
                }
                if (enLeftX < nextX){
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX){
                // up or left
                direction = "up";
                checkCollision();
                if (collisionOn){
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX){
                // up or right
                direction = "up";
                checkCollision();
                if (collisionOn){
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX){
                // down or left
                direction = "down";
                checkCollision();
                if (collisionOn){
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX){
                // down or right
                direction = "down";
                checkCollision();
                if (collisionOn){
                    direction = "right";
                }
            }
        }
    }
    public void checkCollision(){
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this,gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (type == type_monster && contactPlayer){
            damagePlayer(attack);
        }
    }
    public int getXDistance(Entity target) {
        return Math.abs(worldX - target.worldX);
    }
    public int getYDistance(Entity target) {
        return Math.abs(worldY - target.worldY);
    }
    public int getTileDistance(Entity target) {
        return (getXDistance(target) + getYDistance(target)/gp.tileSize);
    }
    public int getGoalCol(Entity target) {
        return (target.worldX + target.solidArea.x)/gp.tileSize;
    }
    public int getGoalRow(Entity target) {
        return (target.worldY + target.solidArea.y)/gp.tileSize;
    }
    public void update() {

        if (knockBack){
             checkCollision();

             if (collisionOn){
                 knockBackCounter = 0;
                 knockBack = false;
                 speed = defSpeed;
             }
             else if (!collisionOn){
                 switch (knockBackDirection){
                     case "up":worldY -= speed;break;
                     case "down":worldY += speed;break;
                     case "left": worldX -= speed;break;
                     case "right":worldX += speed;break;
                 }
             }

             knockBackCounter++;
             if (knockBackCounter == 10){ // KnockBack distance
                 knockBackCounter = 0;
                 knockBack = false;
                 speed = defSpeed;
             }
        }
        else if (attacking){
            attacking();
        }
        else {
            setAction();
            checkCollision();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn){

                switch (direction){
                    case "up":worldY -= speed;break;
                    case "down":worldY += speed;break;
                    case "left": worldX -= speed;break;
                    case "right":worldX += speed;break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 20){
                if (spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if (offBalance){
            offBalanceCounter++;
            if (offBalanceCounter > 60){
                offBalance = false;
                offBalanceCounter = 0;
            }
        }
    }
    public void draw (Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX > gp.player.worldX - gp.player.screenX - 2 * gp.tileSize &&
                worldX < gp.player.worldX + gp.player.screenX + 2 * gp.tileSize &&
                worldY > gp.player.worldY - gp.player.screenY - 2 * gp.tileSize &&
                worldY < gp.player.worldY + gp.player.screenY + 2 * gp.tileSize) {

            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {
                case "up":
                    if (attacking) { // Якщо атакує
                        tempScreenY = screenY - gp.tileSize;
                        if (spriteNum == 1) {
                            image = attackUp1;
                        }
                        if (spriteNum == 2) {
                            image = attackUp2;
                        }
                    } else { // Якщо рухається
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                    }
                    break;

                case "down":

                    if (attacking) { // Якщо атакує
                        if (spriteNum == 1) {
                            image = attackDown1;
                        }
                        if (spriteNum == 2) {
                            image = attackDown2;
                        }
                    } else { // Якщо рухається
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                    }
                    break;

                case "left":
                    if (attacking) { // Якщо атакує
                        tempScreenX = screenX - gp.tileSize;
                        if (spriteNum == 1) {
                            image = attackLeft1;
                        }
                        if (spriteNum == 2) {
                            image = attackLeft2;
                        }
                    } else { // Якщо рухається
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                    }
                    break;

                case "right":
                    if (attacking) { // Якщо атакує
                        if (spriteNum == 1) {
                            image = attackRight1;
                        }
                        if (spriteNum == 2) {
                            image = attackRight2;
                        }
                    } else { // Якщо рухається
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                    }
                    break;
            }
            // Monster HP bar
            if (type == 2 && hpBarOn) {

                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;


                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);


                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                hpBarCounter++;

                if (hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dying){
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY,null);

            changeAlpha(g2, 1f);
        }

    }
    public void getRandomDirection(){
        actionLockCounter ++;

        if (actionLockCounter == 120){
            Random r = new Random();

            int i = r.nextInt(100)+1; // pick up a number from 1 tp 100


            // EASY AI FOR NPC
            if (i <= 25){direction = "up";}
            if (i <= 50 && i > 25){direction = "down";}
            if (i <= 75 && i > 50){direction = "left";}
            if (i > 75){direction = "right";}
            actionLockCounter = 0;
        }
    }
    public void checkAttackOrNot(int rate, int straight, int horizontal) {

        boolean targetInRage = false;
        int xDis = getXDistance(gp.player);
        int yDis = getYDistance(gp.player);

        switch (direction){
            case "up": if (gp.player.worldY < worldY && yDis < straight && xDis < horizontal){targetInRage = true;}break;
            case "down": if (gp.player.worldY > worldY && yDis < straight && xDis < horizontal){targetInRage = true;} break;
            case "left": if (gp.player.worldX < worldX && xDis < straight && yDis < horizontal){targetInRage = true;} break;
            case "right": if (gp.player.worldX > worldX && xDis < straight && yDis < horizontal){targetInRage = true;} break;
        }
        if (targetInRage){
            // check if it initiates an attack
            int i = new Random().nextInt(rate);
            if (i == 0){
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }
    public void checkShootOrNot(int rate, int shotInterval){
        int i = new Random().nextInt(rate);
        if (i == 0 && !projectile.alive && shotAvailableCounter == shotInterval){
            projectile.set(worldX,worldY, direction, true, this);
            for (int j = 0; j < gp.projectile[1].length; j++) {
                if (gp.projectile[gp.currentMap][j] == null){
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }
    public void checkStartChasingOrNot( Entity target, int distance, int rate){
        if (getTileDistance(target) < distance){
            int i = new Random().nextInt(rate);
            if (i == 0){
                onPath = true;
            }
        }
    }
    public void checkStopChasingOrNot( Entity target, int distance, int rate){
        if (getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if (i == 0){
                onPath = false;
            }
        }
    }
    private void dyingAnimation(Graphics2D g2) {

        dyingCounter++;
        int i = 5;

        if (dyingCounter <= i){changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*8){
            alive = false;
        }

    }
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e ){
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Error: Image file not found for path: " + imagePath);
        }
        return image;
    }
    public boolean use (Entity e){
        return false;
    }
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for (int i = 0; i <gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public Color getParticleColor(){
        Color color = null;
        return color;
    }
    public int getParticleSize(){
        int size = 0;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 0;
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);

    }
    public void setLoot(Entity loot) {}
    public void resetCounter() {
        actionLockCounter = 0;
        invincibleCounter = 0;
        spriteCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }
}