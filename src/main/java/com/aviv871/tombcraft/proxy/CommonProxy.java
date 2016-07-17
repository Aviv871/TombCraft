package com.aviv871.tombcraft.proxy;

import com.aviv871.tombcraft.event.TCEvents;
import com.aviv871.tombcraft.handler.ConfigurationHandler;
import com.aviv871.tombcraft.init.ModBlocks;
import com.aviv871.tombcraft.init.ModItems;
import com.aviv871.tombcraft.init.ModRecipes;
import com.aviv871.tombcraft.tileentity.TileEntityGhostSeat;
import com.aviv871.tombcraft.tileentity.TileEntityRelicLab;
import com.aviv871.tombcraft.tileentity.TileEntityTombRiser;
import com.aviv871.tombcraft.world.WorldGeneratorTombCraft;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityTombRiser.class, "TileEntityTombRiser");
        GameRegistry.registerTileEntity(TileEntityRelicLab.class, "Relic_Lab");
        GameRegistry.registerTileEntity(TileEntityGhostSeat.class, "Ghost_Seat");
    }

    @Override
    public void registerBlocks() {
        ModBlocks.register();
    }

    @Override
    public void registerItems() {
        ModItems.register();
    }

    @Override
    public void registerEvents() {
        TCEvents.register();
    }

    @Override
    public void registerRecipes() {
        ModRecipes.register();
    }

    @Override
    public void registerWorldGenerator() {
        GameRegistry.registerWorldGenerator(new WorldGeneratorTombCraft(), 1);
    }

    @Override
    public void registerConfiguration(File file) {
        ConfigurationHandler.register(file);
    }
}
