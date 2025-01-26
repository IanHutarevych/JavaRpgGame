package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Fireball extends Projectile {

    GamePanel gp;
    public static final String objName = "Fireball";

    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        speed = 5;
        maxLife = 80;
        life = maxLife;
        knockBackPower = 1;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();

    }

    private void getImage() {
        up1 = setup("/projectile/Fireball_up1",gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/Fireball_up2",gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/Fireball_down1",gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/Fireball_down2",gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/Fireball_right1",gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/Fireball_right2",gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/Fireball_left1",gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/Fireball_left2",gp.tileSize, gp.tileSize);
    }
    public boolean haveResources(Entity user){
        boolean haveRes = user.mana >= useCost;
        return haveRes;
    }
    public void subtractResources(Entity user) {
        user.mana -= useCost;
    }
    public Color getParticleColor(){
        Color color = new Color(250,86,28);
        return color;
    }
    public int getParticleSize(){
        int size = 10;
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
