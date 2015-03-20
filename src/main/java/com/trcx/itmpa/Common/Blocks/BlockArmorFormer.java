package com.trcx.itmpa.Common.Blocks;


import com.trcx.itmpa.Common.TileEntities.BaseTE;
import com.trcx.itmpa.Main;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Trcx on 3/18/2015.
 */
public class BlockArmorFormer extends BlockBase {

    public BlockArmorFormer(String name, Class<? extends BaseTE> teClass) {
        super(name, teClass, Material.rock);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int sideId, float clickX, float clickY, float clickZ) {
        System.out.println("x: " + x + " y: " + y + " z: " + z + " sideId: " + sideId + " cX: "+ clickX + " cY: " + clickY + " cZ: " + clickZ);
        if (!world.isRemote){
            player.openGui(Main.INSTANCE,0, world,x,y,z);
        }
        return false;
    }
}
