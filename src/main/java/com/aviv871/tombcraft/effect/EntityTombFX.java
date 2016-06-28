package com.aviv871.tombcraft.effect;

public class EntityTombFX
{
    /*public static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/particle/tomb.png");

    public EntityTombFX(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    @Override
    public void renderParticle(Tessellator tessellator, float particleTicks, float var3, float var4, float var5, float var6, float var7)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
        tessellator.startDrawingQuads();
        tessellator.setBrightness(getBrightnessForRender(particleTicks));
        float scale = 0.1F * particleScale;
        float x = (float)(prevPosX + (posX - prevPosX) * particleTicks - interpPosX);
        float y = (float)(prevPosY + (posY - prevPosY) * particleTicks - interpPosY);;
        float z = (float)(prevPosZ + (posZ - prevPosZ) * particleTicks - interpPosZ);;
        tessellator.addVertexWithUV(x - var3 * scale - var6 * scale, y - var4 * scale, z - var5 * scale - var7 * scale, 0, 0);
        tessellator.addVertexWithUV(x - var3 * scale + var6 * scale, y + var4 * scale, z - var5 * scale + var7 * scale, 1, 0);
        tessellator.addVertexWithUV(x + var3 * scale + var6 * scale, y + var4 * scale, z - var5 * scale + var7 * scale, 1, 1);
        tessellator.addVertexWithUV(x + var3 * scale - var6 * scale, y - var4 * scale, z - var5 * scale - var7 * scale, 0, 1);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
    } */
}
