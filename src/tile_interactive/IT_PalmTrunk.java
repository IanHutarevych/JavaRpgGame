package tile_interactive;
import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_PalmTrunk extends InteractiveTile {

    GamePanel gp;

    public IT_PalmTrunk(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp. tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/trunkPalm",gp.tileSize, gp.tileSize);

        solidArea.x = 12;
        solidArea.y = 21;
        solidArea.width = 27;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        destructible = true;
        life = 3;

    }
    public boolean isCorrectWeapon(Entity e) {
        boolean correct = false;
        if (e.currentWeapon.type == type_watering){
            correct = true;
        }
        return correct;
    }
    public void playSE(){
        gp.playSE(11);
    }

    public InteractiveTile getDestroyedForm(){
        InteractiveTile destroyedForm = new IT_Palm(gp,worldX/gp.tileSize,worldY/gp.tileSize);

        return destroyedForm;
    }
    public Color getParticleColor(){
        Color color = new Color(129,179,255);
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
