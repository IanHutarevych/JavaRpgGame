package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_DryTree extends InteractiveTile{

    GamePanel gp;

    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp. tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/drytree",gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;

    }
    public boolean isCorrectWeapon(Entity e) {
        boolean correct = false;
        if (e.currentWeapon.type == type_axe){
            correct = true;
        }
        return correct;
    }
    public void playSE(){
        gp.playSE(11);
    }

    public InteractiveTile getDestroyedForm(){
        InteractiveTile destroyedForm = new IT_Trunk(gp,worldX/gp.tileSize,worldY/gp.tileSize);

        return destroyedForm;
    }
    public Color getParticleColor(){
        Color color = new Color(88,67,38);
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
