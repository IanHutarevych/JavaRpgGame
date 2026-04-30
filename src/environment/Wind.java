package environment;

import main.GamePanel;
import java.awt.*;
import java.util.Random;

public class Wind {

    GamePanel gp;
    Random random = new Random();

    public boolean isWindy = false;
    boolean isSandstorm = false;

    int windCounter = 0;
    int gustCounter = 0;
    int gustInterval = 0;

    // Сила вітру (впливає на швидкість і кут)
    float windStrength = 1.0f;
    float targetStrength = 1.0f;

    // Пилові частинки
    int maxParticles = 300;
    float[] pX = new float[maxParticles];
    float[] pY = new float[maxParticles];
    float[] pSpeedX = new float[maxParticles];
    float[] pSpeedY = new float[maxParticles];
    int[] pSize = new int[maxParticles];
    int[] pAlpha = new int[maxParticles];

    // Горизонтальні смуги вітру
    int maxStreaks = 30;
    float[] sX = new float[maxStreaks];
    float[] sY = new float[maxStreaks];
    float[] sSpeed = new float[maxStreaks];
    int[] sLength = new int[maxStreaks];
    int[] sAlpha = new int[maxStreaks];

    public Wind(GamePanel gp) {
        this.gp = gp;
        prepareParticles();
        prepareStreaks();
    }

    private void prepareParticles() {
        for (int i = 0; i < maxParticles; i++) {
            resetParticle(i, true);
        }
    }

    private void prepareStreaks() {
        for (int i = 0; i < maxStreaks; i++) {
            resetStreak(i, true);
        }
    }

    private void resetParticle(int i, boolean randomStart) {
        pX[i] = randomStart ? random.nextInt(gp.screenWidth) : -random.nextInt(100);
        pY[i] = random.nextInt(gp.screenHeight);
        // Швидкість під кутом: більше по X, трохи по Y (хвилеподібно)
        pSpeedX[i] = (random.nextFloat() * 4 + 3) * windStrength;
        pSpeedY[i] = (random.nextFloat() * 1.5f - 0.5f); // злегка вгору-вниз
        pSize[i] = random.nextInt(3) + 1;
        pAlpha[i] = random.nextInt(120) + 60;
    }

    private void resetStreak(int i, boolean randomStart) {
        sX[i] = randomStart ? random.nextInt(gp.screenWidth) : -random.nextInt(200);
        sY[i] = random.nextInt(gp.screenHeight);
        sSpeed[i] = (random.nextFloat() * 8 + 6) * windStrength;
        sLength[i] = random.nextInt(60) + 20;
        sAlpha[i] = random.nextInt(80) + 30;
    }

    public void update() {
        windCounter++;

        // Логіка зміни стану вітру
        if (windCounter > 1800) {
            windCounter = 0;
            int chance = random.nextInt(100);

            if (!isWindy && chance < 75) {
                isWindy = true;
                isSandstorm = chance < 5; // 5% шанс піщаної бурі
                targetStrength = isSandstorm ? (random.nextFloat() + 1.5f) : (random.nextFloat() * 0.8f + 0.6f);
                gustInterval = random.nextInt(120) + 60;
            } else if (isWindy && chance < 25) {
                isWindy = false;
                isSandstorm = false;
                targetStrength = 0.3f;
            }
        }

        if (!isWindy) return;

        // Плавна зміна сили вітру
        if (windStrength < targetStrength) windStrength += 0.02f;
        else if (windStrength > targetStrength) windStrength -= 0.02f;

        // Пориви вітру
        gustCounter++;
        if (gustCounter > gustInterval) {
            gustCounter = 0;
            gustInterval = random.nextInt(120) + 60;
            targetStrength = isSandstorm
                    ? (random.nextFloat() * 1.0f + 1.2f)
                    : (random.nextFloat() * 0.8f + 0.5f);
        }

        // Оновлення частинок
        int activeParticles = isSandstorm ? maxParticles : maxParticles / 2;
        for (int i = 0; i < activeParticles; i++) {
            pX[i] += pSpeedX[i] * windStrength;
            // Хвилеподібний рух по Y
            pY[i] += pSpeedY[i] + (float) Math.sin(pX[i] * 0.01f) * 0.5f;

            if (pX[i] > gp.screenWidth + 10) {
                resetParticle(i, false);
            }
        }

        // Оновлення смуг
        int activeStreaks = isSandstorm ? maxStreaks : maxStreaks / 2;
        for (int i = 0; i < activeStreaks; i++) {
            sX[i] += sSpeed[i] * windStrength;
            if (sX[i] > gp.screenWidth + sLength[i]) {
                resetStreak(i, false);
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (!isWindy || gp.currentArea != gp.outside) return;

        // Легкий жовтуватий туман при піщаній бурі
        if (isSandstorm) {
            int fogAlpha = (int)(windStrength * 35);
            g2.setColor(new Color(180, 140, 60, Math.min(fogAlpha, 60)));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

        // Смуги вітру (горизонтальні лінії)
        int activeStreaks = isSandstorm ? maxStreaks : maxStreaks / 2;
        for (int i = 0; i < activeStreaks; i++) {
            int alpha = Math.min((int)(sAlpha[i] * windStrength), 180);
            Color streakColor = isSandstorm
                    ? new Color(200, 160, 80, alpha)
                    : new Color(200, 200, 220, alpha);
            g2.setColor(streakColor);
            g2.setStroke(new BasicStroke(1f));
            g2.drawLine(
                    (int) sX[i], (int) sY[i],
                    (int) sX[i] - sLength[i], (int) sY[i]
            );
        }

        // Пилові частинки
        int activeParticles = isSandstorm ? maxParticles : maxParticles / 2;
        for (int i = 0; i < activeParticles; i++) {
            int alpha = Math.min((int)(pAlpha[i] * windStrength), 220);
            Color dustColor = isSandstorm
                    ? new Color(210, 170, 90, alpha)
                    : new Color(220, 220, 235, alpha);
            g2.setColor(dustColor);
            g2.fillOval((int) pX[i], (int) pY[i], pSize[i], pSize[i]);
        }
    }
}