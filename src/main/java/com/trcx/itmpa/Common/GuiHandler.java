package com.trcx.itmpa.Common;

import com.trcx.itmpa.Client.Gui.GuiArmorFormer;
import com.trcx.itmpa.Common.Containers.ContainerArmorFormer;
import com.trcx.itmpa.Common.Containers.ContainerBase;
import com.trcx.itmpa.Common.TileEntities.BaseTE;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Trcx on 3/18/2015.
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id){
            case 0:
                if (world.blockExists(x,y,z)){
                    return new ContainerArmorFormer(player, (BaseTE)world.getTileEntity(x,y,z));
                }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        ContainerBase container = (ContainerBase)getServerGuiElement(id, player, world, x, y, z);
        if (container != null) {
            switch (id) {
                case 0:
                    return new GuiArmorFormer(container);
            }
        }
        return null;
    }
}
