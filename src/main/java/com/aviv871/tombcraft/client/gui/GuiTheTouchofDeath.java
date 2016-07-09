package com.aviv871.tombcraft.client.gui;

import com.aviv871.tombcraft.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiTheTouchofDeath extends GuiScreen
{
    private final int bookImageHeight = 181;
    private final int bookImageWidth = 146;

    private int currPage = 0;
    private static final int bookTotalPages = 4;

    private NextPageButton buttonNextPage;
    private NextPageButton buttonPreviousPage;
    private GuiButton buttonDone;

    private static ResourceLocation[] pageTexture = new ResourceLocation[2];
    private static String[] pageHeadText = new String[bookTotalPages];
    private static String[] pageText = new String[bookTotalPages];

    private boolean inited = false;

    public GuiTheTouchofDeath()
    {
        pageTexture[0] = new ResourceLocation(Reference.MOD_ID+":textures/gui/theTouchofDeathGuiCovor.png");
        pageTexture[1] = new ResourceLocation(Reference.MOD_ID+":textures/gui/theTouchofDeathGui.png");

        pageText[0] = "";
        pageText[1] = "aaaaaaaaaaaaaaaaaaaaa bbbbbbbbbbbbbbbbb ccccccccccccc";
        pageText[2] = "ddddddddddddddddddddd eeeeeeeeeeeeeeeee ffffffffffffffff";
        pageText[3] = "gggggggggggggggggggg hhhhhhhhhhhhhhhhhh iiiiiiiiiiiiiiii";

        pageHeadText[0] = "";
        pageHeadText[1] = "Hello";
        pageHeadText[2] = "What's Up?";
        pageHeadText[3] = "Hye There!";
    }

    @Override
    public void initGui()
    {
        buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        int offsetFromScreenLeft = (width - bookImageWidth) / 2;

        buttonDone = new GuiButton(0, offsetFromScreenLeft + bookImageWidth / 2 - 49, 4 + bookImageHeight, 98, 20, I18n.format("gui.done", new Object[0]));
        buttonDone.visible = false;

        buttonNextPage = new NextPageButton(1, offsetFromScreenLeft + 105, 156, true);
        buttonPreviousPage = new NextPageButton(2, offsetFromScreenLeft + 17, 156, false);
        buttonPreviousPage.visible = false;

        buttonList.add(buttonDone);
        buttonList.add(buttonNextPage);
        buttonList.add(buttonPreviousPage);

        inited = true;
    }

    @Override
    public void updateScreen()
    {
        if(inited) {
            buttonDone.visible = (currPage == bookTotalPages - 1);
            buttonNextPage.visible = (currPage < bookTotalPages - 1);
            buttonPreviousPage.visible = currPage > 0;
        }
    }

    @Override
    public void drawScreen(int theWidth, int theHeight, float par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        if (currPage == 0)
        {
            mc.getTextureManager().bindTexture(pageTexture[0]);
        }
        else
        {
            mc.getTextureManager().bindTexture(pageTexture[1]);
        }

        int offsetFromScreenLeft = (width - bookImageWidth ) / 2;

        drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, bookImageWidth, bookImageHeight);

        int widthOfString = fontRendererObj.getStringWidth(pageHeadText[currPage]);
        fontRendererObj.drawSplitString(pageHeadText[currPage], offsetFromScreenLeft + (bookImageWidth / 2) - (widthOfString / 2), 20, 200, 10);
        fontRendererObj.drawSplitString(pageText[currPage], offsetFromScreenLeft + 17, 34, 116, 0);
        super.drawScreen(theWidth, theHeight, par3);

    }

    @Override
    protected void actionPerformed(GuiButton parButton)
    {
        if (parButton == buttonDone)
        {
            // something to server via packet
            mc.displayGuiScreen((GuiScreen)null);
        }
        else if (parButton == buttonNextPage)
        {
            if (currPage < bookTotalPages - 1)
            {
                ++currPage;
            }
        }
        else if (parButton == buttonPreviousPage)
        {
            if (currPage > 0)
            {
                --currPage;
            }
        }
    }

    @Override
    protected void mouseClickMove(int parMouseX, int parMouseY, int parLastButtonClicked, long parTimeSinceMouseClick)
    {
        // nullllllll
    }

    @Override
    public void onGuiClosed()
    {
        // nullllllll
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton
    {
        private final boolean isNextButton;

        public NextPageButton(int buttonId, int posX, int posY, boolean isNextButton)
        {
            super(buttonId, posX, posY, 23, 13, "");
            this.isNextButton = isNextButton;
        }

        @Override
        public void drawButton(Minecraft mc, int parX, int parY)
        {
            if (visible)
            {
                boolean isButtonPressed = (parX >= xPosition && parY >= yPosition && parX < xPosition + width && parY < yPosition + height);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(pageTexture[1]);
                int textureX = 146;
                int textureY = 0;

                if (isButtonPressed)
                {
                    textureX += 18;
                }

                if (!isNextButton)
                {
                    textureY += 10;
                }

                drawTexturedModalRect(xPosition, yPosition, textureX, textureY, 18, 10);
            }
        }
    }
}
