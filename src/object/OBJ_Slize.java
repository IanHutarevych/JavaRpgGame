package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Slize  extends Projectile {
    GamePanel gp;

    public OBJ_Slize(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Slize";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();

    }

    private void getImage() {
        up1 = setup("/projectile/SlizeUp1",gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/SlizeUp2",gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/SlizeDown1",gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/SlizeDown2",gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/SlizeRight1",gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/SlizeRight2",gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/SlizeLeft1",gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/SlizeLeft2",gp.tileSize, gp.tileSize);
    }
}
