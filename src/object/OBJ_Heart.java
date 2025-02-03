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
        image1 = setup("/objects/heart01",gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart02",gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart03",gp.tileSize, gp.tileSize);
        idle = false;
    }
    public boolean use(Entity e) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        e.life += value;
        return false;
    }
}
