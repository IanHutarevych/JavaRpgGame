package object;

import entity.Entity;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class OBJ_DoorOpen extends Entity {
    private static BufferedImage doorOpenImage;


    public OBJ_DoorOpen(GamePanel gp) {

        super(gp);

        name = "door_open";
        if (doorOpenImage == null) {
            doorOpenImage = setup("/objects/door_open");
        }
        down1 = doorOpenImage;
    }
}
