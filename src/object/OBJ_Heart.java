package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    GamePanel gp;
    public static final String objName = "Heart";

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        value = 2;
        type = type_pickupOnly;
        down1 = setup("/objects/heart01",gp.tileSize, gp.tileSize);
        // RED Heart
        image1 = setup("/objects/heart01",gp.tileSize - 12, gp.tileSize - 12);
        image2 = setup("/objects/heart02",gp.tileSize - 12, gp.tileSize - 12);
        image3 = setup("/objects/heart03",gp.tileSize - 12, gp.tileSize - 12);
        // GOLD Heart
        image4 = setup("/objects/heart011",gp.tileSize - 12, gp.tileSize - 12);
        image5 = setup("/objects/heart021",gp.tileSize - 12, gp.tileSize - 12);
        idle = false;
    }
    public boolean use(Entity e) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        e.life += value;
        return false;
    }
}
