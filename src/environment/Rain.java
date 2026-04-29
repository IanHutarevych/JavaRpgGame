package environment;

import main.GamePanel;
import java.awt.*;
import java.util.Random;

public class Rain {

    GamePanel gp;
    public boolean isRaining = false;
    int rainCounter = 0;
    Random random = new Random();

    int maxPossibleDrops = 250;
    int currentMaxDrops = 150;
    int[] dropX = new int[maxPossibleDrops];
    int[] dropY = new int[maxPossibleDrops];
    int[] dropSpeed = new int[maxPossibleDrops];

    public Rain(GamePanel gp) {
        this.gp = gp;
        prepareDrops();
    }

    private void prepareDrops() {
        for (int i = 0; i < maxPossibleDrops; i++) {
            dropX[i] = random.nextInt(gp.screenWidth);
            dropY[i] = random.nextInt(gp.screenHeight);
            dropSpeed[i] = random.nextInt(5) + 10;
        }
    }

    public void update() {
        rainCounter++;

        if (rainCounter > 2200) {
            rainCounter = 0;
            int chance = random.nextInt(100);

            if (!isRaining && chance < 10) {
                isRaining = true;
                currentMaxDrops = random.nextInt(151) + 100;
            } else if (isRaining && chance < 30) {
                isRaining = false;
            }
        }

        if (isRaining) {
            for (int i = 0; i < currentMaxDrops; i++) {
                dropY[i] += dropSpeed[i];
                dropX[i] += 1;

                if (dropY[i] > gp.screenHeight) {
                    dropY[i] = -20;
                    dropX[i] = random.nextInt(gp.screenWidth);
                    dropSpeed[i] = random.nextInt(5) + 10;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
         if (isRaining && gp.currentArea == gp.outside) {
            g2.setColor(new Color(150, 150, 255, 150));
            g2.setStroke(new BasicStroke(2f));

            for (int i = 0; i < currentMaxDrops; i++) {
                g2.drawLine(dropX[i], dropY[i], dropX[i] + 1, dropY[i] + 15);
            }
        }
    }
}