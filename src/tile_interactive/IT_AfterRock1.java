package tile_interactive;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaStar;

import java.util.Random;

public class IT_AfterRock1 extends InteractiveTile {

    GamePanel gp;

    public IT_AfterRock1(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp. tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/afterRock1",gp.tileSize, gp.tileSize);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }


}
