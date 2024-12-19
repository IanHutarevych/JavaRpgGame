package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Swamp1 extends SuperObject {


    GamePanel gp;

    public OBJ_Swamp1(GamePanel gp) {

        this.gp = gp;

        name = "swamp1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swamp1.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
