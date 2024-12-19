package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Swamp3 extends SuperObject {


    GamePanel gp;

    public OBJ_Swamp3(GamePanel gp) {

        this.gp = gp;

        name = "swamp3";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swamp3.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
