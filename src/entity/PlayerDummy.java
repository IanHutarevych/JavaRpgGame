package entity;

import entity.Entity;
import main.GamePanel;

public class PlayerDummy extends Entity{

    public static final String npcName = "Dummy";

    public PlayerDummy(GamePanel gp) {
        super(gp);

        name = npcName;
        getImage();

    }
    public void getImage() {
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
}
