package main;

import entity.NPC_PumpkinHead;
import monster.MON_GreenSlime;
import object.*;

import java.io.IOException;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){

        /*gp.obj[0] = new OBJ_Swamp3(gp);
        gp.obj[0].worldX = 35 * gp.tileSize;
        gp.obj[0].worldY = 30 * gp.tileSize;

        gp.obj[1] = new OBJ_Swamp2(gp);
        gp.obj[1].worldX = 35 * gp.tileSize;
        gp.obj[1].worldY = 31 * gp.tileSize;

        gp.obj[2] = new OBJ_Swamp2(gp);
        gp.obj[2].worldX = 35 * gp.tileSize;
        gp.obj[2].worldY = 32 * gp.tileSize;*/

        /*gp.obj[3] = new OBJ_Key(gp);
        gp.obj[3].worldX = 9 * gp.tileSize;
        gp.obj[3].worldY = 35 * gp.tileSize;*/

        gp.obj[4] = new OBJ_ChestClose(gp);
        gp.obj[4].worldX = 35 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5] = new OBJ_Billboard(gp);
        gp.obj[5].worldX = 34 * gp.tileSize;
        gp.obj[5].worldY = 34 * gp.tileSize;


        gp.obj[6] = new OBJ_Router(gp);
        gp.obj[6].worldX = 24 * gp.tileSize;
        gp.obj[6].worldY = 25 * gp.tileSize;

        gp.obj[7] = new OBJ_Router(gp);
        gp.obj[7].worldX = 24 * gp.tileSize;
        gp.obj[7].worldY = 38 * gp.tileSize;

        gp.obj[8] = new OBJ_DoorClose(gp);
        gp.obj[8].worldX = 4 * gp.tileSize;
        gp.obj[8].worldY = 22 * gp.tileSize;

    }
    public void setNPC() throws IOException {

        gp.npc[0] = new NPC_PumpkinHead(gp);
        gp.npc[0].worldX = gp.tileSize*26;
        gp.npc[0].worldY = gp.tileSize*21;

    }

    public void setMonster(){
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = 9 * gp.tileSize;
        gp.monster[0].worldY = 35 * gp.tileSize;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = 11 * gp.tileSize;
        gp.monster[1].worldY = 36 * gp.tileSize;

        /*gp.monster[2] = new MON_GreenSlime(gp);
        gp.monster[2].worldX = 9 * gp.tileSize;
        gp.monster[2].worldY = 35 * gp.tileSize;*/
    }

}
