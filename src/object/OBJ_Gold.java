package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Gold extends Entity {

    GamePanel gp;
    public static final String objName = "Gold";

    public OBJ_Gold(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        down1 = setup("/objects/gold",gp.tileSize, gp.tileSize);
        price = 40;
        stackable = true;
        description = "[" + name + "]\nA nugget of gold.";
        idle = false;
    }
    /*public void use(Entity e) {

        gp.playSE(1);
        gp.ui.addMessage("Gold + 1");
    }*/
}
