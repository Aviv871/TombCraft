package com.aviv871.tombcraft.proxy;

import java.io.File;

public interface IProxy
{
    public abstract void registerTileEntities();

    public abstract void initRenderingAndTextures();

    public abstract void registerBlocks();

    public abstract void registerItems();

    public abstract void registerKeyBinding();

    public abstract void registerEvents();

    public abstract void registerGuiHandler();

    public abstract void registerRecipes();

    public abstract void registerWorldGenerator();

    public abstract void registerConfiguration(File file);

    public abstract void openGuiScreen();
}
