package com.aviv871.tombcraft.event;

import com.aviv871.tombcraft.handler.SoundHandler;
import com.aviv871.tombcraft.init.ModBlocks;
import com.aviv871.tombcraft.init.ModItems;
import com.aviv871.tombcraft.utility.TCCreateToolHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TCCreateToolEvent
{
    @SubscribeEvent
    public void createSword(PlayerInteractEvent event)
    {
        World world = event.getEntityPlayer().worldObj;
        EntityPlayer player = event.getEntityPlayer();


        if (world.isRemote || player.getHeldItem(EnumHand.MAIN_HAND) == null) return;

        Block block;

        if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() == ModItems.pureSoul)
        {
            block = world.getBlockState(event.getPos()).getBlock();

            if (TCCreateToolHelper.isSorted(world, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ()))
            {
                if (block == ModBlocks.swordCore) {
                    if (!player.capabilities.isCreativeMode) {
                        player.getHeldItem(EnumHand.MAIN_HAND).stackSize -= 1;
                    }

                    SoundHandler.onEntityPlay("createTool", event.getWorld(), event.getEntity(), 1, 1);
                    world.spawnEntityInWorld(new EntityItem(world, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ModItems.soulSword)));
                    world.setBlockToAir(event.getPos());
                }

                if (block == ModBlocks.hoeCore) {
                    if (!player.capabilities.isCreativeMode) {
                        player.getHeldItem(EnumHand.MAIN_HAND).stackSize -= 1;
                    }

                    SoundHandler.onEntityPlay("createTool", event.getWorld(), event.getEntity(), 1, 1);
                    world.spawnEntityInWorld(new EntityItem(world, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ModItems.soulHoe)));
                    world.setBlockToAir(event.getPos());
                }

                if (block == ModBlocks.pickaxeCore) {
                    if (!player.capabilities.isCreativeMode) {
                        player.getHeldItem(EnumHand.MAIN_HAND).stackSize -= 1;
                    }

                    SoundHandler.onEntityPlay("createTool", event.getWorld(), event.getEntity(), 1, 1);
                    world.spawnEntityInWorld(new EntityItem(world, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ModItems.soulPickaxe)));
                    world.setBlockToAir(event.getPos());
                }

                if (block == ModBlocks.axeCore) {
                    if (!player.capabilities.isCreativeMode) {
                        player.getHeldItem(EnumHand.MAIN_HAND).stackSize -= 1;
                    }

                    SoundHandler.onEntityPlay("createTool", event.getWorld(), event.getEntity(), 1, 1);
                    world.spawnEntityInWorld(new EntityItem(world, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ModItems.soulAxe)));
                    world.setBlockToAir(event.getPos());
                }

                if (block == ModBlocks.shovelCore) {
                    if (!player.capabilities.isCreativeMode) {
                        player.getHeldItem(EnumHand.MAIN_HAND).stackSize -= 1;
                    }

                    SoundHandler.onEntityPlay("createTool", event.getWorld(), event.getEntity(), 1, 1);
                    world.spawnEntityInWorld(new EntityItem(world, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ModItems.soulShovel)));
                    world.setBlockToAir(event.getPos());
                }
            }
        }
    }
}
