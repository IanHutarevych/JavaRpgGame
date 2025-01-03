package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaStar;
import object.OBJ_Slize;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Green Slime";
        speed = 1;
        maxLife = 8;
        life = maxLife;
        attack = 5;
        defence = 0;
        exp = 2;
        projectile = new OBJ_Slize(gp);


        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/monster/slime_jump_1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/slime_jump_2",gp.tileSize, gp.tileSize);

        down1 = setup("/monster/slime_jump_1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/slime_jump_2",gp.tileSize, gp.tileSize);

        left1 = setup("/monster/slime_jump_1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/slime_jump_2",gp.tileSize, gp.tileSize);

        right1 = setup("/monster/slime_jump_1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/slime_jump_2",gp.tileSize, gp.tileSize);
    }

    public void setAction(){
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

        int i = new Random().nextInt(100)+1;
        if (i > 99 && !projectile.alive && shotAvailableCounter == 30){
            projectile.set(worldX,worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    public void damageReaction(){

        actionLockCounter = 0;
        direction = gp.player.direction;
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
            dropItem(new OBJ_ManaStar(gp));
        }
    }
}
