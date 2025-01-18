package main;

import entity.AOBJ_Fire;
import entity.NPC_PumpkinHead;
import entity.NPC_Mortemark;
import monster.MON_GreenSlime;
import object.*;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_Palm;
import tile_interactive.IT_Rock;

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


        gp.obj[mapNum][i] = new OBJ_Potion_Health_Small(gp);
        gp.obj[mapNum][i].worldX = 4 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Health_Small(gp);
        gp.obj[mapNum][i].worldX = 3 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Health_Small(gp);
        gp.obj[mapNum][i].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;
        /*
        gp.obj[i] = new OBJ_Swamp2(gp);
        gp.obj[i].worldX = 35 * gp.tileSize;
        gp.obj[i].worldY = 32 * gp.tileSize;
        i++;*/



        gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Shield_Metal(gp));
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

        mapNum = 2;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 22 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        i++;


        gp.obj[mapNum][i] = new OBJ_Cactus_Ship(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;


        gp.obj[mapNum][i] = new OBJ_Cactus_Ship(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 16 * gp.tileSize;
        i++;

        /*gp.obj[mapNum][i] = new OBJ_Campfire(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;*/

        gp.obj[mapNum][i] = new OBJ_Barrel(gp, new OBJ_Axe(gp));
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BillboardSand(gp);
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

    }
    public void setNPC() throws IOException {
        int i = 0;

        int mapNum = 0;
        // MAP 0
        gp.npc[mapNum][i] = new NPC_PumpkinHead(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*23;
        gp.npc[mapNum][i].worldY = gp.tileSize*19;


        // MAP 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Mortemark(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*23;
        gp.npc[mapNum][i].worldY = gp.tileSize*16;


        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new AOBJ_Fire(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*21;
        gp.npc[mapNum][i].worldY = gp.tileSize*12;
        i++;

        /*gp.npc[mapNum][i] = new NPC_PumpkinHead(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*20;
        gp.npc[mapNum][i].worldY = gp.tileSize*26;*/


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

        mapNum = 2;
        i=0;
        gp.iTile[mapNum][i] = new IT_Palm(gp,18,15);i++;
        gp.iTile[mapNum][i] = new IT_Rock(gp,21,16);i++;
    }

}
