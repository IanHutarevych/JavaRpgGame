package environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManager {

    GamePanel gp;
    Lightning lightning;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }
    public void setup(){
        lightning = new Lightning(gp, 350);
    }
    public void draw(Graphics2D g2){
        lightning.draw(g2);
    }
}
