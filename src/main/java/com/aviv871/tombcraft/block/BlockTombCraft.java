package com.aviv871.tombcraft.block;

import com.aviv871.tombcraft.TombCraft;
import com.aviv871.tombcraft.reference.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockTombCraft extends Block
{
    public BlockTombCraft(Blocks block, Material material, float hardness, float resistance)
    {
        super(material);
        this.setCreativeTab(TombCraft.tabTombCraft);
        this.setRegistryName(block.getRegistryName());
        this.setUnlocalizedName(block.getUnlocalizedName());
        this.setHardness(hardness);
        this.setResistance(resistance);
    }
}
