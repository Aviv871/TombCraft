package com.aviv871.tombcraft.utility;

import com.aviv871.tombcraft.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TCCreateToolHelper
{
    public static boolean isSorted(World world, int x, int y, int z)
    {
        Block blocks1[] = {
                world.getBlockState(new BlockPos(x+1, y, z)).getBlock(),
                world.getBlockState(new BlockPos(x+1, y, z+1)).getBlock(),
                world.getBlockState(new BlockPos(x+1, y, z-1)).getBlock(),
                world.getBlockState(new BlockPos(x, y, z+1)).getBlock(),
                world.getBlockState(new BlockPos(x, y, z-1)).getBlock(),
                world.getBlockState(new BlockPos(x-1, y, z)).getBlock(),
                world.getBlockState(new BlockPos(x-1, y, z+1)).getBlock(),
                world.getBlockState(new BlockPos(x-1, y, z-1)).getBlock(),
                world.getBlockState(new BlockPos(x+2, y, z)).getBlock(),
                world.getBlockState(new BlockPos(x-2, y, z)).getBlock(),
                world.getBlockState(new BlockPos(x, y, z+2)).getBlock(),
                world.getBlockState(new BlockPos(x, y, z-2)).getBlock(),  };
        Block blocks2[] = {
                world.getBlockState(new BlockPos(x+2, y+1, z)).getBlock(),
                world.getBlockState(new BlockPos(x-2, y+1, z)).getBlock(),
                world.getBlockState(new BlockPos(x, y+1, z+2)).getBlock(),
                world.getBlockState(new BlockPos(x, y+1, z-2)).getBlock(),  };
        return Check(blocks1, blocks2);
    }

    public static boolean Check(Block blocks1[], Block blocks2[])
    {
        for (int i = 0; i < blocks1.length; i++)
        {
            if (blocks1[i] != ModBlocks.tombRelic) return false;
        }

        for (int i = 0; i < blocks2.length; i++)
        {
            if (blocks2[i] != Blocks.TORCH) return false;
        }

        return true;
    }
}
