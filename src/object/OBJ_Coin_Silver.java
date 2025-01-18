package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Silver extends Entity {

    GamePanel gp;

    public OBJ_Coin_Silver(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        value = 5;
        name = "Bronze Coin";
        down1 = setup("/objects/coinSilver",gp.tileSize, gp.tileSize);
    }
    public void use(Entity e) {

        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
    }
}
