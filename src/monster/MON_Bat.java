package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_Bat extends Entity {

    GamePanel gp;

    public MON_Bat(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Bat";
        defSpeed = 4;
        speed = defSpeed;
        maxLife = 12;
        life = maxLife;
        attack = 5;
        defence = 2;
        exp = 8;


        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        idle = false;

        getImage();
    }

    public void getImage(){
        up1 = setup("/monster/bat_1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/bat_2",gp.tileSize, gp.tileSize);

        down1 = setup("/monster/bat_1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/bat_2",gp.tileSize, gp.tileSize);

        left1 = setup("/monster/bat_1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/bat_2",gp.tileSize, gp.tileSize);

        right1 = setup("/monster/bat_1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/bat_2",gp.tileSize, gp.tileSize);
    }
    public void setAction(){

        if (onPath){

/*            // check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);

            // search the direction to go
            searchPathToPlayer(getGoalCol(gp.player), getGoalRow(gp.player));

            // check if it shoots a projectile
            checkShootOrNot(200, 30);*/
        }
        else {
            // check if it starts chasing
            /*checkStartChasingOrNot(gp.player, 5, 100);*/

            // get a random direction
            getRandomDirection();
        }
    }
    public void damageReaction(){

        actionLockCounter = 0;
        //onPath = true;
    }
    @Override
    public void checkDrop(){
        int i = new Random().nextInt(100)+1;
        if (i < 50){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >=50 && i < 75){
            dropItem(new OBJ_Coin_Silver(gp));
        }
        if (i >=75 && i < 100){
            dropItem(new OBJ_Key(gp));
        }
    }
}
