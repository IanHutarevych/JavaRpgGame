package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[80];
        this.mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt"); // CHANGE WORLD MAPS
    }

    public void getTileImage(){


        setup(0, "grass1", false);
        setup(1, "grass1", false);
        setup(2, "grass1", false);
        setup(3, "grass1", false);
        setup(4, "grass1", false);
        setup(5, "grass1", false);
        setup(6, "grass1", false);
        setup(7, "grass1", false);
        setup(8, "grass1", false);
        setup(9, "grass1", false);


        setup(10, "grass2", false);
        setup(11, "grass1", false);
        setup(12, "tree2", true);
        setup(13, "tree1", true);
        setup(14, "pathway1", false);
        setup(15, "pathway2", false);
        setup(16, "wall1", true);
        setup(17, "wall2", true);
        setup(18, "flor", false);
        setup(19, "tree3", true);
        setup(20, "tree4", true);
        setup(21, "water10", false); // u can change
        setup(22, "pathway3", false);
        setup(23, "pathway4", false);
        setup(24, "pathway5", false);
        setup(25, "pathway6", false);
        setup(26, "pathway7", false);
        setup(27, "pathway8", false);
        setup(28, "pathway9", false);
        setup(29, "path-flor2", false);
        setup(30, "path-flor1", false);
        setup(31, "wall3", true);
        setup(32, "wall4", true);
        setup(33, "wall5", true);
        setup(34, "wall6", true);
        setup(35, "wall7", true);
        setup(36, "water1", true);
        setup(37, "water2", true);
        setup(38, "water3", true);
        setup(39, "water4", true);
        setup(40, "water5", true);
        setup(41, "water6", true);
        setup(42, "water7", true);
        setup(43, "water8", true);
        setup(44, "water9", true);
        setup(45, "tree5", true);
        setup(46, "tree6", true);
        setup(47, "tree7", true);
        setup(48, "tree8", true);
        setup(49, "tree9", true);
        setup(50, "tree10", true);
        setup(51, "tree11", true);
        setup(52, "tree12", true);
        setup(53, "tree13", false);
        setup(54, "tree14", true);
        setup(55, "tree15", true);
        setup(56, "tree16", false);
        setup(57, "tree17", true);
        setup(58, "tree18", true);
        setup(59, "tree19", false);
        setup(60, "tree20", true);
        setup(61, "tree21", true);
        setup(62, "router", false);
        setup(63, "well", true);
        setup(64, "bridge1", false);
        setup(65, "bridge2", false);
        setup(66, "bridge3", false);
    }

    public void setup (int index, String imagePath, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e ){
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            if (worldCol < mapTileNum.length && worldRow < mapTileNum[worldCol].length) {
                int tileNum = mapTileNum[worldCol][worldRow];

                if (tileNum >= 0 && tileNum < tile.length) {
                    int worldX = worldCol * gp.tileSize;
                    int worldY = worldRow * gp.tileSize;
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;

                    if (worldX > gp.player.worldX - gp.player.screenX - 2 * gp.tileSize &&
                        worldX < gp.player.worldX + gp.player.screenX + 2 * gp.tileSize &&
                        worldY > gp.player.worldY - gp.player.screenY - 2 * gp.tileSize &&
                        worldY < gp.player.worldY + gp.player.screenY + 2 * gp.tileSize) {

                        g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                    }



                } else {
                    System.err.println("Некоректний індекс тайла: " + tileNum);
                }
            } else {
                System.err.println("Некоректний доступ до mapTileNum: [" + worldCol + "][" + worldRow + "]");
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
