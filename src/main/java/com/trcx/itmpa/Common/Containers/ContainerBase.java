package com.trcx.itmpa.Common.Containers;

import com.trcx.itmpa.Common.TileEntities.BaseTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Trcx on 3/19/2015.
 */
public class ContainerBase extends Container {

    public ContainerBase(InventoryPlayer invPlayer, BaseTE te){
        bindTileInventory(te);
        bindPlayerInventory(invPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
        return null;
    }

    private void bindTileInventory(BaseTE te){
        int y = 50;
        int x = 18;
        for (int i = 0; i < te.getSizeInventory(); i++){
            addSlotToContainer(new Slot(te, i, x + (i * 18), y));
        }
    }

    private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
