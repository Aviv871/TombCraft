package com.aviv871.tombcraft.item;

import com.aviv871.tombcraft.TombCraft;
import com.aviv871.tombcraft.reference.Items;
import com.aviv871.tombcraft.utility.SoulsData;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.Random;

public class Soul_Pickaxe extends ItemPickaxe
{
    public Soul_Pickaxe(Items item)
    {
        //super(ModItems.SoulMaterial);
        super(ToolMaterial.GOLD);
        this.setUnlocalizedName(item.getUnlocalizedName());
        this.setRegistryName(item.getRegistryName());
        this.maxStackSize = 1;
        this.setCreativeTab(TombCraft.tabTombCraft);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
    {
        World world = player.getEntityWorld();
        Block block = world.getBlockState(pos).getBlock();

        if(block == Blocks.STONE && SoulsData.getSouls(player) > 0)
        {
            if (!player.capabilities.isCreativeMode)
            {
                SoulsData.decreesSouls(player, 1);
            }

            world.setBlockToAir(pos);

            Random rand = new Random();
            int chance = rand.nextInt(99) + 1;

            if(chance == 1)
            {
                world.setBlockState(pos, Blocks.DIAMOND_ORE.getDefaultState());
            }
            else if(chance == 100)
            {
                world.setBlockState(pos, Blocks.EMERALD_ORE.getDefaultState());
            }
            else if(chance >= 80 && chance <= 85)
            {
                world.setBlockState(pos, Blocks.REDSTONE_ORE.getDefaultState());
            }
            else if(chance >= 90 && chance <= 95)
            {
                world.setBlockState(pos, Blocks.LAPIS_ORE.getDefaultState());
            }
            else if(chance >= 3 && chance <= 8)
            {
                world.setBlockState(pos, Blocks.GOLD_ORE.getDefaultState());
            }
            else if(chance >= 11 && chance <= 22)
            {
                world.setBlockState(pos, Blocks.IRON_ORE.getDefaultState());
            }
            else if(chance >= 30 && chance <= 50)
            {
                world.setBlockState(pos, Blocks.COAL_ORE.getDefaultState());
            }
        }

        return false;
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


