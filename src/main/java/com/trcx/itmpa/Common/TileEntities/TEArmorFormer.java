package com.trcx.itmpa.Common.TileEntities;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import com.trcx.itmpa.Main;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Trcx on 3/18/2015.
 */
public class TEArmorFormer extends BaseTE implements ISidedInventory, IEnergyHandler{
    private EnergyStorage rfStorage = new EnergyStorage(32000);

    public TEArmorFormer() {
        super(4, Main.Constants.invNameArmorFormer);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        rfStorage.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        rfStorage.writeToNBT(tag);
    }

    @Override
    public void updateEntity() {
        //System.out.println("Ticking!");
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int sideId) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slotId, ItemStack stack, int par3) {
        return isItemValidForSlot(slotId, stack);
    }

    @Override
    public boolean canExtractItem(int slotId, ItemStack stack, int par3) {
        return false;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return rfStorage.receiveEnergy(maxReceive,simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return 0; // do not allow energy to leave this device
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return rfStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return rfStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }
}
