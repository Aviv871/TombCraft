package com.aviv871.tombcraft;

import net.minecraft.potion.Potion;

public class PotionTC extends Potion
{

    protected PotionTC(boolean par1, int par2) {
        super(par1, par2);
    }

    public Potion setIconIndex(int par1, int par2)
    {
        super.setIconIndex(par1, par2);
        return this;
    }
}
