package object;

import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_SandSlize  extends Projectile {

    GamePanel gp;
    public static final String objName = "SandSlize";

    public OBJ_SandSlize(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        idle = false;
        getImage();

    }

    private void getImage() {
        up1 = setup("/projectile/SandSlize_up1",gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/SandSlize_up2",gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/SandSlize_down1",gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/SandSlize_down2",gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/SandSlize_right1",gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/SandSlize_right2",gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/SandSlize_left1",gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/SandSlize_left2",gp.tileSize, gp.tileSize);
    }
    public Color getParticleColor(){
        Color color = new Color(230,185,90);
        return color;
    }
    public int getParticleSize(){
        int size = 6;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}
