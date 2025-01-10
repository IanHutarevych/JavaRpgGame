package tile_interactive;

import main.GamePanel;
import entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InteractiveTile extends Entity {

    GamePanel gp;
    public boolean destructible = false;
    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);

        this.gp = gp;


    }
    public boolean isCorrectWeapon(Entity e) {
        boolean correct = false;
        return correct;
    }
    public void playSE(){}

    public InteractiveTile getDestroyedForm(){
        InteractiveTile destroyedForm = null;
        return destroyedForm;
    }

    public void update() {
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 20){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX > gp.player.worldX - gp.player.screenX - 2 * gp.tileSize &&
                worldX < gp.player.worldX + gp.player.screenX + 2 * gp.tileSize &&
                worldY > gp.player.worldY - gp.player.screenY - 2 * gp.tileSize &&
                worldY < gp.player.worldY + gp.player.screenY + 2 * gp.tileSize) {


            g2.drawImage(down1, screenX, screenY,null);


        }
    }
}
