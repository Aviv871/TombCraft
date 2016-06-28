package com.aviv871.tombcraft.client.settings;

import com.aviv871.tombcraft.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindings
{
    public static KeyBinding charge = new KeyBinding(Names.Keys.CHARGE, Keyboard.KEY_C, Names.Keys.CATEGORY);
    public static KeyBinding releace = new KeyBinding(Names.Keys.RELEACE, Keyboard.KEY_R, Names.Keys.CATEGORY);
}
