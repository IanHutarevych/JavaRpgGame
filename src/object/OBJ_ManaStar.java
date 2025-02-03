package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_ManaStar extends Entity{

    GamePanel gp;
    public static final String objName = "Mana Star";

    public OBJ_ManaStar(GamePanel gp) {
        super(gp);
        this.gp = gp;

        value = 1;
        type = type_pickupOnly;
        name = objName;
        down1 = setup("/objects/manaFull",gp.tileSize, gp.tileSize);
        image1 = setup("/objects/manaFull",gp.tileSize, gp.tileSize);
        image2 = setup("/objects/manaEmpty",gp.tileSize, gp.tileSize);
        idle = false;
    }
    public boolean use(Entity e) {
        gp.playSE(2);
        gp.ui.addMessage("Mana +" + value);
        e.mana += value;
        return false;
    }
}
