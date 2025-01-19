package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Gold extends Entity {

    GamePanel gp;

    public OBJ_Coin_Gold(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        value = 10;
        name = "Bronze Coin";
        down1 = setup("/objects/coinGold",gp.tileSize, gp.tileSize);

        solidArea.x = 12;
        solidArea.y = 9;
        solidArea.width = 27;
        solidArea.height = 27;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void use(Entity e) {

        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
    }
}
