package environment;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lightning {

    GamePanel gp;
    BufferedImage darknessFilter;
    public int dayCounter;
    public float filterAlpha = 0f;

    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = night;

    public Lightning(GamePanel gp) {
        this.gp = gp;
        setLightSource();
    }

    public void setLightSource() {
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = darknessFilter.createGraphics();

        g2.setColor(new Color(0, 0, 0.1f, 0.95f));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // DST_OUT
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OUT));

        if (gp.player.currentLight != null) {
            int cx = gp.player.screenX + gp.tileSize / 2;
            int cy = gp.player.screenY + gp.tileSize / 2;
            drawLightCircle(g2, cx, cy, gp.player.currentLight.lightRadius);
        }

        for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
            Entity obj = gp.obj[gp.currentMap][i];
            if (obj != null && obj.isLightSource) {
                int screenX = obj.worldX - gp.player.worldX + gp.player.screenX;
                int screenY = obj.worldY - gp.player.worldY + gp.player.screenY;
                drawLightCircle(g2, screenX + gp.tileSize / 2, screenY + gp.tileSize / 2, obj.lightRadius);
            }
        }

        g2.dispose();
    }

    private void drawLightCircle(Graphics2D g2, int centerX, int centerY, int radius) {
        Color[] color = new Color[]{
                new Color(0, 0, 0, 1f),
                new Color(0, 0, 0, 0f)
        };
        float[] fraction = new float[]{0f, 1f};

        RadialGradientPaint paint = new RadialGradientPaint(
                centerX, centerY, radius, fraction, color
        );
        g2.setPaint(paint);
        g2.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    }

    public void resetDay() {
        dayCounter = day;
        filterAlpha = 0f;
    }

    public void update() {
        setLightSource();
        gp.player.lightUpdated = false;

        if (dayState == day) {
            dayCounter++;
            if (dayCounter > 1800) {
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if (dayState == dusk) {
            filterAlpha += 0.001f;
            if (filterAlpha > 1f) {
                filterAlpha = 1f;
                dayState = night;
            }
        }
        if (dayState == night) {
            dayCounter++;
            if (dayCounter > 1800) {
                dayState = dawn;
                dayCounter = 0;
            }
        }
        if (dayState == dawn) {
            filterAlpha -= 0.001f;
            if (filterAlpha < 0f) {
                filterAlpha = 0f;
                dayState = day;
                gp.achManager.onNightSurvived();
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (gp.currentArea == gp.outside) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        }
        if (gp.currentArea == gp.outside || gp.currentArea == gp.dungeon) {
            g2.drawImage(darknessFilter, 0, 0, null);
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
            entity.Entity obj = gp.obj[gp.currentMap][i];
            if (obj != null && obj.isLightSource) {
                int screenX = obj.worldX - gp.player.worldX + gp.player.screenX;
                int screenY = obj.worldY - gp.player.worldY + gp.player.screenY;
                int cx = screenX + gp.tileSize / 2;
                int cy = screenY + gp.tileSize / 2;
                int radius = obj.lightRadius;

                Color[] glowColor = new Color[]{
                        new Color(255, 180, 50, 80),
                        new Color(255, 120, 20, 40),
                        new Color(255, 80,  0,  0)
                };
                float[] fraction = new float[]{0f, 0.5f, 1f};

                RadialGradientPaint glow = new RadialGradientPaint(cx, cy, radius, fraction, glowColor);
                g2.setPaint(glow);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                g2.fillOval(cx - radius, cy - radius, radius * 2, radius * 2);
            }
        }
    }
}