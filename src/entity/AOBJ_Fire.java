package entity;

import environment.Lightning;
import main.GamePanel;
import object.OBJ_Torch;


public class AOBJ_Fire extends Entity {


    public AOBJ_Fire(GamePanel gp) {
        super(gp);
        //this.gp = gp;

        //name = "Fire";
        direction = "down";
        collision = true;
        lightRadius = 100;


        type = type_obstacle;
        solidArea.x = 3;
        solidArea.y = 16;
        solidArea.width = 36;
        solidArea.height = 27;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        idle = false;

        isLightSource = true;

        getImage();
    }

    public void getImage() {
        down1 = setup("/objects/fire1", gp.tileSize, gp.tileSize);
        down2 = setup("/objects/fire2", gp.tileSize, gp.tileSize);
        up1 = setup("/objects/fire1", gp.tileSize, gp.tileSize);
        up2 = setup("/objects/fire2", gp.tileSize, gp.tileSize);
        left1 = setup("/objects/fire1", gp.tileSize, gp.tileSize);
        left2 = setup("/objects/fire2", gp.tileSize, gp.tileSize);
        right1 = setup("/objects/fire1", gp.tileSize, gp.tileSize);
        right2 = setup("/objects/fire2", gp.tileSize, gp.tileSize);
    }
}
