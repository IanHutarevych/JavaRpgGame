package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
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
        loadMap("/maps/Woods/Woods.txt", 0);
        //loadMap("/maps/Desert/FinalDungeon.txt", 1);
        loadMap("/maps/Desert/Desert.txt", 2);
        loadMap("/maps/Desert/Transition.txt", 3);
        loadMap("/maps/Desert/SandIndoor1.txt", 4);
        loadMap("/maps/Desert/SandIndoor2.txt", 5);
        loadMap("/maps/Desert/SandIndoor3.txt", 6);
        loadMap("/maps/Desert/SandIndoor4.txt", 7);
        loadMap("/maps/Desert/SandIndoor5.txt", 8);
        loadMap("/maps/Desert/SandIndoor6.txt", 9);
        loadMap("/maps/Desert/SandIndoor7.txt", 10);
        loadMap("/maps/Desert/SandIndoor8.txt", 11);
        loadMap("/maps/Desert/SandIndoor9.txt", 12);
        loadMap("/maps/Desert/SandIndoor10.txt", 13);
        loadMap("/maps/Desert/Spawn.txt", 14);
        loadMap("/maps/Desert/House.txt", 15);
        loadMap("/maps/Desert/FinalDungeon.txt", 16);
    }

    public void getTileImage(){

        // WOODS
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
        setup(41, "/Woods/042", false);
        setup(42, "/Woods/043", false);
        setup(43, "/Woods/044", false);
        setup(44, "/Woods/045", false);
        setup(45, "/Woods/046", true);
        setup(46, "/Woods/047", false);
        setup(47, "/Woods/048", true);

        // DESERT
        setup(48, "/Desert/049", true);
        setup(49, "/Desert/050", true);
        setup(50, "/Desert/051", false);
        setup(51, "/Desert/052", true);
        setup(52, "/Desert/053", true);
        setup(53, "/Desert/054", false);
        setup(54, "/Desert/055", false);
        setup(55, "/Desert/056", true);
        setup(56, "/Desert/057", true);
        setup(57, "/Desert/058", false);
        setup(58, "/Desert/059", false);
        setup(59, "/Desert/060", false);
        setup(60, "/Desert/061", false);
        setup(61, "/Desert/062", false);
        setup(62, "/Desert/063", false);
        setup(63, "/Desert/064", false);
        setup(64, "/Desert/065", false);
        setup(65, "/Desert/066", false);
        setup(66, "/Desert/067", false);
        setup(67, "/Desert/068", false);
        setup(68, "/Desert/069", false);
        setup(69, "/Desert/070", true);
        setup(70, "/Desert/071", true);
        setup(71, "/Desert/072", true);
        setup(72, "/Desert/073", true);
        setup(73, "/Desert/074", true);
        setup(74, "/Desert/075", true);
        setup(75, "/Desert/076", true);
        setup(76, "/Desert/077", true);
        setup(77, "/Desert/078", true);
        setup(78, "/Desert/079", true);
        setup(79, "/Desert/080", true);
        setup(80, "/Desert/081", true);
        setup(81, "/Desert/082", false);
        setup(82, "/Desert/083", false);
        setup(83, "/Desert/084", false);
        setup(84, "/Desert/085", false);
        setup(85, "/Desert/086", false);
        setup(86, "/Desert/087", false);
        setup(87, "/Desert/088", false);
        setup(88, "/Desert/089", false);
        setup(89, "/Desert/090", false);
        setup(90, "/Desert/091", false);
        setup(91, "/Desert/092", false);
        setup(92, "/Desert/093", false);
        setup(93, "/Desert/094", false);
        setup(94, "/Desert/095", false);
        setup(95, "/Desert/096", false);
        setup(96, "/Desert/097", false);
        setup(97, "/Desert/098", false);
        setup(98, "/Desert/099", false);
        setup(99, "/Desert/100", false);
        setup(100, "/Desert/101", true);
        setup(101, "/Desert/102", true);
        setup(102, "/Desert/103", false);
        setup(103, "/Desert/104", false);
        setup(104, "/Desert/105", true);
        setup(105, "/Desert/106", true);
        setup(106, "/Desert/107", true);
        setup(107, "/Desert/108", true);
        setup(108, "/Desert/109", false);
        setup(109, "/Desert/110", false);
        setup(110, "/Desert/111", true);
        setup(111, "/Desert/112", true);
        setup(112, "/Desert/113", false);
        setup(113, "/Desert/114", false);
        setup(114, "/Desert/115", false);
        setup(115, "/Desert/116", false);
        setup(116, "/Desert/117", false);
        setup(117, "/Desert/118", false);
        setup(118, "/Desert/119", false);
        setup(119, "/Desert/120", false);
        setup(120, "/Desert/121", false);
        setup(121, "/Desert/122", false);
        setup(122, "/Desert/123", false);
        setup(123, "/Desert/124", true);
        setup(124, "/Desert/125", true);
        setup(125, "/Desert/126", true);
        setup(126, "/Desert/127", true);
        setup(127, "/Desert/128", true);
        setup(128, "/Desert/129", true);
        setup(129, "/Desert/130", true);
        setup(130, "/Desert/131", true);
        setup(131, "/Desert/132", true);
        setup(132, "/Desert/133", false);
        setup(133, "/Desert/134", true);
        setup(134, "/Desert/135", false);
        setup(135, "/Desert/136", true);
        setup(136, "/Desert/137", true);
        setup(137, "/Desert/138", true);
        setup(138, "/Desert/139", true);
        setup(139, "/Desert/140", true);
        setup(140, "/Desert/141", true);
        setup(141, "/Desert/142", false);
        setup(142, "/Desert/143", false);
        setup(143, "/Desert/144", false);
        setup(144, "/Desert/145", true);
        setup(145, "/Desert/146", false);
        setup(146, "/Desert/147", false);
        setup(147, "/Desert/148", true);
        setup(148, "/Desert/149", true);
        setup(149, "/Desert/150", true);
        setup(150, "/Desert/151", false);
        setup(151, "/Desert/152", false);
        setup(152, "/Desert/153", true);
        setup(153, "/Desert/154", true);
        setup(154, "/Desert/155", true);
        setup(155, "/Desert/156", true);
        setup(156, "/Desert/157", true);
        setup(157, "/Desert/158", false);
        setup(158, "/Desert/159", true);
        setup(159, "/Desert/160", true);
        setup(160, "/Desert/161", true);
        setup(161, "/Desert/162", false);
        setup(162, "/Desert/163", false);
        setup(163, "/Desert/164", false);
        setup(164, "/Desert/165", false);
        setup(165, "/Desert/166", false);
        setup(166, "/Desert/167", true);
        setup(167, "/Desert/168", true);

        setup(200, "/Desert/201", true);
        setup(201, "/Desert/202", true);
        setup(202, "/Desert/203", true);
        setup(203, "/Desert/204", true);
        setup(204, "/Desert/205", true);
        setup(205, "/Desert/206", true);
        setup(206, "/Desert/207", true);
        setup(207, "/Desert/208", true);
        setup(208, "/Desert/209", false);
        setup(209, "/Desert/210", false);
        setup(210, "/Desert/211", false);
        setup(211, "/Desert/212", false);
        setup(212, "/Desert/213", false);
        setup(213, "/Desert/214", true);
        setup(214, "/Desert/215", true);
        setup(215, "/Desert/216", true);
        setup(216, "/Desert/217", true);
        setup(217, "/Desert/218", true);
        setup(218, "/Desert/219", true);
        setup(219, "/Desert/220", false);
        setup(220, "/Desert/221", false);
        setup(221, "/Desert/222", false);
        setup(222, "/Desert/223", false);
        setup(223, "/Desert/224", false);
        setup(224, "/Desert/225", true);
        setup(225, "/Desert/226", true);
        setup(226, "/Desert/227", true);
        setup(227, "/Desert/228", false);
        setup(228, "/Desert/229", false);
        setup(229, "/Desert/230", true);
        setup(230, "/Desert/231", true);
        setup(231, "/Desert/232", true);
        setup(232, "/Desert/233", true);
        setup(233, "/Desert/234", false);
        setup(234, "/Desert/235", true);
        setup(235, "/Desert/236", true);
        setup(236, "/Desert/237", true);
        setup(237, "/Desert/238", true);
        setup(238, "/Desert/239", true);
        setup(239, "/Desert/240", true);
        setup(240, "/Desert/241", true);
        setup(241, "/Desert/242", true);
        setup(242, "/Desert/243", true);
        setup(243, "/Desert/244", true);
        setup(244, "/Desert/245", true);
        setup(245, "/Desert/246", false);
        setup(246, "/Desert/247", false);
        setup(247, "/Desert/248", true);
        setup(248, "/Desert/249", true);
        setup(249, "/Desert/250", true);
        setup(250, "/Desert/251", false);
        setup(251, "/Desert/252", true);
        setup(252, "/Desert/253", true);
        setup(253, "/Desert/254", true);
        setup(254, "/Desert/255", false);
        setup(255, "/Desert/256", true);
        setup(256, "/Desert/257", false);
        setup(257, "/Desert/258", true);
        setup(258, "/Desert/259", true);
        setup(259, "/Desert/260", true);
        setup(260, "/Desert/261", true);
        setup(261, "/Desert/262", true);
        setup(262, "/Desert/263", true);
        setup(263, "/Desert/264", true);
        setup(264, "/Desert/265", true);
        setup(265, "/Desert/266", true);
        setup(266, "/Desert/267", true);
        setup(267, "/Desert/268", true);
        setup(268, "/Desert/269", false);
        setup(269, "/Desert/270", true);
        setup(270, "/Desert/271", true);
        setup(271, "/Desert/272", false);
        setup(272, "/Desert/273", true);
        setup(273, "/Desert/274", true);

        setup(300, "/House/301", true);
        setup(301, "/House/302", true);
        setup(302, "/House/303", true);
        setup(303, "/House/304", true);
        setup(304, "/House/305", true);
        setup(305, "/House/306", true);
        setup(306, "/House/307", true);
        setup(307, "/House/308", false);
        setup(308, "/House/309", true);
        setup(309, "/House/310", true);
        setup(310, "/House/311", true);
        setup(311, "/House/312", true);
        setup(312, "/House/313", true);
        setup(313, "/House/314", true);
        setup(314, "/House/315", true);
        setup(315, "/House/316", true);
        setup(316, "/House/317", true);
        setup(317, "/House/318", false);
        setup(318, "/House/319", false);
        setup(319, "/House/320", false);
        setup(320, "/House/321", false);
        setup(321, "/House/322", false);
        setup(322, "/House/323", false);
        setup(323, "/House/324", false);
        setup(324, "/House/325", false);
        setup(325, "/House/326", true);
        setup(326, "/House/327", true);
        setup(327, "/House/328", true);
        setup(328, "/House/329", true);
        setup(329, "/House/330", true);
        setup(330, "/House/331", false);
        setup(331, "/House/332", true);
        setup(332, "/House/333", true);
        setup(333, "/House/334", true);
        setup(334, "/House/335", true);
        setup(335, "/House/336", true);
        setup(336, "/House/337", false);
        setup(337, "/House/338", false);
        setup(338, "/Dungeon/339", false);
        setup(339, "/Desert/340", true);

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
