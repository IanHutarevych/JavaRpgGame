package environment;

import main.GamePanel;
import java.awt.*;
import java.util.Random;

public class Rain {

    GamePanel gp;
    public boolean isRaining = false;
    int rainCounter = 0;
    Random random = new Random();

    int maxPossibleDrops = 300;
    int currentMaxDrops = 150;
    float[] dropX = new float[maxPossibleDrops];
    float[] dropY = new float[maxPossibleDrops];
    float[] dropSpeed = new float[maxPossibleDrops];
    int[] dropLength = new int[maxPossibleDrops];
    int[] dropAlpha = new int[maxPossibleDrops];

    float windAngle = 2f;

    public Rain(GamePanel gp) {
        this.gp = gp;
        prepareDrops();
    }

    private void prepareDrops() {
        for (int i = 0; i < maxPossibleDrops; i++) {
            resetDrop(i, true);
        }
    }

    private void resetDrop(int i, boolean randomStart) {
        dropX[i] = random.nextInt(gp.screenWidth + 100);
        dropY[i] = randomStart ? random.nextInt(gp.screenHeight) : -random.nextInt(50) - 10;
        dropSpeed[i] = random.nextFloat() * 6 + 10;
        dropLength[i] = random.nextInt(12) + 8;
        dropAlpha[i] = random.nextInt(80) + 100;
    }

    public void update() {
        rainCounter++;

        if (rainCounter > 600) {
            rainCounter = 0;
            int chance = random.nextInt(100);

            if (!isRaining && chance < 60) {
                isRaining = true;
                currentMaxDrops = random.nextInt(151) + 100;
            } else if (isRaining && chance < 15) {
                isRaining = false;
            }
        }

        if (gp.eManager.wind.isWindy) {
            windAngle += 0.05f;
            if (windAngle > 5f) windAngle = 5f;
        } else {
            windAngle -= 0.05f;
            if (windAngle < 1.5f) windAngle = 1.5f;
        }

        if (isRaining) {
            for (int i = 0; i < currentMaxDrops; i++) {
                dropY[i] += dropSpeed[i];
                dropX[i] += windAngle;

                if (dropY[i] > gp.screenHeight + 20 || dropX[i] > gp.screenWidth + 20) {
                    resetDrop(i, false);
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (!isRaining || gp.currentArea != gp.outside) return;

        if (currentMaxDrops > 200) {
            g2.setColor(new Color(100, 120, 160, 20));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

        for (int i = 0; i < currentMaxDrops; i++) {
            int alpha = dropAlpha[i];

            g2.setColor(new Color(160, 180, 255, alpha));
            g2.setStroke(new BasicStroke(1.2f));
            int endX = (int)(dropX[i] + windAngle * 1.5f);
            int endY = (int)(dropY[i] + dropLength[i]);
            g2.drawLine((int)dropX[i], (int)dropY[i], endX, endY);

            g2.setColor(new Color(220, 235, 255, alpha / 3));
            g2.setStroke(new BasicStroke(0.6f));
            g2.drawLine((int)dropX[i], (int)dropY[i], (int)dropX[i], (int)dropY[i] + dropLength[i] / 3);
        }
    }
}