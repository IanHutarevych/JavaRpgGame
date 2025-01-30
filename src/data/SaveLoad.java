package data;

import main.GamePanel;

import java.io.*;
import entity.Entity;
import object.*;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelUp = gp.player.nextLevelUp;
            ds.coin = gp.player.coin;

            // player inventory
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmount.add(gp.player.inventory.get(i).amount);
            }
            // player equipment
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();



            // obj on the map
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
            //ds.mapObjectCollision = new boolean[gp.maxMap][gp.obj[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {

                for (int i = 0; i < gp.obj[1].length; i++) {

                    if (gp.obj[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "NA";
                    }
                    else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if (gp.obj[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                        //ds.mapObjectCollision[mapNum][i] = gp.obj[mapNum][i].collision;
                    }
                }
            }
            // write the ds obj
            oos.writeObject(ds);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // read the ds obj
            DataStorage ds = (DataStorage) ois.readObject();
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelUp = ds.nextLevelUp;
            gp.player.coin = ds.coin;

            // player inventory
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmount.get(i);
            }

            // player equipment
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefence();
            gp.player.getAttackImage();
            gp.player.getGuardImage();

            // obj on the map
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {

                for (int i = 0; i < gp.obj[1].length; i++) {

                    if (ds.mapObjectNames[mapNum][i].equals("NA")) {
                        gp.obj[mapNum][i] = null;
                    }
                    else {
                        gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if (ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.obj[mapNum][i].loot = gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        //gp.obj[mapNum][i].collision = ds.mapObjectOpened[mapNum][i];
                        if (gp.obj[mapNum][i].opened) {
                            if (gp.obj[mapNum][i].type == gp.obj[mapNum][i].type_obstacle){
                                gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].down2;
                                gp.obj[mapNum][i].collision = false;
                            }
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
           // System.out.println("Load Exception" + e.getMessage());
        }
    }
}
