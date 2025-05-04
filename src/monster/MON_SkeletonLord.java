package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Silver;
import object.OBJ_Gold;
import object.OBJ_Key;

import java.util.Random;

public class MON_SkeletonLord extends Entity {

    GamePanel gp;
    public static final String monName = "Skeleton Lord";

    public MON_SkeletonLord(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        boss = true;
        name = "Skeleton Lord";
        defSpeed = 1;
        speed = defSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 16;
        defence = 2;
        exp = 50;
        knockBackPower = 5;

        int size = gp.tileSize*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;
        motion1_duration = 25;
        motion2_duration = 50;
        idle = false;

        getImage();
        getAttackImage();
    }

    public void getImage() {

        int i = 3;

        if (!inRage) {
            up1 = setup("/monster/skeletonlord_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monster/skeletonlord_up_2", gp.tileSize * i, gp.tileSize * i);

            down1 = setup("/monster/skeletonlord_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monster/skeletonlord_down_2", gp.tileSize * i, gp.tileSize * i);

            left1 = setup("/monster/skeletonlord_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monster/skeletonlord_left_2", gp.tileSize * i, gp.tileSize * i);

            right1 = setup("/monster/skeletonlord_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monster/skeletonlord_right_2", gp.tileSize * i, gp.tileSize * i);
        }

        if (inRage) {
            up1 = setup("/monster/skeletonlord_phase2_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monster/skeletonlord_phase2_up_2", gp.tileSize * i, gp.tileSize * i);

            down1 = setup("/monster/skeletonlord_phase2_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monster/skeletonlord_phase2_down_2", gp.tileSize * i, gp.tileSize * i);

            left1 = setup("/monster/skeletonlord_phase2_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monster/skeletonlord_phase2_left_2", gp.tileSize * i, gp.tileSize * i);

            right1 = setup("/monster/skeletonlord_phase2_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monster/skeletonlord_phase2_right_2", gp.tileSize * i, gp.tileSize * i);
        }
    }
    public void getAttackImage() {

        int i = 3;

        if (!inRage) {
            attackUp1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);

            attackDown1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);

            attackRight1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);

            attackLeft1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
        }

        if (inRage) {
            attackUp1 = setup("/monster/skeletonlord_phase2_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/monster/skeletonlord_phase2_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);

            attackDown1 = setup("/monster/skeletonlord_phase2_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/monster/skeletonlord_phase2_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);

            attackRight1 = setup("/monster/skeletonlord_phase2_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/monster/skeletonlord_phase2_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);

            attackLeft1 = setup("/monster/skeletonlord_phase2_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/monster/skeletonlord_phase2_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
        }
    }
    public void setAction(){

        if (!inRage && life < maxLife/2) {
            inRage = true;
            getImage();
            getAttackImage();
            defSpeed++;
            speed = defSpeed;
            attack *=2;
        }

        if (getTileDistance(gp.player) < 10){
            moveTowardPlayer(60);
        }
        else {

            // get a random direction
            getRandomDirection();
        }

        // check if it attacks
        if (!attacking){
            checkAttackOrNot(60, gp.tileSize*7, gp.tileSize*5);
        }
    }
    public void damageReaction(){

        actionLockCounter = 0;
    }

    @Override
    public void checkDrop(){
        int i = new Random().nextInt(100)+1;
        if (i < 50){
            dropItem(new OBJ_Coin_Silver(gp));
        }
        if (i >=50 && i < 75){
            dropItem(new OBJ_Gold(gp));
        }
        if (i >=75 && i < 100){
            dropItem(new OBJ_Key(gp));
        }
    }

}
