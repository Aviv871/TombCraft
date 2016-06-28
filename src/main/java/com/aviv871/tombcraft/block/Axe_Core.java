package com.aviv871.tombcraft.block;

import com.aviv871.tombcraft.reference.Blocks;
import net.minecraft.block.material.Material;

public class Axe_Core extends BlockTombCraft
{
    public Axe_Core(Blocks block, Material material, float hardness, float resistance)
    {
        super(block, material, hardness, resistance);
        this.setLightLevel(0.2F);
        this.setHarvestLevel("pickaxe", 0);
    }
}
