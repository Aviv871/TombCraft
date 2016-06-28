package com.aviv871.tombcraft.item;

import com.aviv871.tombcraft.TombCraft;
import com.aviv871.tombcraft.reference.Items;
import com.aviv871.tombcraft.utility.SoulsData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class Soul_Sword extends ItemSword
{

    public Soul_Sword(Items item)
    {
        //super(ModItems.SoulMaterial);
        super(ToolMaterial.GOLD);
        this.setUnlocalizedName(item.getUnlocalizedName());
        this.setRegistryName(item.getRegistryName());
        this.maxStackSize = 1;
        this.setCreativeTab(TombCraft.tabTombCraft);
    }

    public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker)
    {
        //target.addPotionEffect(new PotionEffect(TombCraft.potionTC, 200, 1)); //ERROR
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer, EnumHand hand)
    {
        if (entityPlayer.isSneaking() && (!world.isRemote))
        {
            entityPlayer.addChatMessage(new TextComponentString(String.format("Current Souls: " + SoulsData.getSouls(entityPlayer))));
        }
        return super.onItemRightClick(itemStack, world, entityPlayer, hand);
    }
}
