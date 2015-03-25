package com.trcx.itmpa.Common.Blocks;

import com.trcx.itmpa.Common.TileEntities.BaseTE;
import com.trcx.itmpa.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Trcx on 3/18/2015.
 */
public class BlockBase extends BlockContainer {
    private final Class<? extends BaseTE> teClass;
    private final String name;
    private static IIcon defaultMachineIcon;
    public static IIcon frontIcon;

    @Override
    public IIcon getIcon(int sideId, int meta) {
        if (sideId == 2)
            return frontIcon;
        return defaultMachineIcon;
    }

    @Override
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int sideId) {
        TileEntity te = access.getTileEntity(x,y,z);
        te.markDirty();
        if (te instanceof BaseTE)
            if (((BaseTE) te).frontSide == sideId)
                return frontIcon;
        return defaultMachineIcon;
    }

    protected BlockBase(String name, Class<? extends BaseTE> teClass, Material mat) {
        super(mat);
        this.teClass = teClass;
        this.name = name;
        setBlockName(name);
        setHardness(0.5F);
        setHarvestLevel("pickaxe",0);
        GameRegistry.registerBlock(this, name);
        setBlockTextureName(Main.Constants.modid + ":" + this.name);
        if (teClass != null){
            GameRegistry.registerTileEntity(teClass, this.name + "TE");
        }
    }


    @Override
    public boolean hasTileEntity(int metadata) {
        return teClass != null;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        super.breakBlock(world, x, y, z, block, meta);
        if (teClass != null){
            world.removeTileEntity(x,y,z);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        if (teClass != null) {
            try {
                return teClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        defaultMachineIcon = iconRegister.registerIcon("itmpa:Machine");
        frontIcon = defaultMachineIcon;
    }
}
