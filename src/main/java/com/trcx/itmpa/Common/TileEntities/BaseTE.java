package com.trcx.itmpa.Common.TileEntities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Trcx on 3/18/2015.
 */
public class BaseTE extends TileEntity implements IInventory {
    private ItemStack[] inventoryContents;
    private String invName;

    BaseTE(int invSize, String invName){
        inventoryContents = new ItemStack[invSize];
        this.invName = invName;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord,yCoord,zCoord,1,tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        int slotsCount = 0;
        if (tag.hasKey("size")) {
            slotsCount = tag.getInteger("size");
        }
        NBTTagList nbttaglist = tag.getTagList("Items", 10);
        inventoryContents = new ItemStack[slotsCount];
        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound stacktag = nbttaglist.getCompoundTagAt(i);
            int j = stacktag.getByte("Slot");
            if (j >= 0 && j < inventoryContents.length) {
                inventoryContents[j] = ItemStack.loadItemStackFromNBT(stacktag);
            }
        }

    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("size", getSizeInventory());
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < inventoryContents.length; i++) {
            if (inventoryContents[i] != null) {
                NBTTagCompound stacktag = new NBTTagCompound();
                stacktag.setByte("Slot", (byte)i);
                inventoryContents[i].writeToNBT(stacktag);
                nbttaglist.appendTag(stacktag);
            }
        }
        tag.setTag("Items", nbttaglist);

    }

    //region inventory stuff

    @Override
    public int getSizeInventory() {
        return inventoryContents.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotID) {
        return inventoryContents[slotID];
    }

    @Override
    public ItemStack decrStackSize(int slotId, int qty) {
        if (this.inventoryContents[slotId] != null)
        {
            ItemStack itemstack;

            if (this.inventoryContents[slotId].stackSize <= qty)
            {
                itemstack = this.inventoryContents[slotId];
                this.inventoryContents[slotId] = null;
                this.worldObj.markBlockForUpdate(xCoord,yCoord,zCoord);
                return itemstack;
            }
            itemstack = this.inventoryContents[slotId].splitStack(qty);
            if (this.inventoryContents[slotId].stackSize == 0)
            {
                this.inventoryContents[slotId] = null;
                this.worldObj.markBlockForUpdate(xCoord,yCoord,zCoord);
            }

            return itemstack;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotId) {
        return getStackInSlot(slotId);
    }

    @Override
    public void setInventorySlotContents(int slotId, ItemStack stack) {
        inventoryContents[slotId] = stack;
        this.worldObj.markBlockForUpdate(xCoord,yCoord,zCoord);
    }

    @Override
    public String getInventoryName() {
        return invName;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slotId, ItemStack stack) {
        return true;
    }
    //end region
}
