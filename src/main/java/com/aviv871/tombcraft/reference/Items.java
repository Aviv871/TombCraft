package com.aviv871.tombcraft.reference;

import net.minecraft.util.ResourceLocation;

public enum Items
{
    SOUL("soul"),
    PURE_SOUL("pureSoul"),
    SCROLL_OF_FORGOTTEN_KNOWLEDGE("scrollofForgottenKnowledge"),
    SOUL_AXE("soulAxe"),
    SOUL_HOE("soulHoe"),
    SOUL_PICKAXE("soulPickaxe"),
    SOUL_SHOVEL("soulShovel"),
    SOUL_SWORD("soulSword"),
    THE_TOUCH_OF_DEATH("theTouchofDeath");

    private String unlocalizedName;
    private ResourceLocation registryName;

    Items(String theUnlocalizedName)
    {
        this.unlocalizedName = theUnlocalizedName;
        this.registryName = new ResourceLocation(Reference.MOD_ID, theUnlocalizedName);
    }

    public ResourceLocation getRegistryName()
    {
        return this.registryName;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }
}
