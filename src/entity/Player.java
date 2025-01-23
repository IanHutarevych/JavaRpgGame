package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX, screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;

    public BufferedImage idleUp1, idleUp2, idleDown1, idleDown2, idleLeft1, idleLeft2, idleRight1, idleRight2, head;




    public Player(GamePanel gp, KeyHandler keyH) {

        super (gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/3 + gp.tileSize * 2;
        screenY = gp.screenHeight/3 + gp.tileSize;

        // COLLISION DETECTION
        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 27;
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

        /*worldX = gp.tileSize * 27;
        worldY = gp.tileSize * 21;*/
        worldX = gp.tileSize * 17;
        worldY = gp.tileSize * 16;

        defSpeed = 4;
        speed = defSpeed;
        direction = "down";
        knockBackSkill = 0;

        // PLAYER STATES
        level = 1;
        maxLife = 10;
        life = maxLife;
        maxMana = 1;
        mana = maxMana;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelUp = 5;
        coin = 10;
        currentWeapon = new OBJ_Archangel_Sword(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defence = getDefence();
    }
    public void setDefaultPositions(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 20;
        direction = "down";

    }
    public void restoreLifeAndMane(){
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }
    public void setItems(){

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Torch(gp));
        inventory.add(new OBJ_Potion_Recovery_Big(gp));

    }
    private int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }
    private int getDefence() {
        return defence = dexterity * currentShield.defenceValue;
    }
    public void getPlayerImage(){



            up1 = setup("/player/up1", gp.tileSize, gp.tileSize);
            up2 = setup("/player/up2", gp.tileSize, gp.tileSize);
            down1 = setup("/player/down1", gp.tileSize, gp.tileSize);
            down2 = setup("/player/down2", gp.tileSize, gp.tileSize);
            left1 = setup("/player/left1", gp.tileSize, gp.tileSize);
            left2 = setup("/player/left2", gp.tileSize, gp.tileSize);
            right1 = setup("/player/right1", gp.tileSize, gp.tileSize);
            right2 = setup("/player/right2", gp.tileSize, gp.tileSize);

            idleUp1 = setup("/player/idleUp1", gp.tileSize, gp.tileSize);
            idleUp2 = setup("/player/idleUp2", gp.tileSize, gp.tileSize);
            idleDown1 = setup("/player/idleDown1", gp.tileSize, gp.tileSize);
            idleDown2 = setup("/player/idleDown2", gp.tileSize, gp.tileSize);
            idleLeft1 = setup("/player/idleLeft1", gp.tileSize, gp.tileSize);
            idleLeft2 = setup("/player/idleLeft2", gp.tileSize, gp.tileSize);
            idleRight1 = setup("/player/idleRight1", gp.tileSize, gp.tileSize);
            idleRight2 = setup("/player/idleRight2", gp.tileSize, gp.tileSize);
            head = setup("/player/head", gp.tileSize, gp.tileSize);

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
        }if (currentWeapon.type == type_watering){
            attackUp1 = setup("/player/water_up1",gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/water_up2",gp.tileSize, gp.tileSize*2);

            attackDown1 = setup("/player/water_down1",gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/water_down2",gp.tileSize, gp.tileSize*2);

            attackLeft1 = setup("/player/water_left1",gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/water_left2",gp.tileSize*2, gp.tileSize);

            attackRight1 = setup("/player/water_right1",gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/water_right2",gp.tileSize*2, gp.tileSize);
        }
        if (currentWeapon.type == type_michael_sword){
            attackUp1 = setup("/player/mic_up1",gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/mic_up2",gp.tileSize, gp.tileSize*2);

            attackDown1 = setup("/player/mic_down1",gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/mic_down2",gp.tileSize, gp.tileSize*2);

            attackLeft1 = setup("/player/mic_left1",gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/mic_left2",gp.tileSize*2, gp.tileSize);

            attackRight1 = setup("/player/mic_right1",gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/mic_right2",gp.tileSize*2, gp.tileSize);
        }
    }
    public void update() {
        // Якщо атака активна, викликаємо метод атаки
        if (attacking) {
            attacking();
        }
        // Якщо натискаються клавіші для руху або атаки
        else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            handleMovement();  // обробляємо рух
        } else {
            idle = true; // Якщо нічого не натискається - в стан idle
            idleAnimation();  // Викликаємо idle анімацію
        }

        // Перевірка пострілів
        if (gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResources(this)) {
            shootProjectile();
        }

        // Інші перевірки і стан "невразливості"
        handleInvincibility();
        handleResources();
        checkGameOver();
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking) { // Якщо атакує
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) { image = attackUp1; }
                    if (spriteNum == 2) { image = attackUp2; }
                }
                else if (idle) { // Якщо персонаж в стані idle
                    if (spriteNum == 1) { image = idleUp1; }
                    if (spriteNum == 2) { image = idleUp2; }
                } else { // Якщо рухається
                    if (spriteNum == 1) { image = up1; }
                    if (spriteNum == 2) { image = up2; }
                }
                break;

            case "down":

                if (attacking) { // Якщо атакує
                    if (spriteNum == 1) { image = attackDown1; }
                    if (spriteNum == 2) { image = attackDown2; }
                }
                else if (idle) { // Якщо персонаж в стані idle
                    if (spriteNum == 1) {
                        image = idleDown1;
                    }
                    if (spriteNum == 2) {
                        image = idleDown2;
                    }
                }
                else { // Якщо рухається
                    if (spriteNum == 1) { image = down1; }
                    if (spriteNum == 2) { image = down2; }
                }
                break;

            case "left":
                if (attacking) { // Якщо атакує
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) { image = attackLeft1; }
                    if (spriteNum == 2) { image = attackLeft2; }
                }
                else if (idle) { // Якщо персонаж в стані idle
                    if (spriteNum == 1) { image = idleLeft1; }
                    if (spriteNum == 2) { image = idleLeft2; }
                }
                else { // Якщо рухається
                    if (spriteNum == 1) { image = left1; }
                    if (spriteNum == 2) { image = left2; }
                }
                break;

            case "right":  if (attacking) { // Якщо атакує
                if (spriteNum == 1) { image = attackRight1; }
                if (spriteNum == 2) { image = attackRight2; }
            }
            else if (idle) { // Якщо персонаж в стані idle
                if (spriteNum == 1) { image = idleRight1; }
                if (spriteNum == 2) { image = idleRight2; }
            } else { // Якщо рухається
                if (spriteNum == 1) { image = right1; }
                if (spriteNum == 2) { image = right2; }
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
    private void handleMovement() {
        if (keyH.upPressed) {
            direction = "up";
            idle = false;
        } else if (keyH.downPressed) {
            direction = "down";
            idle = false;
        } else if (keyH.leftPressed) {
            direction = "left";
            idle = false;
        } else if (keyH.rightPressed) {
            direction = "right";
            idle = false;
        }

        // Перевірка на зіткнення
        checkCollisions();

        // Якщо немає зіткнень - можна рухатись
        if (!collisionOn && !keyH.enterPressed) {
            moveCharacter();
        }

        if (keyH.enterPressed && !attackCanceled) {
            attacking = true;
            spriteCounter = 0;
        }

        attackCanceled = false;
        gp.keyH.enterPressed = false;

        // Анімація
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
    private void moveCharacter() {
        switch (direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }
    }
    private void checkCollisions() {
        collisionOn = false;
        gp.cChecker.checkTile(this);

        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        contactMonster(monsterIndex);

        int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
        gp.eHandler.checkEvent();
    }
    private void shootProjectile() {
        projectile.set(worldX, worldY, direction, true, this);
        projectile.subtractResources(this);

        // CHECK VACANCY
        for (int i = 0; i < gp.projectile[1].length; i++) {
            if (gp.projectile[gp.currentMap][i] == null) {
                gp.projectile[gp.currentMap][i] = projectile;
                break;
            }
        }

        gp.playSE(10);
        shotAvailableCounter = 0;
    }
    private void handleInvincibility() {
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    private void handleResources() {
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (life > maxLife) {
            life = maxLife;
        }
        if (mana > maxMana) {
            mana = maxMana;
        }
    }
    private void checkGameOver() {
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(12);
        }
    }

    public void idleAnimation() {
        standCounter++; // збільшуємо лічильник кадрів

        if (standCounter == 20) {
            spriteNum = (spriteNum == 1) ? 2 : 1; // чергування між спрайтами
            standCounter = 0; // скидаємо лічильник
        }
    }

    public void damageProjectile(int i) {
        if (i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }
    public void damageInteractiveTile(int i) {
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible && !gp.iTile[gp.currentMap][i].invincible &&gp.iTile[gp.currentMap][i].isCorrectWeapon(this)){
            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if (gp.iTile[gp.currentMap][i].life == 0){
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
                gp.iTile[gp.currentMap][i].checkDrop();
            }
        }
    }
    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {

        if (i != 999) {
            if (!gp.monster[gp.currentMap][i].invincible){
                gp.playSE(5);

                if (knockBackPower > 0){
                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower,  knockBackSkill);
                }

                int damage = attack - gp.monster[gp.currentMap][i].defence;
                if (damage < 0){
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0 ){
                    gp.monster[gp.currentMap][i].dying = true;
                    //gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp +" + gp.monster[gp.currentMap][i].exp);
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLvlUp();
                }
            }
        }
    }
    private void checkLvlUp() {


        if (exp >= nextLevelUp){
            level++;
            nextLevelUp = nextLevelUp*3;
            maxLife += 2;
            maxMana++;
            strength++;
            dexterity++;
            attack++;
            defence++;
            life = maxLife;
            mana = maxMana;

            gp.playSE(7);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialog = "You are level " + level + " now!";
        }
    }
    private void contactMonster(int i) {
        if (i != 999){
            if (!invincible && !gp.monster[gp.currentMap][i].dying) {
                gp.playSE(6);
                int damage = gp.monster[gp.currentMap][i].attack - defence;
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

            // PICK UP ONLY ITEMS
            if (gp.obj[gp.currentMap][i].type == type_pickupOnly){
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }

            else if (gp.obj[gp.currentMap][i].type == type_obstacle){
                if (keyH.enterPressed) {
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            }
            else {

                // INVENTORY ITEMS
                String text;
                if (!gp.obj[gp.currentMap][i].collision && !"door_open".equals(gp.obj[gp.currentMap][i].name)) {
                    if (canObtainItem(gp.obj[gp.currentMap][i])) {

                        gp.playSE(5); // coin sound
                        text = "Got a " + gp.obj[gp.currentMap][i].name + "!";

                    } else {
                        text = "You cannot carry anymore";
                    }
                    gp.ui.addMessage(text);
                    gp.obj[gp.currentMap][i] = null;

                }
            }
        }
    }
    public void interactNPC(int i) {
        if (gp.keyH.enterPressed) {
            if (i != 999) {
                attackCanceled = true;

                if (gp.gameState == gp.dialogueState) {
                    gp.npc[gp.currentMap][i].speak();
                    if (gp.ui.currentDialog == null) {
                        gp.gameState = gp.playState;
                    }
                } else {
                    gp.gameState = gp.dialogueState;
                    gp.npc[gp.currentMap][i].speak();
                }
            }
        }
    }
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword
            || selectedItem.type == type_axe
            || selectedItem.type == type_michael_sword
            || selectedItem.type == type_watering) {

                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defence = getDefence();
            }
            if (selectedItem.type == type_light) {
                if (currentLight == selectedItem){
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if (selectedItem.type == type_consumable) {

                selectedItem.use(this);
                if (selectedItem.amount > 1) {
                    selectedItem.amount--;
                } else {
                    inventory.remove(itemIndex);
                }
            }
        }
    }
    public int searchItemInInventory(String itemName){
        int itemIndex = 999;
        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item){
        boolean canObtain = false;

        // CHECK IF STACKABLE
        if (item.stackable){

            int index = searchItemInInventory(item.name);

            if (index != 999){
                inventory.get(index).amount++;
                canObtain = true;
            }
            else { // This is a new item so need to check vacancy
                if (inventory.size() != maxInventorySize){
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else { // NOT STACKABLE
            if (inventory.size() != maxInventorySize){
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }
    public void getSleepingImage(BufferedImage image){
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;

        idleDown1 = image;
        idleDown2 = image;
        idleLeft1 = image;
        idleLeft2 = image;
        idleRight1 = image;
        idleRight2 = image;
        idleUp1 = image;
        idleUp2 = image;
    }
}
