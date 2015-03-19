package com.trcx.itmpa.Client.Gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

/**
 * Created by Trcx on 3/19/2015.
 */
public class GuiBase extends GuiContainer {


    public GuiBase(Container container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

    }
}
