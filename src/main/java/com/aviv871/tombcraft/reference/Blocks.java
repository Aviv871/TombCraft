package com.aviv871.tombcraft.reference;

import net.minecraft.util.ResourceLocation;

public enum Blocks
{
    OVEN_IDLE("oven"),
    OVEN_ACTIVE("ovenActive"),
    AXE_CORE("axeCore"),
    HOE_CORE("hoeCore"),
    PICKAXE_CORE("pickaxeCore"),
    SHOVEL_CORE("shovelCore"),
    SWORD_CORE("swordCore"),
    TOMB_RELIC("tombRelic"),
    TOMB_RISER("tombRiser");

    private String unlocalizedName;
    private ResourceLocation registryName;

    Blocks(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        this.registryName = new ResourceLocation(Reference.MOD_ID, unlocalizedName);
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
