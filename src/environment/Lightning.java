package environment;

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
    public int dayState = day;

    public Lightning(GamePanel gp) {

        this.gp = gp;
        setLightSource();
    }
    public void setLightSource(){

        // Create a buffered image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 =darknessFilter.createGraphics();

        if (gp.player.currentLight == null){
            g2.setColor(new Color(0, 0, 0.1f, 0.95f));
        }
        else{
            // Get the center x and y of the light circle
            int centerX = gp.player.screenX + (gp.tileSize) / 2;
            int centerY = gp.player.screenY + (gp.tileSize) / 2;

            Color[] color = new Color[12];
            float fraction[] = new float[12];

            color[0] = new Color(0, 0, 0.1f, 0f);
            color[1] = new Color(0, 0, 0.1f, 0.1f);
            color[2] = new Color(0, 0, 0.1f, 0.42f);
            color[3] = new Color(0, 0, 0.1f, 0.52f);
            color[4] = new Color(0, 0, 0.1f, 0.61f);
            color[5] = new Color(0, 0, 0.1f, 0.69f);
            color[6] = new Color(0, 0, 0.1f, 0.76f);
            color[7] = new Color(0, 0, 0.1f, 0.82f);
            color[8] = new Color(0, 0, 0.1f, 0.87f);
            color[9] = new Color(0, 0, 0.1f, 0.91f);
            color[10] = new Color(0, 0, 0.1f, 0.92f);
            color[11] = new Color(0, 0, 0.1f, 0.96f);

            fraction[0] = 0f;
            fraction[1] = 0.1f;
            fraction[2] = 0.42f;
            fraction[3] = 0.52f;
            fraction[4] = 0.61f;
            fraction[5] = 0.69f;
            fraction[6] = 0.76f;
            fraction[7] = 0.82f;
            fraction[8] = 0.87f;
            fraction[9] = 0.91f;
            fraction[10] = 0.92f;
            fraction[11] = 0.96f;

            // Create a gradation paint settings for the light circle
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color );

            // Set the gradient data on g2
            g2.setPaint(gPaint);
        }

        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.dispose();
    }
    public void resetDay() {
        dayCounter = day;
        filterAlpha = 0f;
    }
    public void update() {
        if (gp.player.lightUpdated){
            setLightSource();
            gp.player.lightUpdated = false;
        }

        // Check the state of the day
        if (dayState == day){

            dayCounter++;
            if (dayCounter > 200){
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if (dayState == dusk){
            filterAlpha += 0.001f;

            if (filterAlpha > 1f){
                filterAlpha = 1f;
                dayState = night;
            }
        }
        if (dayState == night){
            dayCounter++;

            if (dayCounter > 1800){
                dayState = dawn;
                dayCounter = 0;

            }
        }
        if (dayState == dawn){
            filterAlpha -= 0.001f;

            if (filterAlpha < 0f){
                filterAlpha = 0f;
                dayState = day;
            }
        }
    }
    public void draw (Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter,0,0,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
        String period = "";

        switch (dayState){
            case day: period = "Day"; break;
            case dawn: period = "Dawn"; break;
            case dusk: period = "Dusk"; break;
            case night: period = "Night"; break;
        }
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.drawString(period, 800, 500);
    }
}
