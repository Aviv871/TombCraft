package com.aviv871.tombcraft.block;


import com.aviv871.tombcraft.reference.Blocks;
import net.minecraft.block.material.Material;

public class Shovel_Core extends BlockTombCraft
{
    public Shovel_Core(Blocks block, Material material, float hardness, float resistance)
    {
        super(block, material, hardness, resistance);
        this.setLightLevel(0.2F);
        this.setHarvestLevel("pickaxe", 0);
    }
}
