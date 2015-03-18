package com.trcx.ita_tech;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

/**
 * Created by Trcx on 3/18/2015.
 */

@Mod(modid = Main.Constants.modid, version = Main.Constants.version, name = Main.Constants.modFriendlyName)
public class Main {

    @Mod.Instance(Main.Constants.modid)
    public static Main INSTANCE;

    @SidedProxy(clientSide = "com.trcx.ita_tech.Client.Proxy", serverSide = "com.trcx.ita_tech.Common.Proxy")
    public static com.trcx.ita_tech.Common.Proxy proxy;


    public static class Items {

    }

    public static class Blocks {

    }

    public static class Config {

    }

    public static class Constants {
        public static final String modid = "ITA-Tech";
        public static final String version = "0.0.1";
        public static final String modFriendlyName = "Infinitely Tweakable Armor - Tech Edition";
    }




    public Main (){}

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //register stuff
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {}

    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event){}

}
