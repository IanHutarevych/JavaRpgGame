package tile_interactive;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Gold;
import object.OBJ_Iron;

import java.awt.*;
import java.util.Random;

public class IT_Rock2 extends InteractiveTile{

    GamePanel gp;

    public IT_Rock2(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp. tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/rock2",gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;

        solidArea.x = 0;
        solidArea.y = 20;
        solidArea.width = 48;
        solidArea.height = 28;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

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
        InteractiveTile destroyedForm = new IT_AfterRock2(gp,worldX/gp.tileSize,worldY/gp.tileSize);
        checkDrop();
        return destroyedForm;
    }
    public Color getParticleColor(){
        Color color = new Color(214,160,84);
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
    @Override
    public void checkDrop(){
        int i = new Random().nextInt(100)+1;
        if (i < 60){
            dropItem(new OBJ_Iron(gp));
        }
        if (i >=60){
            dropItem(new OBJ_Gold(gp));
        }
    }
}
