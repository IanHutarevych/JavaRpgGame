package main;

import entity.NPC_PumpkinHead;
import monster.MON_GreenSlime;
import object.*;
import tile_interactive.IT_DryTree;
import tile_interactive.InteractiveTile;

import java.io.IOException;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        int i = 0;
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = 39 * gp.tileSize;
        gp.obj[i].worldY = 39 * gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 9 * gp.tileSize;
        gp.obj[i].worldY = 35 * gp.tileSize;
        i++;


        gp.obj[i] = new OBJ_Potion_Health(gp);
        gp.obj[i].worldX = 4 * gp.tileSize;
        gp.obj[i].worldY = 19 * gp.tileSize;
        i++;
        /*
        gp.obj[i] = new OBJ_Swamp2(gp);
        gp.obj[i].worldX = 35 * gp.tileSize;
        gp.obj[i].worldY = 32 * gp.tileSize;
        i++;*/



        gp.obj[i] = new OBJ_ChestClose(gp);
        gp.obj[i].worldX = 35 * gp.tileSize;
        gp.obj[i].worldY = 28 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Billboard(gp);
        gp.obj[i].worldX = 34 * gp.tileSize;
        gp.obj[i].worldY = 34 * gp.tileSize;
        i++;


        gp.obj[i] = new OBJ_Router(gp);
        gp.obj[i].worldX = 24 * gp.tileSize;
        gp.obj[i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Router(gp);
        gp.obj[i].worldX = 24 * gp.tileSize;
        gp.obj[i].worldY = 38 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_DoorClose(gp);
        gp.obj[i].worldX = 4 * gp.tileSize;
        gp.obj[i].worldY = 22 * gp.tileSize;
        i++;

    }
    public void setNPC() throws IOException {

        gp.npc[0] = new NPC_PumpkinHead(gp);
        gp.npc[0].worldX = gp.tileSize*26;
        gp.npc[0].worldY = gp.tileSize*21;

    }

    public void setMonster(){
        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 9 * gp.tileSize;
        gp.monster[i].worldY = 35 * gp.tileSize;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 11 * gp.tileSize;
        gp.monster[i].worldY = 36 * gp.tileSize;
        i++;

    }
    public void setInteractiveTile(){

        int i = 0;

        gp.iTile[i] = new IT_DryTree(gp,34,37);i++;
        gp.iTile[i] = new IT_DryTree(gp,38,38);i++;
        gp.iTile[i] = new IT_DryTree(gp,37,35);i++;
    }

}
