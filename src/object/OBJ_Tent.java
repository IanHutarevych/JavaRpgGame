package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{

    GamePanel gp;
    public static final String objName = "Tent";

    public OBJ_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
        description = "[Tent]\nYou can skip the night.";
        price = 300;
        stackable = true;
        idle = false;
    }
    public boolean use(Entity e){
        gp.gameState = gp.sleepState;
        // playSE in future
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;
    }
}
