package com.aviv871.tombcraft.init;

import com.aviv871.tombcraft.item.*;
import com.aviv871.tombcraft.reference.Items;
import com.aviv871.tombcraft.reference.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static Item.ToolMaterial SoulMaterial = EnumHelper.addToolMaterial("SoulMaterial", 3, 100, 20.0F, 8.0F, 20); //ERROR

    public static Item soulSword = new Soul_Sword(Items.SOUL_SWORD);
    public static Item soulPickaxe = new Soul_Pickaxe(Items.SOUL_PICKAXE);
    public static Item soulShovel = new Soul_Shovel(Items.SOUL_SHOVEL);
    public static Item soulHoe = new Soul_Hoe(Items.SOUL_HOE);
    public static Item soulAxe = new Soul_Axe(Items.SOUL_AXE);

    public static ItemTombCraft pureSoul = new Pure_Soul(Items.PURE_SOUL);
    public static ItemTombCraft scrollofForgottenKnowledge = new Scroll_of_Forgotten_Knowledge(Items.SCROLL_OF_FORGOTTEN_KNOWLEDGE);
    public static ItemTombCraft soul = new Soul(Items.SOUL);
    public static ItemTombCraft theTouchofDeath = new The_Touch_of_Death(Items.THE_TOUCH_OF_DEATH);

    public static void register()
    {
        GameRegistry.register(soulSword);
        GameRegistry.register(soulPickaxe);
        GameRegistry.register(soulShovel);
        GameRegistry.register(soulHoe);
        GameRegistry.register(soulAxe);

        GameRegistry.register(pureSoul);
        GameRegistry.register(scrollofForgottenKnowledge);
        GameRegistry.register(soul);
        GameRegistry.register(theTouchofDeath);
    }
}
