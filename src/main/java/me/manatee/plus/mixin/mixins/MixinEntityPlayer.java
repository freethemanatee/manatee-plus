package me.manatee.plus.mixin.mixins;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.event.events.PlayerJumpEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {
    @Shadow public abstract String getName();

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    public void onJump(CallbackInfo ci){
        if(Minecraft.getMinecraft().player.getName() == this.getName()){
            ManateePlus.EVENT_BUS.post(new PlayerJumpEvent());
        }
    }
}
