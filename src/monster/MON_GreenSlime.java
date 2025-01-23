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
        defSpeed = 1;
        speed = defSpeed;
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

        if (onPath){

            // check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);

            // search the direction to go
            searchPathToPlayer(getGoalCol(gp.player), getGoalRow(gp.player));

            // check if it shoots a projectile
            checkShootOrNot(200, 30);
        }
        else {
            // check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);

            // get a random direction
            getRandomDirection();
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
            dropItem(new OBJ_ManaStar(gp));
        }
    }
}
