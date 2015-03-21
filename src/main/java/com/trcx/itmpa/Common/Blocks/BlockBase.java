package com.trcx.itmpa.Common.Blocks;

import com.trcx.itmpa.Common.TileEntities.BaseTE;
import com.trcx.itmpa.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by Trcx on 3/18/2015.
 */
public class BlockBase extends BlockContainer {
    private final Class<? extends BaseTE> teClass;
    private final String name;

    protected BlockBase(String name, Class<? extends BaseTE> teClass, Material mat) {
        super(mat);
        this.teClass = teClass;
        this.name = name;
        setBlockName(name);
        setHardness(0.5F);
        setHarvestLevel("pickaxe",0);
        GameRegistry.registerBlock(this, name);
        setBlockTextureName(Main.Constants.modid + ":" +name);
        if (teClass != null){
            GameRegistry.registerTileEntity(teClass, name + "TE");
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
}
