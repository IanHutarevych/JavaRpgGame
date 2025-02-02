package main;

import entity.AOBJ_Fire;
import entity.NPC_PumpkinHead;
import entity.NPC_Mortemark;
import monster.MON_GreenSlime;
import monster.MON_Ogre;
import monster.MON_SandSlime;
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


        mapNum = 3;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_DoorDungeon(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 38 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 39 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 35 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_StairsUp(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 42 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_StairsDown(gp);
        gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 24 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Health_Small(gp));
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = 40 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 37 * gp.tileSize;
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
        gp.monster[mapNum][i] = new MON_Ogre(gp);
        gp.monster[mapNum][i].worldX = 24 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 24 * gp.tileSize;
        i++;

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

        gp.iTile[mapNum][i] = new IT_Rock1(gp,30,41);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,29,38);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,34,37);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,36,41);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,33,34);i++;

        gp.iTile[mapNum][i] = new IT_Rock2(gp,32,43);i++;
        gp.iTile[mapNum][i] = new IT_Rock2(gp,34,42);i++;
        gp.iTile[mapNum][i] = new IT_Rock2(gp,29,30);i++;
        gp.iTile[mapNum][i] = new IT_Rock2(gp,31,33);i++;
        gp.iTile[mapNum][i] = new IT_Rock2(gp,36,32);i++;
        gp.iTile[mapNum][i] = new IT_Rock1(gp,35,29);i++;



        mapNum = 3;
        i = 0;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18,31);i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18,20);i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,36,31);i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,25,17);i++;

    }

}
