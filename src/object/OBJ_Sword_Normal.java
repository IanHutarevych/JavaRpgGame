package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        name = "Knight's sword";
        down1 = setup("/objects/sword",gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "[" + name + "]\nAn old knight`s sword.";
    }
}
