package com.aviv871.tombcraft.world;

import com.aviv871.tombcraft.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGeneratorTombCraft implements IWorldGenerator
{
    private void GenerateOverworld(Random random, int x, int z, World world)
    {
        this.addOreSpawn(ModBlocks.tombRelic, world, random, x, z, 3, 7, 5, 85, 100);
    }

    private void GenerateNether(Random random, int x, int z, World world)
    {

    }

    private void GenerateEnd(Random random, int x, int z, World world)
    {

    }

    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVainSize, int maxVainSize, int chancesToSpawn, int minY, int maxY )
    {
        WorldGenMinable minable = new WorldGenMinable(block.getDefaultState(), (minVainSize + random.nextInt(maxVainSize - minVainSize)));
        for(int i = 0; i < chancesToSpawn; i++)
        {
            int posX = blockXPos + random.nextInt(16);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(16);
            minable.generate(world, random, new BlockPos(posX, posY, posZ));
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 0: GenerateOverworld(random, chunkX * 16, chunkZ * 16, world); break;
            case 1: GenerateEnd(random, chunkX * 16, chunkZ * 16, world); break;
            case -1: GenerateNether(random, chunkX * 16, chunkZ * 16, world); break;
        }
    }
}
