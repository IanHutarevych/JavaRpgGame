package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_DoorClose extends Entity {

    GamePanel gp;

    public OBJ_DoorClose(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "door_close";
        down1 = setup("/objects/door_close",gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    @Override
    public void interact() {

        if (gp.keyCounter == 0){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialog = "You need a key to open this";
        }
    }
}
