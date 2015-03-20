package com.trcx.itmpa;

import com.trcx.itmpa.Common.Blocks.BlockArmorFormer;
import com.trcx.itmpa.Common.CommonProxy;
import com.trcx.itmpa.Common.GuiHandler;
import com.trcx.itmpa.Common.TileEntities.TEArmorFormer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;

/**
 * Created by Trcx on 3/18/2015.
 */

@Mod(modid = Main.Constants.modid, version = Main.Constants.version, name = Main.Constants.modFriendlyName)
public class Main {

    @Mod.Instance(Main.Constants.modid)
    public static Main INSTANCE;

    @SidedProxy(clientSide = "com.trcx.itmpa.Client.ClientProxy", serverSide = "com.trcx.itmpa.Common.CommonProxy")
    public static CommonProxy proxy;


    public static class Items {

    }

    public static class Blocks {
        public static Block ArmorFormer;
    }

    public static class Config {

    }

    public static class Constants {
        public static final String modid = "itmpa";
        public static final String version = "0.0.1";
        public static final String modFriendlyName = "Infinitely Tweakable Modular Power Armor";

        public static final String nameArmorFormer = "ArmorFormer";

        public static final String invNameArmorFormer = "Armor Former";
    }


    public Main (){}

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Blocks.ArmorFormer = new BlockArmorFormer(Constants.nameArmorFormer, TEArmorFormer.class);

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {}

    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event){}

}
