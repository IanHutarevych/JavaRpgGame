package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Gold_Clever extends Entity {

    public static final String objName = "Clever";

    public OBJ_Gold_Clever(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/objects/goldClever",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA final artifact.";
        price = 900;
        idle = false;
    }
}
