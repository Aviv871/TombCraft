package com.aviv871.tombcraft.client.gui;

import com.aviv871.tombcraft.inventory.ContainerOven;
import com.aviv871.tombcraft.reference.Reference;
import com.aviv871.tombcraft.tileentity.TileEntityOven;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiOven extends GuiContainer
{
    public static final ResourceLocation background = new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + "textures/gui/ovenGui.png");

    public TileEntityOven oven;
    
    public GuiOven(InventoryPlayer inventoryPlayer, TileEntityOven entity)
    {
        super(new ContainerOven(inventoryPlayer, entity));

        this.oven = entity;

        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        String name = this.oven.hasCustomInventoryName() ? this.oven.getInventoryName() : I18n.format(this.oven.getInventoryName(), new Object[0]);

        //this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2 + 20, 6, 4210752);
        this.fontRendererObj.drawString(name, 7, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 119, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        //Bind Texture
        this.mc.getTextureManager().bindTexture(background);

        // draw the texture
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize,  ySize);

        if(this.oven.isBurning())
        {
            int k = this.oven.getBurnTimeRemainingScale(18);
            int j = 18 - k;
            drawTexturedModalRect(guiLeft + 59, (guiTop + 6) + j, 176, 28, 10, 18 - j);
            drawTexturedModalRect(guiLeft + 60, guiTop + 24, 176, 47, 8, 11);
        }

        //24 is the GUI arrow slot scale/available progress
        int k =this.oven.getCookProgressScaled(24);
        drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 10, k + 1, 16);

    }
}
