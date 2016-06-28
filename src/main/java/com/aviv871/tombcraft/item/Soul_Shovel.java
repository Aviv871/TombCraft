package com.aviv871.tombcraft.item;

import com.aviv871.tombcraft.TombCraft;
import com.aviv871.tombcraft.reference.Items;
import net.minecraft.item.ItemSpade;

public class Soul_Shovel extends ItemSpade
{
    public Soul_Shovel(Items item)
    {
        //super(ModItems.SoulMaterial);
        super(ToolMaterial.GOLD);
        this.setUnlocalizedName(item.getUnlocalizedName());
        this.setRegistryName(item.getRegistryName());
        this.maxStackSize = 1;
        this.setCreativeTab(TombCraft.tabTombCraft);
    }
}
