package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Gold_Clever extends Entity {

    public static final String objName = "Clever";
    GamePanel gp;

    public OBJ_Gold_Clever(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        down1 = setup("/objects/goldClever",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA final artifact.";
        price = 900;
        idle = false;

        setDialogues();
    }

    private void setDialogues() {
        dialogues[0][0] = "You pick up a beautiful Gold Clever.";
        dialogues[0][1] = "You find an artifact, the legendary treasure!";

    }
    public boolean use(Entity entity) {

        gp.gameState = gp.cutSceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
}
