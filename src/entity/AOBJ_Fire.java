package entity;

import main.GamePanel;



public class AOBJ_Fire extends Entity {
    public AOBJ_Fire(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Fire";
        direction = "down";

        collision = true;


        solidArea.x = 3;
        solidArea.y = 16;
        solidArea.width = 36;
        solidArea.height = 27;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        isBurning();
    }

    public void getImage() {
        down1 = setup("/objects/fire1", gp.tileSize, gp.tileSize);
        down2 = setup("/objects/fire2", gp.tileSize, gp.tileSize);
        up1 = setup("/objects/fire",gp.tileSize, gp.tileSize);
        up2 = setup("/objects/fire",gp.tileSize, gp.tileSize);
    }
    public void isBurning(){

    }
}
