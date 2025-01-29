package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_DoorClose extends Entity {

    GamePanel gp;
    public static final String objName = "door_close";

    public OBJ_DoorClose(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("/objects/door_close",gp.tileSize, gp.tileSize);
        down2 = setup("/objects/door_open",gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "You need a key to open this";
        dialogues[1][0] = "You opened the door";
    }
    @Override
    public void interact() {
        if (!opened) {
            if (gp.player.keyCounter == 0){
                startDialogue(this,0);
            }
            else if (gp.player.keyCounter > 0){
                startDialogue(this,1);
                down1 = down2;
                opened = true;
                collision = false;
            }
        }
    }
}
