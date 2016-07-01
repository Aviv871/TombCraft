package com.aviv871.tombcraft.proxy;

import com.aviv871.tombcraft.TombCraft;
import com.aviv871.tombcraft.client.render.BlockRenderRegister;
import com.aviv871.tombcraft.client.render.ItemRenderRegister;
import com.aviv871.tombcraft.client.render.RenderOven;
import com.aviv871.tombcraft.client.settings.KeyBindings;
import com.aviv871.tombcraft.handler.GuiHandler;
import com.aviv871.tombcraft.tileentity.TileEntityOven;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void initRenderingAndTextures()
    {
        ItemRenderRegister.registerItemRenderer();
        BlockRenderRegister.registerItemBlockRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOven.class, new RenderOven());
    }

    @Override
    public void registerKeyBinding()
    {
        ClientRegistry.registerKeyBinding(KeyBindings.charge);
        ClientRegistry.registerKeyBinding(KeyBindings.releace);
    }

    @Override
    public void registerGuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(TombCraft.instance, new GuiHandler());
    }

}
