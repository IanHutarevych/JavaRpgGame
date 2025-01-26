package object;

import entity.Entity;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class OBJ_DoorOpen extends Entity {
    private static BufferedImage doorOpenImage;
    public static final String objName = "door_open";


    public OBJ_DoorOpen(GamePanel gp) {

        super(gp);

        name = objName;
        if (doorOpenImage == null) {
            doorOpenImage = setup("/objects/door_open",gp.tileSize, gp.tileSize);
        }
        down1 = doorOpenImage;
    }
}
