package environment;

import main.GamePanel;
import java.awt.*;
import java.util.Random;

public class Wind {

    private static final Stroke STROKE_1   = new BasicStroke(1f);
    private static final Stroke STROKE_BOLT_OUTER = new BasicStroke(8f,   BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private static final Stroke STROKE_BOLT_MID   = new BasicStroke(3f,   BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private static final Stroke STROKE_BOLT_CORE  = new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    private static final Color BOLT_OUTER  = new Color(180, 200, 255,  90);
    private static final Color BOLT_MID    = new Color(255, 240, 150, 160);
    private static final Color BOLT_CORE   = new Color(255, 255, 255, 230);
    private static final Color FLASH_COLOR = new Color(230, 230, 180);

    GamePanel gp;
    Random random = new Random();

    public boolean isWindy = false;
    boolean isSandstorm = false;

    int windCounter = 0;
    int gustCounter = 0;
    int gustInterval = 0;

    float windStrength = 1.0f;
    float targetStrength = 1.0f;

    int maxParticles = 300;
    float[] pX = new float[maxParticles];
    float[] pY = new float[maxParticles];
    float[] pSpeedX = new float[maxParticles];
    float[] pSpeedY = new float[maxParticles];
    int[] pSize = new int[maxParticles];
    int[] pAlpha = new int[maxParticles];

    int maxStreaks = 30;
    float[] sX = new float[maxStreaks];
    float[] sY = new float[maxStreaks];
    float[] sSpeed = new float[maxStreaks];
    int[] sLength = new int[maxStreaks];
    int[] sAlpha = new int[maxStreaks];

    int lightningTimer    = 0;
    int lightningInterval = 180;
    int lightningFlash    = 0;
    int[] boltX;
    int[] boltY;
    final int BOLT_SEGMENTS = 10;

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

        pSpeedX[i] = (random.nextFloat() * 4 + 3) * windStrength;
        pSpeedY[i] = (random.nextFloat() * 1.5f - 0.5f);
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

        if (windCounter > 1800) {
            windCounter = 0;
            int chance = random.nextInt(100);

            if (!isWindy && chance < 75) {
                isWindy = true;
                isSandstorm = chance < 25;
                targetStrength = isSandstorm ? (random.nextFloat() + 1.5f) : (random.nextFloat() * 0.8f + 0.6f);
                gustInterval = random.nextInt(120) + 60;

                if (isSandstorm) {
                    gp.eManager.rain.isRaining = false;
                    lightningTimer    = 0;
                    lightningInterval = random.nextInt(240) + 120;
                }
            } else if (isWindy && chance < 25) {
                isWindy    = false;
                isSandstorm = false;
                targetStrength = 0.3f;
                lightningFlash = 0;
            }
        }

        if (!isWindy) return;

        if (windStrength < targetStrength) windStrength += 0.02f;
        else if (windStrength > targetStrength) windStrength -= 0.02f;

        gustCounter++;
        if (gustCounter > gustInterval) {
            gustCounter = 0;
            gustInterval = random.nextInt(120) + 60;
            targetStrength = isSandstorm
                    ? (random.nextFloat() * 1.0f + 1.2f)
                    : (random.nextFloat() * 0.8f + 0.5f);
        }

        int activeParticles = isSandstorm ? maxParticles : maxParticles / 2;
        for (int i = 0; i < activeParticles; i++) {
            pX[i] += pSpeedX[i] * windStrength;
            pY[i] += pSpeedY[i] + (float) Math.sin(pX[i] * 0.01f) * 0.5f;
            if (pX[i] > gp.screenWidth + 10) {
                resetParticle(i, false);
            }
        }

        if (isSandstorm) {
            if (lightningFlash > 0) lightningFlash--;

            lightningTimer++;
            if (lightningTimer >= lightningInterval) {
                lightningTimer    = 0;
                lightningInterval = random.nextInt(240) + 120;
                triggerLightning();
            }
        }

        int activeStreaks = isSandstorm ? maxStreaks : maxStreaks / 2;
        for (int i = 0; i < activeStreaks; i++) {
            sX[i] += sSpeed[i] * windStrength;
            if (sX[i] > gp.screenWidth + sLength[i]) {
                resetStreak(i, false);
            }
        }
    }

    private void triggerLightning() {
        lightningFlash = 28;

        boltX = new int[BOLT_SEGMENTS];
        boltY = new int[BOLT_SEGMENTS];

        boltX[0] = random.nextInt(gp.screenWidth - 120) + 60;
        boltY[0] = 0;

        int targetY = (int)(gp.screenHeight * 0.85f);
        for (int i = 1; i < BOLT_SEGMENTS; i++) {
            float t  = (float) i / (BOLT_SEGMENTS - 1);
            boltX[i] = boltX[i - 1] + random.nextInt(70) - 35;
            boltX[i] = Math.max(10, Math.min(gp.screenWidth - 10, boltX[i]));
            boltY[i] = (int)(t * targetY);
        }

        gp.playSE(19);
    }

    public void draw(Graphics2D g2) {
        if (!isWindy || gp.currentArea != gp.outside) return;

        if (isSandstorm) {
            int fogAlpha = (int)(windStrength * 35);
            g2.setColor(new Color(180, 140, 60, Math.min(fogAlpha, 60)));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

        int activeStreaks = isSandstorm ? maxStreaks : maxStreaks / 2;
        g2.setStroke(STROKE_1);
        for (int i = 0; i < activeStreaks; i++) {
            int alpha = Math.min((int)(sAlpha[i] * windStrength), 180);
            Color streakColor = isSandstorm
                    ? new Color(200, 160, 80, alpha)
                    : new Color(200, 200, 220, alpha);
            g2.setColor(streakColor);
            g2.drawLine(
                    (int) sX[i], (int) sY[i],
                    (int) sX[i] - sLength[i], (int) sY[i]
            );
        }

        int activeParticles = isSandstorm ? maxParticles : maxParticles / 2;
        for (int i = 0; i < activeParticles; i++) {
            int alpha = Math.min((int)(pAlpha[i] * windStrength), 220);
            Color dustColor = isSandstorm
                    ? new Color(210, 170, 90, alpha)
                    : new Color(220, 220, 235, alpha);
            g2.setColor(dustColor);
            g2.fillOval((int) pX[i], (int) pY[i], pSize[i], pSize[i]);
        }

        if (isSandstorm && lightningFlash > 0 && boltX != null) {
            Composite original = g2.getComposite();

            float flashAlpha;
            if      (lightningFlash > 20) flashAlpha = 0.70f;
            else if (lightningFlash > 14) flashAlpha = 0.00f;
            else                          flashAlpha = (lightningFlash / 14f) * 0.30f;

            if (flashAlpha > 0f) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, flashAlpha));
                g2.setColor(FLASH_COLOR);
                g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            }

            if (lightningFlash > 14 && boltX.length == BOLT_SEGMENTS) {
                g2.setComposite(AlphaComposite.SrcOver);

                g2.setColor(BOLT_OUTER);
                g2.setStroke(STROKE_BOLT_OUTER);
                for (int i = 0; i < BOLT_SEGMENTS - 1; i++) {
                    g2.drawLine(boltX[i], boltY[i], boltX[i + 1], boltY[i + 1]);
                }

                g2.setColor(BOLT_MID);
                g2.setStroke(STROKE_BOLT_MID);
                for (int i = 0; i < BOLT_SEGMENTS - 1; i++) {
                    g2.drawLine(boltX[i], boltY[i], boltX[i + 1], boltY[i + 1]);
                }

                g2.setColor(BOLT_CORE);
                g2.setStroke(STROKE_BOLT_CORE);
                for (int i = 0; i < BOLT_SEGMENTS - 1; i++) {
                    g2.drawLine(boltX[i], boltY[i], boltX[i + 1], boltY[i + 1]);
                }
            }

            g2.setComposite(original);
            g2.setStroke(STROKE_1);
        }
    }
}