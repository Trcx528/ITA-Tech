package com.trcx.itmpa.Common.Blocks;


import com.trcx.itmpa.Common.TileEntities.BaseTE;
import net.minecraft.block.material.Material;

/**
 * Created by Trcx on 3/18/2015.
 */
public class BlockArmorFormer extends BlockBase {

    public BlockArmorFormer(String name, Class<? extends BaseTE> teClass) {
        super(name, teClass, Material.rock);
    }
}
