package environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManager {

    GamePanel gp;
    public Lightning lightning;
    public Rain rain;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }
    public void setup(){
        lightning = new Lightning(gp);
        rain = new Rain(gp);
    }
    public void update(){
        lightning.update();
        rain.update();
    }
    public void draw(Graphics2D g2){
        lightning.draw(g2);
        rain.draw(g2);
    }
}