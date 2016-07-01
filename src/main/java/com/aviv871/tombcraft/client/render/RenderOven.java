package com.aviv871.tombcraft.client.render;

import com.aviv871.tombcraft.tileentity.TileEntityOven;
import com.aviv871.tombcraft.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RenderOven extends TileEntitySpecialRenderer<TileEntityOven>
{
    private static Minecraft minecraft = Minecraft.getMinecraft();
    private static final double period = 24 * Math.PI;

    @Override
    public void renderTileEntityAt(TileEntityOven te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        ItemStack itemStack = ItemStack.copyItemStack(te.getStackInSlot(0));

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        this.renderFloatingItem(te.getWorld(), itemStack);
        this.renderItemOnBlock(te.getWorld(), itemStack);
        GlStateManager.popMatrix();
    }

    private void renderFloatingItem(World world, ItemStack itemStack)
    {
        RenderItem itemRenderer = minecraft.getRenderItem();
        if (itemStack != null)
        {
            double local = (period * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
            float hoverHeight = (float) ((0.4 * (Math.sin(local))) / 8);

            GlStateManager.translate(0.5F, 2F + hoverHeight, 0.5F);
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

            GlStateManager.translate(0.0F, -hoverHeight, 0.0F);
        }
    }

    private void renderItemOnBlock(World world, ItemStack itemStack)
    {
        RenderItem itemRenderer = minecraft.getRenderItem();
        if (itemStack != null && !(itemStack.getItem() instanceof ItemBlock))
        {
            GlStateManager.translate(0.0F, -0.98F, 0.0F);
            EntityItem entityitem = new EntityItem(world, 0.0D, 0.0D, 0.0D, itemStack);
            entityitem.getEntityItem().stackSize = 1;
            entityitem.hoverStart = 0.0F;

            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            GlStateManager.rotate(180, 0.0F, 1.0F, 1.0F);
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
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
