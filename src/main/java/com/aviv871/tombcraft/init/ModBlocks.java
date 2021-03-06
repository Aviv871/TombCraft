package com.aviv871.tombcraft.init;

import com.aviv871.tombcraft.block.*;
import com.aviv871.tombcraft.reference.Blocks;
import com.aviv871.tombcraft.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static BlockTombCraft tombRelic = new Tomb_Relic(Blocks.TOMB_RELIC, Material.IRON, 1.0f, 1.0f);
    public static BlockTombCraft tombRiser = new Tomb_Riser(Blocks.TOMB_RISER, Material.IRON, 1.0f, 1.0f);

    public static BlockTombCraft swordCore = new Sword_Core(Blocks.SWORD_CORE, Material.IRON, 1.0f, 1.0f);
    public static BlockTombCraft pickaxeCore = new PickAxe_Core(Blocks.PICKAXE_CORE, Material.IRON, 1.0f, 1.0f);
    public static BlockTombCraft axeCore = new Axe_Core(Blocks.AXE_CORE, Material.IRON, 1.0f, 1.0f);
    public static BlockTombCraft hoeCore = new Hoe_Core(Blocks.HOE_CORE, Material.IRON, 1.0f, 1.0f);
    public static BlockTombCraft shovelCore = new Shovel_Core(Blocks.SHOVEL_CORE, Material.IRON, 1.0f, 1.0f);

    public static BlockTombCraft relicLab = new Relic_Lab(Blocks.RELIC_LAB, Material.IRON, 1.0f, 1.0f);

    public static BlockTombCraft ghostSeat = new Ghost_Seat(Blocks.GHOST_SEAT, Material.GLASS, 1.0f, 1.0f);
    public static BlockTombCraft handyMan = new Handy_Man(Blocks.HANDY_MAN, Material.GLASS, 1.0f, 1.0f);
    public static BlockTombCraft edge = new Edge(Blocks.EDGE, Material.IRON, 1.0f, 1.0f);

    public static void register()
    {
        registerBlock(tombRelic);
        registerBlock(tombRiser);

        registerBlock(swordCore);
        registerBlock(pickaxeCore);
        registerBlock(axeCore);
        registerBlock(hoeCore);
        registerBlock(shovelCore);

        registerBlock(relicLab);

        registerBlock(ghostSeat);
        registerBlock(handyMan);
        registerBlock(edge);
    }

    private static void registerBlock(Block block)
    {
        GameRegistry.register(block);
        ItemBlock itemblock = new ItemBlock(block);
        itemblock.setRegistryName(block.getRegistryName());
        GameRegistry.register(itemblock);
    }
}
