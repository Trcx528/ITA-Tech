package com.trcx.itmpa.Common.Containers;

import com.trcx.itmpa.Common.TileEntities.BaseTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Trcx on 3/19/2015.
 */
public class ContainerBase extends Container {
    protected int teSize;

    public ContainerBase(InventoryPlayer invPlayer, BaseTE te){
        teSize = te.getSizeInventory();
        bindTileInventory(te);
        bindPlayerInventory(invPlayer);
    }

    /*
     * @param player the player that clicked
     * @param slotId the id of the slot that the player clicked
     * @return a copy of the ItemStack that was transferred, or null if nothing was transferred
     */
    //TODO implement shift right clicking
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
        ItemStack clickStack = getSlot(slotId).getStack();
        if (clickStack == null)
            return null;
        ItemStack copyStack = clickStack.copy();
        if (slotId < teSize) { //clicked in machine
            if (mergeItemStack(clickStack,teSize, getInventory().size() - teSize, true))
                return copyStack;
            return null;
        } else { // clicked in player inventory
            if (mergeItemStack(clickStack,0, teSize, false))
                return copyStack;
            return null;
        }
    }

    /*
     * @param stack the stack to find a new slot for
     * @param start the slot index to start searching at
     * @param finish the slot index to end at
     * @param search from high index to low index if true, otherwise count up
     * @return return true if the stack was able to be completely or partially merged, else return false
     *
     * be sure to check slot.isItemValid()
     */
    @Override
    protected boolean mergeItemStack(ItemStack stack, int start, int finish, boolean reverse) {
        return super.mergeItemStack(stack,start,finish,reverse);
        /*boolean result = false;
        int i = reverse ? finish : start;
        while (stack != null && stack.stackSize > 0 && reverse ? i >= start : i <= finish) {
            if (inventorySlots.size() < i) {
                Slot trySlot = getSlot(i);
                ItemStack tryStack = trySlot.getStack();
                if (tryStack == null && trySlot.isItemValid(stack)) {
                    trySlot.putStack(stack.copy());
                    stack = null;
                    trySlot.onSlotChanged();
                    result = true;
                } else if (tryStack != null && tryStack.isStackable() && trySlot.isItemValid(stack)) {
                    if (ItemStack.areItemStacksEqual(tryStack, stack) && ItemStack.areItemStackTagsEqual(tryStack, stack)) {
                        result = true;
                        int delta = Math.min(stack.stackSize, tryStack.getMaxStackSize() - tryStack.stackSize);
                        stack.stackSize -= delta;
                        tryStack.stackSize += delta;
                        if (stack.stackSize <= 0)
                            stack = null;
                        trySlot.onSlotChanged();
                    }
                }
                i = reverse ? i - 1 : i + 1;
            }
        }
        re
        return result;*/
    }

    protected void bindTileInventory(BaseTE te){
        int y = 50;
        int x = 18;
        for (int i = 0; i < te.getSizeInventory(); i++){
            addSlotToContainer(new Slot(te, i, x + (i * 18), y));
        }
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int xOffset = 8;
        int yOffset = 89;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        xOffset + j * 18, yOffset + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, xOffset + i * 18, yOffset + 58 ));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
