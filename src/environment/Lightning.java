package environment;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lightning {

    private static final Color DARK_FILL = new Color(0, 0, 0.1f, 0.80f);

    private static final Color[] LIGHT_COLORS = {
            new Color(0, 0, 0, 1.00f),
            new Color(0, 0, 0, 0.97f),
            new Color(0, 0, 0, 0.90f),
            new Color(0, 0, 0, 0.78f),
            new Color(0, 0, 0, 0.62f),
            new Color(0, 0, 0, 0.45f),
            new Color(0, 0, 0, 0.30f),
            new Color(0, 0, 0, 0.17f),
            new Color(0, 0, 0, 0.08f),
            new Color(0, 0, 0, 0.03f),
            new Color(0, 0, 0, 0.01f),
            new Color(0, 0, 0, 0.00f)
    };
    private static final float[] LIGHT_FRACTIONS = {
            0.00f, 0.10f, 0.22f, 0.36f, 0.50f, 0.63f,
            0.74f, 0.83f, 0.90f, 0.95f, 0.98f, 1.00f
    };

    private static final Color[] GLOW_COLORS = {
            new Color(255, 180, 50, 80),
            new Color(255, 120, 20, 40),
            new Color(255,  80,  0,  0)
    };
    private static final float[] GLOW_FRACTIONS = { 0f, 0.5f, 1f };

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
        if (darknessFilter == null
                || darknessFilter.getWidth()  != gp.screenWidth
                || darknessFilter.getHeight() != gp.screenHeight) {
            darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        }
        Graphics2D g2 = darknessFilter.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setColor(DARK_FILL);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setComposite(AlphaComposite.DstOut);

        if (gp.player.currentLight != null) {
            int cx = gp.player.screenX + gp.tileSize / 2;
            int cy = gp.player.screenY + gp.tileSize / 2;
            drawLightCircle(g2, cx, cy, gp.player.currentLight.lightRadius);
        }

        Entity[] mapObjects = gp.obj[gp.currentMap];
        int playerWX = gp.player.worldX;
        int playerWY = gp.player.worldY;
        int playerSX = gp.player.screenX;
        int playerSY = gp.player.screenY;
        int half     = gp.tileSize / 2;

        for (int i = 0; i < mapObjects.length; i++) {
            Entity obj = mapObjects[i];
            if (obj != null && obj.isLightSource) {
                int screenX = obj.worldX - playerWX + playerSX;
                int screenY = obj.worldY - playerWY + playerSY;
                drawLightCircle(g2, screenX + half, screenY + half, obj.lightRadius);
            }
        }

        g2.dispose();
    }

    private void drawLightCircle(Graphics2D g2, int centerX, int centerY, int radius) {
        RadialGradientPaint paint = new RadialGradientPaint(
                centerX, centerY, radius, LIGHT_FRACTIONS, LIGHT_COLORS);
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

        g2.setComposite(AlphaComposite.SrcOver);

        Entity[] mapObjects = gp.obj[gp.currentMap];
        int playerWX = gp.player.worldX;
        int playerWY = gp.player.worldY;
        int playerSX = gp.player.screenX;
        int playerSY = gp.player.screenY;
        int half     = gp.tileSize / 2;

        for (int i = 0; i < mapObjects.length; i++) {
            Entity obj = mapObjects[i];
            if (obj != null && obj.isLightSource) {
                int screenX = obj.worldX - playerWX + playerSX;
                int screenY = obj.worldY - playerWY + playerSY;
                int cx = screenX + half;
                int cy = screenY + half;
                int radius = obj.lightRadius;

                RadialGradientPaint glow = new RadialGradientPaint(cx, cy, radius, GLOW_FRACTIONS, GLOW_COLORS);
                g2.setPaint(glow);
                g2.fillOval(cx - radius, cy - radius, radius * 2, radius * 2);
            }
        }
    }
}