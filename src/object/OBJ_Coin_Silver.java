package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Silver extends Entity {

    GamePanel gp;
    public static final String objName = "Silver Coin";

    public OBJ_Coin_Silver(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        value = 5;
        name = objName;
        down1 = setup("/objects/coinSilver",gp.tileSize, gp.tileSize);

        solidArea.x = 12;
        solidArea.y = 9;
        solidArea.width = 27;
        solidArea.height = 27;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public boolean use(Entity e) {

        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
        return false;
    }
}
