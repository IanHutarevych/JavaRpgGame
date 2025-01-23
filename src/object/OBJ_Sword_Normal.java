package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Knight's sword";
        down1 = setup("/objects/sword",gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old knight`s sword.";
        price = 10;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
