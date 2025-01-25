package tile_interactive;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coconut;

import java.awt.*;

public class IT_Palm extends InteractiveTile{

    GamePanel gp;

    public IT_Palm(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp. tileSize * col;
        this.worldY = gp.tileSize * row;

        solidArea.x = 9;
        solidArea.y = 4;
        solidArea.width = 24;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        down1 = setup("/tiles_interactive/palm",gp.tileSize, gp.tileSize);
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
        InteractiveTile destroyedForm = new IT_PalmTrunk(gp,worldX/gp.tileSize,worldY/gp.tileSize);
        gp.player.canObtainItem(new OBJ_Coconut(gp));
        gp.ui.addMessage("Coconut + 1");



        return destroyedForm;
    }
    public Color getParticleColor(){
        Color color = new Color(212,167,83);
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
