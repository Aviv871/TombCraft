package com.aviv871.tombcraft.item;

import com.aviv871.tombcraft.TombCraft;
import com.aviv871.tombcraft.reference.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class The_Touch_of_Death extends ItemTombCraft
{
    public The_Touch_of_Death(Items item)
    {
        super(item);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer, EnumHand hand)
    {
        if(!world.isRemote)
        {
            TombCraft.proxy.openGuiScreen();
        }
        return super.onItemRightClick(itemStack, world, entityPlayer, hand);
    }
}
