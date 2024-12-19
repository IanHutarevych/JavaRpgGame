package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_ChestOpen extends SuperObject {

    GamePanel gp;

    public OBJ_ChestOpen(GamePanel gp) {

        this.gp = gp;
        collision = true;

        name = "chest_open";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_open.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
