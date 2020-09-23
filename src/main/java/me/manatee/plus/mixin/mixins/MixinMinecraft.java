package me.manatee.plus.mixin.mixins;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.event.events.GuiScreenDisplayedEvent;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "displayGuiScreen", at = @At("HEAD"), cancellable = true)
    public void displayGuiScreen(GuiScreen guiScreenIn, CallbackInfo info) {
        try {
            GuiScreenDisplayedEvent screenEvent = new GuiScreenDisplayedEvent(guiScreenIn);
            ManateePlus.EVENT_BUS.post(screenEvent);
            guiScreenIn = screenEvent.getScreen();
        } catch (Exception e){}
    }
}
