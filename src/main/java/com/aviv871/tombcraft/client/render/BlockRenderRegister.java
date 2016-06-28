package com.aviv871.tombcraft.client.render;

import com.aviv871.tombcraft.init.ModBlocks;
import com.aviv871.tombcraft.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRenderRegister
{
    public static void registerItemBlockRenderer()
    {
        registerRender(ModBlocks.axeCore);
        registerRender(ModBlocks.hoeCore);
        registerRender(ModBlocks.ovenActive);
        registerRender(ModBlocks.ovenIdle);
        registerRender(ModBlocks.pickaxeCore);
        registerRender(ModBlocks.shovelCore);
        registerRender(ModBlocks.swordCore);
        registerRender(ModBlocks.tombRelic);
        registerRender(ModBlocks.tombRiser);
    }

    private static void registerRender(Block block)
    {
        //ModelLoader.setCustomModelResourceLocation(itemblock, 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
