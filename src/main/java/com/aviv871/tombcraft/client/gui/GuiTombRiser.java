package com.aviv871.tombcraft.client.gui;

import com.aviv871.tombcraft.inventory.ContainerTombRiser;
import com.aviv871.tombcraft.reference.Reference;
import com.aviv871.tombcraft.tileentity.TileEntityTombRiser;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTombRiser extends GuiContainer
{
    private static final ResourceLocation backgroundimage = new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + "textures/gui/tombRiserGui.png");

    TileEntityTombRiser tombRiser;

    public GuiTombRiser(InventoryPlayer inventoryPlayer, TileEntityTombRiser entity)
    {
        super(new ContainerTombRiser(inventoryPlayer, entity));

        this.tombRiser = entity;

        xSize = 176;
        ySize = 214;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        //Bind Texture
        this.mc.getTextureManager().bindTexture(backgroundimage);
        // draw the texture
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize,  ySize);

        //24 is the GUI arrow slot scale/available progress
        // int k = this.tombRiser.getCookProgressScaled(47);
        //drawTexturedModalRect(guiLeft + 64, guiTop + 56, 176, 0, k + 1, 20);
    }
}
