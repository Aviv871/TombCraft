package com.aviv871.tombcraft.item;

import com.aviv871.tombcraft.TombCraft;
import com.aviv871.tombcraft.reference.Items;
import net.minecraft.item.Item;

public class ItemTombCraft extends Item
{
    public ItemTombCraft(Items item)
    {
        super();
        this.setCreativeTab(TombCraft.tabTombCraft);
        this.setRegistryName(item.getRegistryName());
        this.setUnlocalizedName(item.getUnlocalizedName());
    }
}
