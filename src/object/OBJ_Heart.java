package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {


    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "Heart";

        image = setup("/objects/heart01");
        image2 = setup("/objects/heart02");
        image3 = setup("/objects/heart03");
    }

}
