package com.trcx.itmpa.Client.Gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Trcx on 3/19/2015.
 */
public class GuiArmorFormer extends GuiContainer {
    public static final ResourceLocation guiBackground = new ResourceLocation("itmpa:textures/gui/armorFabGui.png");

    public GuiArmorFormer(Container container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(guiBackground);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
