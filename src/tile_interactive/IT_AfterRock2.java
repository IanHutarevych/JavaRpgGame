package tile_interactive;
import main.GamePanel;

public class IT_AfterRock2 extends InteractiveTile {

    GamePanel gp;

    public IT_AfterRock2(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp. tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/afterRock2",gp.tileSize, gp.tileSize);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
