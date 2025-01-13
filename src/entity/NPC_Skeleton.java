package entity;

import main.GamePanel;

public class NPC_Skeleton extends Entity {

    public NPC_Skeleton(GamePanel gp) {
        super(gp);

        direction = "down";

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
            down1 = setup("/npc/skeleton1", gp.tileSize, gp.tileSize);
            down2 = setup("/npc/skeleton2", gp.tileSize, gp.tileSize);
    }
}
