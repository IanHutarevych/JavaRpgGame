package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaStar;
import object.OBJ_Slize;

import java.util.Random;

public class MON_Ogre extends Entity{

    GamePanel gp;

    public MON_Ogre(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Ogre";
        defSpeed = 1;
        speed = defSpeed;
        maxLife = 30;
        life = maxLife;
        attack = 12;
        defence = 2;
        exp = 15;
        knockBackPower = 5;


        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;

        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage(){
        up1 = setup("/monster/Ogre_up1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/Ogre_up2",gp.tileSize, gp.tileSize);

        down1 = setup("/monster/Ogre_down1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/Ogre_down2",gp.tileSize, gp.tileSize);

        left1 = setup("/monster/Ogre_left1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/Ogre_left2",gp.tileSize, gp.tileSize);

        right1 = setup("/monster/Ogre_right1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/Ogre_right2",gp.tileSize, gp.tileSize);
    }
    public void getAttackImage() {
        attackUp1 = setup("/monster/Ogre_attackUp1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/monster/Ogre_attackUp2", gp.tileSize, gp.tileSize*2);

        attackDown1 = setup("/monster/Ogre_attackDown1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/monster/Ogre_attackDown2", gp.tileSize, gp.tileSize*2);

        attackRight1 = setup("/monster/Ogre_attackRight1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/monster/Ogre_attackRight2", gp.tileSize*2, gp.tileSize);

        attackLeft1 = setup("/monster/Ogre_attackLeft1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/monster/Ogre_attackLeft2", gp.tileSize*2, gp.tileSize);

    }
    public void setAction(){

        if (onPath){

            // check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);

            // search the direction to go
            searchPathToPlayer(getGoalCol(gp.player), getGoalRow(gp.player));

        }
        else {
            // check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);

            // get a random direction
            getRandomDirection();
        }

        // check if it attacks
        if (!attacking){
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);
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
