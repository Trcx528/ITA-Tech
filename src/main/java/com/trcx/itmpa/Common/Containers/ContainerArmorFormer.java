package com.trcx.itmpa.Common.Containers;

import com.trcx.itmpa.Common.TileEntities.BaseTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by Trcx on 3/19/2015.
 */
public class ContainerArmorFormer extends ContainerBase {
    private EntityPlayer player;
    private BaseTE tileEntity;

    public ContainerArmorFormer(EntityPlayer player, BaseTE te) {
        super(player.inventory, te);
        this.player = player;
        this.tileEntity = te;
    }
}
