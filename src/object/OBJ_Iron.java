package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Iron extends Entity {

    GamePanel gp;
    public static final String objName = "Iron";

    public OBJ_Iron(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        down1 = setup("/objects/iron",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA nugget of iron.";
        price = 20;
        stackable = true;
    }
    /*public void use(Entity e) {

        gp.playSE(1);
        gp.ui.addMessage("Gold + 1");
    }*/
}
