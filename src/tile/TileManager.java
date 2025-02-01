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
    public int[][][] mapTileNum;
    public boolean drawPath = false;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[400];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];


        getTileImage();
        loadMap("/maps/Woods.txt", 0);
        loadMap("/maps/shop.txt", 1);
        loadMap("/maps/SandTown.txt", 2);
        loadMap("/maps/dungeon.txt", 3);
    }

    public void getTileImage(){

        setup(0, "/Woods/001", true);
        setup(1, "/Woods/002", true);
        setup(2, "/Woods/003", true);
        setup(3, "/Woods/004", true);
        setup(4, "/Woods/005", true);
        setup(5, "/Woods/006", true);
        setup(6, "/Woods/007", true);
        setup(7, "/Woods/008", true);
        setup(8, "/Woods/009", true);
        setup(9, "/Woods/010", false);
        setup(10, "/Woods/011", false);
        setup(11, "/Woods/012", true);
        setup(12, "/Woods/013", true);
        setup(13, "/Woods/014", false);
        setup(14, "/Woods/015", false);
        setup(15, "/Woods/016", false);
        setup(16, "/Woods/017", false);
        setup(17, "/Woods/018", false);
        setup(18, "/Woods/019", false);
        setup(19, "/Woods/020", false);
        setup(20, "/Woods/021", true);
        setup(21, "/Woods/022", false);
        setup(22, "/Woods/023", true);
        setup(23, "/Woods/024", true);
        setup(24, "/Woods/025", true);
        setup(25, "/Woods/026", true);
        setup(26, "/Woods/027", true);
        setup(27, "/Woods/028", true);
        setup(28, "/Woods/029", true);
        setup(29, "/Woods/030", false);
        setup(30, "/Woods/031", true);
        setup(31, "/Woods/032", true);
        setup(32, "/Woods/033", true);
        setup(33, "/Woods/034", true);
        setup(34, "/Woods/035", true);
        setup(35, "/Woods/036", true);
        setup(36, "/Woods/037", true);
        setup(37, "/Woods/038", true);
        setup(38, "/Woods/039", true);
        setup(39, "/Woods/040", true);
        setup(40, "/Woods/041", false);
        setup(41, "/Woods/042", false
        );
        setup(42, "/Woods/043", false);
        setup(43, "/Woods/044", false);
        setup(44, "/Woods/045", false);
        setup(45, "/Woods/046", true);
        setup(46, "/Woods/047", false);
        setup(47, "/Woods/048", true);





        setup(100, "/ShopTiles/darkness", true);
        setup(101, "/ShopTiles/s1", true);
        setup(102, "/ShopTiles/s2", true);
        setup(103, "/ShopTiles/s3", true);
        setup(104, "/ShopTiles/s4", true);
        setup(105, "/ShopTiles/s5", true);
        setup(106, "/ShopTiles/s6", true);
        setup(107, "/ShopTiles/s7", true);
        setup(108, "/ShopTiles/s8", true);
        setup(109, "/ShopTiles/s9", true);
        setup(110, "/ShopTiles/s10", true);
        setup(111, "/ShopTiles/s11", true);
        setup(112, "/ShopTiles/s12", false);
        setup(113, "/ShopTiles/s13", false);
        setup(114, "/ShopTiles/s14", false);
        setup(115, "/ShopTiles/s15", true);
        setup(116, "/ShopTiles/s16", false);
        setup(119, "/ShopTiles/s19", true);
        setup(120, "/ShopTiles/s20", true);
        setup(121, "/ShopTiles/s21", true);
        setup(135, "/ShopTiles/s35", true);
        setup(136, "/ShopTiles/s36", true);
        setup(137, "/ShopTiles/s37", true);
        setup(138, "/ShopTiles/s38", true);
        setup(139, "/ShopTiles/s39", true);
        setup(140, "/ShopTiles/carpet1", false);
        setup(141, "/ShopTiles/carpet2", false);
        setup(142, "/ShopTiles/carpet3", false);
        setup(143, "/ShopTiles/carpet4", false);
        setup(144, "/ShopTiles/carpet5", false);
        setup(145, "/ShopTiles/carpet6", false);
        setup(175, "/ShopTiles/s75", true);
        setup(176, "/ShopTiles/doorShop", true);



        setup(200, "/SandTown/sand1", false);
        setup(201, "/SandTown/sand2", false);
        setup(202, "/SandTown/sand3", false);
        setup(203, "/SandTown/home1", true);
        setup(204, "/SandTown/home2", true);
        setup(205, "/SandTown/home3", true);
        setup(206, "/SandTown/home4", true);
        setup(207, "/SandTown/home5", false);
        setup(208, "/SandTown/home6", false);
        setup(209, "/SandTown/cliff1", false);
        setup(210, "/SandTown/cliff2", false);
        setup(211, "/SandTown/cliff3", false);
        setup(212, "/SandTown/crag1", true);
        setup(213, "/SandTown/crag2", true);
        setup(214, "/SandTown/crag3", true);
        setup(215, "/SandTown/grass2", false);
        setup(216, "/SandTown/grass1", false);
        setup(217, "/SandTown/path1", false);
        setup(218, "/SandTown/path2", false);
        setup(219, "/SandTown/path3", false);
        setup(220, "/SandTown/path4", false);
        setup(221, "/SandTown/path5", false);
        setup(222, "/SandTown/path6", false);
        setup(223, "/SandTown/cliff4", true);
        setup(224, "/SandTown/house1", true);
        setup(225, "/SandTown/house2", true);
        setup(226, "/SandTown/house3", true);
        setup(227, "/SandTown/house4", true);
        setup(228, "/SandTown/house5", false);
        setup(229, "/SandTown/house6", false);
        setup(230, "/SandTown/path8", false);
        setup(231, "/SandTown/path9", false);
        setup(232, "/SandTown/water1", true);
        setup(233, "/SandTown/bridge", false);
        setup(234, "/SandTown/bridge2", true);
        setup(235, "/SandTown/water2", true);
        setup(236, "/SandTown/water3", true);
        setup(237, "/SandTown/cliff5", true);
        setup(238, "/SandTown/cliff6", true);
        setup(239, "/SandTown/cliff7", true);
        setup(240, "/SandTown/bridge3", true);
        setup(241, "/SandTown/water4", true);
        setup(242, "/SandTown/path10", false);
        setup(243, "/SandTown/cliff8", true);
        setup(244, "/SandTown/cliff9", true);
        setup(245, "/SandTown/crag4", true);
        setup(246, "/SandTown/crag5", true);
        setup(247, "/SandTown/crag6", true);
        setup(248, "/SandTown/cliff10", true);
        setup(249, "/SandTown/cliff11", true);
        setup(250, "/SandTown/crag7", true);
        setup(251, "/SandTown/cliff12", true);
        setup(252, "/SandTown/water5", true);
        setup(253, "/SandTown/cliff13", true);
        setup(254, "/SandTown/cliff14", true);
        setup(255, "/SandTown/crag8", true);
        setup(256, "/SandTown/crag9", true);
        setup(257, "/SandTown/cliff16", true);
        setup(258, "/SandTown/cliff17", true);
        setup(259, "/SandTown/cliff15", true);
        setup(260, "/SandTown/cliff18", true);
        setup(261, "/SandTown/cliff19", true);
        setup(262, "/SandTown/cliff20", true);
        setup(263, "/SandTown/wellSand", true);
        setup(264, "/SandTown/shop1", true);
        setup(265, "/SandTown/shop2", true);
        setup(266, "/SandTown/shop3", true);
        setup(267, "/SandTown/shop4", true);
        setup(268, "/SandTown/shop5", true);
        setup(269, "/SandTown/shop6", true);
        setup(270, "/SandTown/shop7", true);
        setup(271, "/SandTown/shop8", true);
        setup(272, "/SandTown/shop9", true);
        setup(273, "/SandTown/shop10", false);
        setup(274, "/SandTown/shop11", false);
        setup(275, "/SandTown/upper1", true);
        setup(276, "/SandTown/upper2", true);
        setup(277, "/SandTown/upper3", false);
        setup(278, "/SandTown/upper4", true);
        setup(279, "/SandTown/upper5", false);
        setup(280, "/SandTown/upper6", false);
        setup(281, "/SandTown/pit1", true);
        setup(282, "/SandTown/pit2", true);
        setup(283, "/SandTown/pit3", true);
        setup(284, "/SandTown/pit4", true);
        setup(285, "/SandTown/pit5", true);
        setup(286, "/SandTown/pit6", true);
        setup(287, "/SandTown/pit7", true);
        setup(288, "/SandTown/pit8", true);
        setup(289, "/SandTown/pit9", true);
        setup(290, "/SandTown/cave", false);
        setup(291, "/SandTown/upper7", true);
        setup(292, "/SandTown/upper8", false);
        setup(293, "/SandTown/upper9", true);
        setup(294, "/SandTown/palm1", true);
        setup(295, "/SandTown/palm2", true);

        setup(330, "/Dungeon/darkness", false);
        setup(335, "/Dungeon/flor", false);
        setup(340, "/Dungeon/wall1", true);
        setup(345, "/Dungeon/wall2", true);
        setup(350, "/Dungeon/wall3", true);
        setup(355, "/Dungeon/wall4", true);
        setup(360, "/Dungeon/wall5", true);
        setup(365, "/Dungeon/wall6", true);
        setup(370, "/Dungeon/wall7", true);
        setup(375, "/Dungeon/wall8", true);
        setup(380, "/Dungeon/wall9", true);
        setup(385, "/Dungeon/wall10", true);
        setup(390, "/Dungeon/wall11", true);
        setup(395, "/Dungeon/wall12", true);
        setup(397, "/Dungeon/skull", false);
        setup(398, "/Dungeon/wall13", true);
        setup(399, "/Dungeon/wall14", true);




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
    public void loadMap(String filePath, int map){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol){

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
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
                int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

                if (tileNum >= 0 && tileNum < tile.length) {
                    int worldX = worldCol * gp.tileSize;
                    int worldY = worldRow * gp.tileSize;
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;

                    if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX - 2 * gp.tileSize &&
                        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX + 2 * gp.tileSize &&
                        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY - 2 * gp.tileSize &&
                        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY + 2 * gp.tileSize) {

                        g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                    }



                } else {
                    System.err.println("Некоректний індекс тайла: " + tileNum);
                }


            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        // IT DRAWS THE PATH WITH RED COLOR
        if (drawPath){
            g2.setColor(new Color(255,0,0,70));

            for (int i = 0; i < gp.pFinder.pathList.size(); i++) {

                int worldX = gp.pFinder.pathList.get(i).col  * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row  * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }

}
