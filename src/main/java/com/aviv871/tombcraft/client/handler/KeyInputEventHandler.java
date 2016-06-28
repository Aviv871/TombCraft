package com.aviv871.tombcraft.client.handler;

import com.aviv871.tombcraft.client.settings.KeyBindings;
import com.aviv871.tombcraft.reference.Key;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler
{
    private static Key getPressedKeybindung()
    {
        if (KeyBindings.charge.isPressed())
        {
            return Key.CHARGE;
        }
        else if (KeyBindings.releace.isPressed())
        {
            return Key.RELEACE;
        }

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        //LogHelper.info(getPressedKeybindung());
    }
}
