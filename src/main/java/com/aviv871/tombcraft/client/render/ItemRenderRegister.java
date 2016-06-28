package com.aviv871.tombcraft.client.render;

import com.aviv871.tombcraft.init.ModItems;
import com.aviv871.tombcraft.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ItemRenderRegister
{
    public static void registerItemRenderer()
    {
        reg(ModItems.pureSoul);
        reg(ModItems.scrollofForgottenKnowledge);
        reg(ModItems.soul);
        reg(ModItems.soulAxe);
        reg(ModItems.soulHoe);
        reg(ModItems.soulPickaxe);
        reg(ModItems.soulShovel);
        reg(ModItems.soulSword);
        reg(ModItems.theTouchofDeath);
    }

    private static void reg(Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
