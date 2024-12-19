package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_ChestClose extends  SuperObject{

    GamePanel gp;

    public OBJ_ChestClose(GamePanel gp) {

        this.gp = gp;
        collision = true;

        name = "chest_close";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_close.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
