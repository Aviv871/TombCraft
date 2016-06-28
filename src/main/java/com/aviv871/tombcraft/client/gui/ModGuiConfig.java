package com.aviv871.tombcraft.client.gui;

import com.aviv871.tombcraft.handler.ConfigurationHandler;
import com.aviv871.tombcraft.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ModGuiConfig extends GuiConfig
{
    public ModGuiConfig(GuiScreen guiScreen) {
        super(guiScreen, getConfigCategories(), Reference.MOD_ID, false, false, ConfigurationHandler.title);
    }

    private static List<IConfigElement> getConfigCategories() {

        final List<IConfigElement> categories = new ArrayList<IConfigElement>();

        for (String category : ConfigurationHandler.configuration.getCategoryNames()) {
            ConfigCategory cc = ConfigurationHandler.configuration.getCategory(category);
            if (cc.isChild()) continue;
            ConfigElement ce = new ConfigElement(cc);
            categories.add(ce);
        }
        return categories;
    }
}
