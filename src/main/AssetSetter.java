package main;

import data.Progress;
import entity.AOBJ_Fire;
import entity.NPC_Helmart;
import entity.NPC_PumpkinHead;
import entity.NPC_Mortemark;
import environment.Lightning;
import monster.*;
import object.*;
import tile_interactive.*;

import java.io.IOException;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new OBJ_Router(gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Billboard(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 29 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Billboard(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 39 * gp.tileSize;
        i++;


        mapNum = 2;
        gp.obj[mapNum][i] = new OBJ_BillboardSand(gp);
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Tent(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Silver(gp);
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Gold(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 41 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Watering(gp));
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 33 * gp.tileSize;
        i++;


        mapNum = 3;
        i = 0;


        mapNum = 5;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 24 * gp.tileSize;
        i++;

        mapNum = 6;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_StairsDown(gp);
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 28 * gp.tileSize;
        i++;


        mapNum = 8;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_StairsDown(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 26 * gp.tileSize;
        i++;

        mapNum = 10;
        i = 0;

        mapNum = 13;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_StairsDown(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 26 * gp.tileSize;
        i++;

        mapNum = 14;
        i = 0;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 24 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 28 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 31 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 28 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cactus(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 20 * gp.tileSize;
        i++;

        mapNum = 16;
        i = 0;

        gp.obj[mapNum][i] = new OBJ_DoorDungeon(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 34 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_DoorDungeon(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 34 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Gold_Clever(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 40 * gp.tileSize;
        i++;

    }
    public void setNPC() throws IOException {
        int i = 0;

        int mapNum = 0;
        // MAP 0
        gp.npc[mapNum][i] = new NPC_PumpkinHead(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*23;
        gp.npc[mapNum][i].worldY = gp.tileSize*19;

        gp.npc[mapNum][i] = new NPC_Helmart(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*23;
        gp.npc[mapNum][i].worldY = gp.tileSize*10;


        // MAP 1
        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Helmart(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*16;
        gp.npc[mapNum][i].worldY = gp.tileSize*11;


        mapNum = 6;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Mortemark(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*25;
        gp.npc[mapNum][i].worldY = gp.tileSize*23;

    }

    public void setMonster(){
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = 10 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 38 * gp.tileSize;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = 18 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 41 * gp.tileSize;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = 16 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 36 * gp.tileSize;
        i++;

        mapNum = 2;
        i = 0;
        gp.monster[mapNum][i] = new MON_SandSlime(gp);
        gp.monster[mapNum][i].worldX = 35 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 32 * gp.tileSize;
        i++;

        gp.monster[mapNum][i] = new MON_SandSlime(gp);
        gp.monster[mapNum][i].worldX = 29 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 29 * gp.tileSize;
        i++;

        gp.monster[mapNum][i] = new MON_SandSlime(gp);
        gp.monster[mapNum][i].worldX = 30 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 35 * gp.tileSize;
        i++;


        mapNum = 3;
        i = 0;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = 21 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 25 * gp.tileSize;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = 20 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 24 * gp.tileSize;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = 23 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 23 * gp.tileSize;
        i++;

        mapNum = 5;
        i = 0;
        gp.monster[mapNum][i] = new MON_SandSlime(gp);
        gp.monster[mapNum][i].worldX = 24 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 24 * gp.tileSize;
        i++;

        mapNum = 14;
        i = 0;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = 30 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 30 * gp.tileSize;

        mapNum = 16;
        i = 0;

        if (!Progress.skeletonLordDefeated) {
            gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
            gp.monster[mapNum][i].worldX = 25 * gp.tileSize;
            gp.monster[mapNum][i].worldY = 28 * gp.tileSize;
        }

    }
    public void setInteractiveTile(){

        int i = 0;
        int mapNum = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp,14,17);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,12,17);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,11,17);i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp,9,14);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,9,12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,14,10);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,16,10);i++;

        mapNum = 2;
        i=0;
        gp.iTile[mapNum][i] = new IT_Palm(gp,17,12);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,18,19);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,25,23);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,25,25);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,28,25);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,39,22);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,38,7);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,26,34);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,29,43);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,28,39);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,18,40);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,16,36);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,27,17);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,37,19);i++;
        gp.iTile[mapNum][i] = new IT_Palm(gp,36,11);i++;

        gp.iTile[mapNum][i] = new IT_Rock1(gp,30,41);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,29,38);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,34,37);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,36,41);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,33,34);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,26,43);i++;

        gp.iTile[mapNum][i] = new IT_Rock2(gp,32,43);i++;
        gp.iTile[mapNum][i] = new IT_Rock2(gp,34,42);i++;
        gp.iTile[mapNum][i] = new IT_Rock2(gp,29,30);i++;
        gp.iTile[mapNum][i] = new IT_Rock2(gp,31,33);i++;
        gp.iTile[mapNum][i] = new IT_Rock2(gp,36,32);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,35,29);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,13,35);i++;

        gp.iTile[mapNum][i] = new IT_Vase1(gp,13,11);i++;
        gp.iTile[mapNum][i] = new IT_Vase1(gp,34,15);i++;
        gp.iTile[mapNum][i] = new IT_Vase1(gp,17,35);i++;
        gp.iTile[mapNum][i] = new IT_Vase1(gp,14,40);i++;



        mapNum = 3;
        i = 0;

        mapNum = 4;
        i = 0;
        gp.iTile[mapNum][i] = new IT_Vase1(gp,27,26);i++;
        gp.iTile[mapNum][i] = new IT_Vase1(gp,23,24);i++;

        mapNum = 6;
        i = 0;
        gp.iTile[mapNum][i] = new IT_Vase1(gp,30,24);i++;
        gp.iTile[mapNum][i] = new IT_Vase1(gp,21,27);i++;

        mapNum = 7;
        i = 0;
        gp.iTile[mapNum][i] = new IT_Vase1(gp,29,28);i++;
        gp.iTile[mapNum][i] = new IT_Vase1(gp,21,23);i++;
    }

}
