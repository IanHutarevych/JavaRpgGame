package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity {

    GamePanel gp;

    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        value = 1;
        name = "Bronze Coin";
        down1 = setup("/objects/coinBronze",gp.tileSize, gp.tileSize);

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
