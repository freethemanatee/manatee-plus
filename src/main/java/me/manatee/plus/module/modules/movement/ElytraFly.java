package me.manatee.plus.module.modules.movement;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;

//this is from kami blue or somethin idk
public class ElytraFly extends Module {
    public ElytraFly() {
        super("ElytraFly", Category.MOVEMENT);
    }

    Setting speed;

    public void setup(){
        ManateePlus.getInstance().settingsManager.rSetting(speed = new Setting("Speed", this, 1.8, 0, 100, false));
    }

    public void onUpdate(){
        if(mc.player.capabilities.isFlying || mc.player.isElytraFlying())
            mc.player.setSprinting(false);
        if (mc.player.capabilities.isFlying) {
                mc.player.setVelocity(0, 0, 0);
                mc.player.setPosition(mc.player.posX, mc.player.posY - 0.000050000002f, mc.player.posZ);
                mc.player.capabilities.setFlySpeed((float)speed.getValDouble());
                mc.player.setSprinting(false);
        }

        if (mc.player.onGround) {
            mc.player.capabilities.allowFlying = false;
        }

        if (mc.player.isElytraFlying()) {
            mc.player.capabilities.setFlySpeed(.915f);
            mc.player.capabilities.isFlying = true;

            if (!mc.player.capabilities.isCreativeMode)
                mc.player.capabilities.allowFlying = true;
        }
    }

    protected void onDisable() {
        mc.player.capabilities.isFlying = false;
        mc.player.capabilities.setFlySpeed(0.05f);
        if (!mc.player.capabilities.isCreativeMode)
            mc.player.capabilities.allowFlying = false;
    }

}
