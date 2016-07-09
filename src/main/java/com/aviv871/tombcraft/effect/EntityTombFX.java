package com.aviv871.tombcraft.effect;

import com.aviv871.tombcraft.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityTombFX extends Particle
{
    public static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/particle/tomb.png");

    public EntityTombFX(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    @Override
    public void renderParticle(VertexBuffer worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        this.getBrightnessForRender(getBrightnessForRender(partialTicks));
        float scale = 0.1F * particleScale;
        float x = (float)(prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
        float y = (float)(prevPosY + (posY - prevPosY) * partialTicks - interpPosY);;
        float z = (float)(prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);;
        worldRendererIn.pos(x - partialTicks * scale - rotationYZ * scale, y - rotationX * scale, z - rotationZ * scale - rotationXY * scale);
        worldRendererIn.pos(x - partialTicks * scale + rotationYZ * scale, y + rotationX * scale, z - rotationZ * scale + rotationXY * scale);
        worldRendererIn.pos(x + partialTicks * scale + rotationYZ * scale, y + rotationX * scale, z - rotationZ * scale + rotationXY * scale);
        worldRendererIn.pos(x + partialTicks * scale - rotationYZ * scale, y - rotationX * scale, z - rotationZ * scale - rotationXY * scale);
    }
}
