package main;

import entity.NPC_OldMan;
import object.OBJ_ChestClose;
import object.OBJ_Swamp1;
import object.OBJ_Swamp2;
import object.OBJ_Swamp3;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){

        gp.obj[0] = new OBJ_Swamp3(gp);
        gp.obj[0].worldX = 35 * gp.tileSize;
        gp.obj[0].worldY = 30 * gp.tileSize;

        gp.obj[1] = new OBJ_Swamp2(gp);
        gp.obj[1].worldX = 35 * gp.tileSize;
        gp.obj[1].worldY = 31 * gp.tileSize;

        gp.obj[2] = new OBJ_Swamp2(gp);
        gp.obj[2].worldX = 35 * gp.tileSize;
        gp.obj[2].worldY = 32 * gp.tileSize;

        gp.obj[3] = new OBJ_Swamp1(gp);
        gp.obj[3].worldX = 35 * gp.tileSize;
        gp.obj[3].worldY = 33 * gp.tileSize;

        gp.obj[4] = new OBJ_ChestClose(gp);
        gp.obj[4].worldX = 35 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

    }
    public void setNPC(){

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*26;
        gp.npc[0].worldY = gp.tileSize*21;
    }

}
