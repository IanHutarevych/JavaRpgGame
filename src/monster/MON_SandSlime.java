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
        idle = false;

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

        if (!onPath && tileDistance < 2){

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
            dropItem(new OBJ_Key(gp));
        }
        if (i >=75){
            dropItem(new OBJ_Coin_Silver(gp));
        }
    }
}