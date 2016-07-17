package com.aviv871.tombcraft.client.render;

import com.aviv871.tombcraft.tileentity.TileEntityGhostSeat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GhostSeatTESR extends TileEntitySpecialRenderer<TileEntityGhostSeat>
{
    private static Minecraft minecraft = Minecraft.getMinecraft();
    private static final double period = 24 * Math.PI;

    @Override
    public void renderTileEntityAt(TileEntityGhostSeat te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        ItemStack itemStack = ItemStack.copyItemStack(te.getStackInSlot(0));

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        this.renderFloatingItem(te.getWorld(), itemStack);
        GlStateManager.popMatrix();
    }

    private void renderFloatingItem(World world, ItemStack itemStack)
    {
        RenderItem itemRenderer = minecraft.getRenderItem();
        if (itemStack != null)
        {
            double local = (period * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
            float hoverHeight = (float) ((0.4 * (Math.sin(local))) / 8);

            GlStateManager.translate(0.5F, 0.5F + hoverHeight, 0.5F);
            EntityItem entityitem = new EntityItem(world, 0.0D, 0.0D, 0.0D, itemStack);
            entityitem.getEntityItem().stackSize = 1;
            entityitem.hoverStart = 0.0F;
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            float rotation = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

            GlStateManager.rotate(rotation, 0.0F, 1.0F, 0);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            itemRenderer.renderItem(entityitem.getEntityItem(), ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}
