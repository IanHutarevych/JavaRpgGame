package environment;

import main.GamePanel;
import java.awt.*;

public class EnvironmentManager {

    GamePanel gp;
    public Lightning lightning;
    public Rain rain;
    public Wind wind;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }
    public void setup() {
        lightning = new Lightning(gp);
        rain = new Rain(gp);
        wind = new Wind(gp);
    }
    public void update() {
        lightning.update();
        rain.update();
        wind.update();

        if (rain.isRaining && wind.isSandstorm) {
            rain.isRaining = false;
        }
        if (wind.isSandstorm && rain.isRaining) {
            wind.isSandstorm = false;
            wind.isWindy = false;
        }
    }
    public void draw(Graphics2D g2) {
        lightning.draw(g2);
        rain.draw(g2);
        wind.draw(g2);
    }
}