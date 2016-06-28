package com.aviv871.tombcraft.block;

import com.aviv871.tombcraft.reference.Blocks;
import net.minecraft.block.material.Material;

public class Tomb_Relic extends BlockTombCraft
{
    public Tomb_Relic(Blocks block, Material material, float hardness, float resistance)
    {
        super(block, material, hardness, resistance);
        this.setLightLevel(0.2F);
    }
}
