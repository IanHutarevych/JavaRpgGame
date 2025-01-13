package main;

import entity.NPC_PumpkinHead;
import entity.NPC_Skeleton;
import monster.MON_GreenSlime;
import object.*;
import tile_interactive.IT_DryTree;

import java.io.IOException;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 39 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 35 * gp.tileSize;
        i++;


        gp.obj[mapNum][i] = new OBJ_Potion_Health(gp);
        gp.obj[mapNum][i].worldX = 4 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;
        /*
        gp.obj[i] = new OBJ_Swamp2(gp);
        gp.obj[i].worldX = 35 * gp.tileSize;
        gp.obj[i].worldY = 32 * gp.tileSize;
        i++;*/



        gp.obj[mapNum][i] = new OBJ_ChestClose(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 28 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Billboard(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 34 * gp.tileSize;
        i++;


        gp.obj[mapNum][i] = new OBJ_Router(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Router(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 38 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_DoorClose(gp);
        gp.obj[mapNum][i].worldX = 4 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 22 * gp.tileSize;
        i++;

    }
    public void setNPC() throws IOException {
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_PumpkinHead(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*26;
        gp.npc[mapNum][i].worldY = gp.tileSize*21;

        mapNum = 1;
        gp.npc[mapNum][i] = new NPC_Skeleton(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*23;
        gp.npc[mapNum][i].worldY = gp.tileSize*16;

    }

    public void setMonster(){
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = 9 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 35 * gp.tileSize;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = 11 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 36 * gp.tileSize;
        i++;

    }
    public void setInteractiveTile(){

        int i = 0;
        int mapNum = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp,34,37);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,38,38);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,37,35);i++;
    }

}
