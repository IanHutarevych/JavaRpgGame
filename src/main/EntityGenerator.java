package main;

import entity.Entity;
import object.*;


public class EntityGenerator {
    GamePanel gp;

    public EntityGenerator(GamePanel gp) {
        this.gp = gp;

    }
    public Entity getObject (String itemName) {
        Entity obj = null;

        switch (itemName) {
            case OBJ_Archangel_Sword.objName: obj = new OBJ_Archangel_Sword(gp); break;
            case OBJ_Axe.objName: obj = new OBJ_Axe(gp); break;
            case OBJ_Barrel.objName: obj = new OBJ_Barrel(gp); break;
            case OBJ_Billboard.objName: obj = new OBJ_Billboard(gp); break;
            case OBJ_BillboardSand.objName: obj = new OBJ_BillboardSand(gp); break;
            case OBJ_Boots.objName: obj = new OBJ_Boots(gp); break;
            case OBJ_Cactus.objName: obj = new OBJ_Cactus(gp); break;
            case OBJ_Cactus_Ship.objName: obj = new OBJ_Cactus_Ship(gp); break;
            case OBJ_Chest.objName:  obj = new OBJ_Chest(gp); break;
            case OBJ_Coconut.objName: obj = new OBJ_Coconut(gp); break;
            case OBJ_Coin_Bronze.objName: obj = new OBJ_Coin_Bronze(gp); break;
            case OBJ_Coin_Gold.objName: obj = new OBJ_Coin_Gold(gp); break;
            case OBJ_Coin_Silver.objName: obj = new OBJ_Coin_Silver(gp); break;
            case OBJ_DoorClose.objName: obj = new OBJ_DoorClose(gp); break;
            case OBJ_DoorOpen.objName: obj = new OBJ_DoorOpen(gp); break;
            case OBJ_Gold.objName: obj = new OBJ_Gold(gp); break;
            case OBJ_Gold_Clever.objName: obj = new OBJ_Gold_Clever(gp); break;
            case OBJ_Heart.objName: obj = new OBJ_Heart(gp); break;
            case OBJ_Iron.objName: obj = new OBJ_Iron(gp); break;
            case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
            case OBJ_ManaStar.objName: obj = new OBJ_ManaStar(gp); break;
            case OBJ_Potion_Health_Big.objName: obj = new OBJ_Potion_Health_Big(gp); break;
            case OBJ_Potion_Health_Middle.objName: obj = new OBJ_Potion_Health_Middle(gp); break;
            case OBJ_Potion_Health_Small.objName: obj = new OBJ_Potion_Health_Small(gp); break;
            case OBJ_Potion_Recovery_Big.objName: obj = new OBJ_Potion_Recovery_Big(gp); break;
            case OBJ_Potion_Recovery_Middle.objName: obj = new OBJ_Potion_Recovery_Middle(gp); break;
            case OBJ_Potion_Recovery_Small.objName: obj = new OBJ_Potion_Recovery_Small(gp); break;
            case OBJ_Router.objName: obj = new OBJ_Router(gp); break;
            case OBJ_Shield_Metal.objName: obj = new OBJ_Shield_Metal(gp); break;
            case OBJ_Shield_Wood.objName: obj = new OBJ_Shield_Wood(gp); break;
            case OBJ_Skull.objName: obj = new OBJ_Skull(gp); break;
            case OBJ_Sword_Normal.objName: obj = new OBJ_Sword_Normal(gp); break;
            case OBJ_Tent.objName: obj = new OBJ_Tent(gp); break;
            case OBJ_Torch.objName: obj = new OBJ_Torch(gp); break;
            case OBJ_Watering.objName: obj = new OBJ_Watering(gp); break;
            case OBJ_StairsDown.objName: obj = new OBJ_StairsDown(gp); break;
            case OBJ_StairsUp.objName: obj = new OBJ_StairsUp(gp); break;
        }
        return obj;
    }
}
