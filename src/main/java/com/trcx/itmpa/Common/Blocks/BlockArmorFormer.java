package com.trcx.itmpa.Common.Blocks;


import com.trcx.itmpa.Common.TileEntities.BaseTE;
import com.trcx.itmpa.Common.TileEntities.TEArmorFormer;
import com.trcx.itmpa.Main;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
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
        //System.out.println("x: " + x + " y: " + y + " z: " + z + " sideId: " + sideId + " cX: "+ clickX + " cY: " + clickY + " cZ: " + clickZ);
        if (!world.isRemote){
            player.openGui(Main.INSTANCE,0, world,x,y,z);
        }
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        frontIcon = iconRegister.registerIcon("itmpa:ArmorFormerFront");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, player, stack);
        int side = MathHelper.floor_double((double) ((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        TileEntity te = world.getTileEntity(x,y,z);
        if (side == 0)
            side = 2;
        else if (side == 1)
            side = 5;
        else if (side == 2)
            side = 3;
        else if (side == 3)
            side = 4;
        if (te instanceof BaseTE){
            ((BaseTE) te).frontSide = side;
        }
    }
}
