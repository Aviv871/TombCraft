package com.aviv871.tombcraft.block;

import com.aviv871.tombcraft.reference.Blocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class Edge extends BlockTombCraft
{
    private static final PropertyBool CONNECTED_DOWN = PropertyBool.create("down");
    private static final PropertyBool CONNECTED_EAST = PropertyBool.create("east");
    private static final PropertyBool CONNECTED_NORTH = PropertyBool.create("north");
    private static final PropertyBool CONNECTED_SOUTH = PropertyBool.create("south");
    private static final PropertyBool CONNECTED_UP = PropertyBool.create("up");
    private static final PropertyBool CONNECTED_WEST = PropertyBool.create("west");

    public Edge(Blocks block, Material material, float hardness, float resistance)
    {
        super(block, material, hardness, resistance);

        this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED_DOWN, Boolean.FALSE).withProperty(CONNECTED_EAST, Boolean.FALSE).withProperty(CONNECTED_NORTH, Boolean.FALSE).withProperty(CONNECTED_SOUTH, Boolean.FALSE).withProperty(CONNECTED_UP, Boolean.FALSE).withProperty(CONNECTED_WEST, Boolean.FALSE));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        state = state
                .withProperty(CONNECTED_DOWN, this.isAdjacentBlockTheSameType(world, pos, EnumFacing.DOWN))
                .withProperty(CONNECTED_EAST, this.isAdjacentBlockTheSameType(world, pos, EnumFacing.EAST))
                .withProperty(CONNECTED_NORTH, this.isAdjacentBlockTheSameType(world, pos, EnumFacing.NORTH))
                .withProperty(CONNECTED_SOUTH, this.isAdjacentBlockTheSameType(world, pos, EnumFacing.SOUTH))
                .withProperty(CONNECTED_UP, this.isAdjacentBlockTheSameType(world, pos, EnumFacing.UP))
                .withProperty(CONNECTED_WEST, this.isAdjacentBlockTheSameType(world, pos, EnumFacing.WEST));
        return state;
    }

    private boolean isAdjacentBlockTheSameType(IBlockAccess world, BlockPos position, EnumFacing facing)
    {
        BlockPos newPosition = position.offset(facing);
        IBlockState blockState = world.getBlockState(newPosition);
        Block block = (null == blockState) ? null : blockState.getBlock();

        return this == block;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {CONNECTED_DOWN, CONNECTED_UP, CONNECTED_NORTH, CONNECTED_SOUTH, CONNECTED_WEST, CONNECTED_EAST});
    }
}
