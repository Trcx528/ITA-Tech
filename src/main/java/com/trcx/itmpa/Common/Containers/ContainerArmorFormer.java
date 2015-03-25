package com.trcx.itmpa.Common.Containers;

import com.trcx.itmpa.Common.TileEntities.BaseTE;
import com.trcx.itmpa.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * Created by Trcx on 3/19/2015.
 */
public class ContainerArmorFormer extends ContainerBase {
    private EntityPlayer player;
    private BaseTE tileEntity;

    private Slot inputOne;
    private Slot inputTwo;
    private Slot inputArmor;
    private Slot output;

    @Override
    protected void bindTileInventory(BaseTE te) {
        inputOne = new Slot(te,0,43, 6){
            @Override
            public boolean isItemValid(ItemStack stack) {
                if (stack == null)
                    return false;
                if (stack.getItem() == Items.redstone)
                    return true;
                return false;
            }
        };
        inputTwo = new Slot(te,1,43, 55){
            @Override
            public boolean isItemValid(ItemStack stack){
                if (stack == null)
                    return false;
                if (stack.getItem() == Items.ender_pearl)
                    return true;
                return false;
            }
        };
        inputArmor = new Slot(te,2,75, 30){
            @Override
            public boolean isItemValid(ItemStack stack){
                if (stack == null)
                    return false;
                if (stack.getItem() instanceof ItemArmor){
                    ItemArmor armor = (ItemArmor)stack.getItem();
                    int requiredArmorValue = 100;
                    switch (armor.armorType){
                        case Main.Constants.typeHELMET:
                            requiredArmorValue = 3;
                            break;
                        case Main.Constants.typeCHESTPLATE:
                            requiredArmorValue = 8;
                            break;
                        case Main.Constants.typeLEGGINGS:
                            requiredArmorValue = 6;
                            break;
                        case Main.Constants.typeBOOTS:
                            requiredArmorValue = 3;
                            break;
                    }
                    if (armor.getArmorMaterial().getDamageReductionAmount(armor.armorType) >= requiredArmorValue)
                        return true;
                }
                return false;
            }
        };
        output = new Slot(te,3,132, 30) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }
        };

        addSlotToContainer(inputOne);
        addSlotToContainer(inputTwo);
        addSlotToContainer(inputArmor);
        addSlotToContainer(output);
    }

    public ContainerArmorFormer(EntityPlayer player, BaseTE te) {
        super(player.inventory, te);
        this.player = player;
        this.tileEntity = te;
    }
}
