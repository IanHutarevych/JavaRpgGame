package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_SandSlime extends Entity {

    GamePanel gp;

    public MON_SandSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Sand Slime";
        defSpeed = 1;
        speed = defSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 6;
        defence = 1;
        exp = 4;
        projectile = new OBJ_SandSlize(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setup("/monster/slimeSand_jump_1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/slimeSand_jump_2",gp.tileSize, gp.tileSize);

        down1 = setup("/monster/slimeSand_jump_1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/slimeSand_jump_2",gp.tileSize, gp.tileSize);

        left1 = setup("/monster/slimeSand_jump_1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/slimeSand_jump_2",gp.tileSize, gp.tileSize);

        right1 = setup("/monster/slimeSand_jump_1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/slimeSand_jump_2",gp.tileSize, gp.tileSize);
    }
    public void update(){

        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if (!onPath && tileDistance < 5){

            int i = new Random().nextInt(100)+1;
            if (i > 50){
                onPath = true;
            }
        }
        if (onPath && tileDistance > 20){
            onPath = false;
        }
    }
    public void setAction(){
        if (onPath){


            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPathToPlayer(goalCol, goalRow);

            int i = new Random().nextInt(200)+1;
            if (i > 197 && !projectile.alive && shotAvailableCounter == 30){
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
    public void damageReaction(){

        actionLockCounter = 0;
        onPath = true;
    }
    @Override
    public void checkDrop(){
        int i = new Random().nextInt(100)+1;
        if (i < 50){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >=50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if (i >=75 && i < 100){
            dropItem(new OBJ_Coin_Silver(gp));
        }
    }
}