package com.aviv871.tombcraft.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes
{
    public static void register()
    {
        //GameRegistry.addRecipe(new ItemStack(ModItems.soulSword), " a ", "bbb", " c ", 'c', new ItemStack(Items.stick), 'b', new ItemStack(Blocks.obsidian), 'a', new ItemStack(ModItems.pureSoul));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.theTouchofDeath), new ItemStack(Items.BONE), new ItemStack(Items.BOOK));
        //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.soulSword), " a ", "bbb", " c ", 'c', "stickWood", 'b', new ItemStack(Blocks.obsidian), 'a', new ItemStack(ModItems.pureSoul)));
        //GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.theTouchofDeath), new ItemStack(ModBlocks.tombRelic), new ItemStack(Items.book)));
    }
}
