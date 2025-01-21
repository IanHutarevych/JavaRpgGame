package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Torch extends Entity{

    public OBJ_Torch(GamePanel gp) {
        super(gp);

        type = type_light;
        name = "Torch";
        down1 = setup("/objects/torch1", gp.tileSize, gp.tileSize);
        description = "[Torch]\nIlluminates you \nsurroundings";
        price = 10;
        lightRadius = 350;
    }
}
