package environment;

import main.GamePanel;
import java.awt.*;
import java.util.Random;

public class Rain {

    private static final Stroke STROKE_DROP = new BasicStroke(1.2f);
    private static final Stroke STROKE_SOFT = new BasicStroke(0.6f);

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

    float rainAlpha = 0f;
    final float FADE_STEP = 1f / 480f;

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
        int leftPad = (int)(windAngle * 90) + 40;
        dropX[i] = random.nextInt(gp.screenWidth + leftPad + 100) - leftPad;
        dropY[i] = randomStart ? random.nextInt(gp.screenHeight) : -random.nextInt(50) - 10;
        dropSpeed[i] = random.nextFloat() * 6 + 10;
        dropLength[i] = random.nextInt(12) + 8;
        dropAlpha[i] = random.nextInt(80) + 100;
    }

    public void update() {
        rainCounter++;

        if (rainCounter > 400) {
            rainCounter = 0;
            int chance = random.nextInt(100);

            if (!isRaining && chance < 7 && !gp.eManager.wind.isSandstorm) {
                isRaining = true;
                currentMaxDrops = random.nextInt(151) + 100;
            } else if (isRaining && chance < 20) {
                isRaining = false;
            }
        }

        if (isRaining && gp.eManager.wind.isSandstorm) {
            isRaining = false;
        }

        if (gp.eManager.wind.isWindy) {
            windAngle += 0.05f;
            if (windAngle > 5f) windAngle = 5f;
        } else {
            windAngle -= 0.05f;
            if (windAngle < 1.5f) windAngle = 1.5f;
        }

        float target = isRaining ? 1f : 0f;
        if (rainAlpha < target) {
            rainAlpha = Math.min(target, rainAlpha + FADE_STEP);
        } else if (rainAlpha > target) {
            rainAlpha = Math.max(target, rainAlpha - FADE_STEP);
        }

        if (rainAlpha > 0f) {
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
        if (rainAlpha <= 0f || gp.currentArea != gp.outside) return;

        if (currentMaxDrops > 200) {
            int overlayA = (int)(20 * rainAlpha);
            g2.setColor(new Color(100, 120, 160, overlayA));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

        float windShift = windAngle * 1.5f;

        for (int i = 0; i < currentMaxDrops; i++) {
            int alpha     = (int)(dropAlpha[i] * rainAlpha);
            int alphaSoft = (int)((dropAlpha[i] / 3) * rainAlpha);

            int x  = (int) dropX[i];
            int y  = (int) dropY[i];
            int dl = dropLength[i];

            g2.setStroke(STROKE_DROP);
            g2.setColor(new Color(160, 180, 255, alpha));
            g2.drawLine(x, y, (int)(dropX[i] + windShift), y + dl);

            g2.setStroke(STROKE_SOFT);
            g2.setColor(new Color(220, 235, 255, alphaSoft));
            g2.drawLine(x, y, x, y + dl / 3);
        }
    }
}