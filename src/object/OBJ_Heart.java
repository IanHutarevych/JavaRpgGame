package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Heart";
        value = 2;
        type = type_pickupOnly;
        down1 = setup("/objects/heart01",gp.tileSize, gp.tileSize);
        image = setup("/objects/heart01",gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart02",gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart03",gp.tileSize, gp.tileSize);
    }
    public void use(Entity e) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        e.life += value;
    }
}
