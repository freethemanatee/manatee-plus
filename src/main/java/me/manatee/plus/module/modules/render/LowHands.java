package me.manatee.plus.module.modules.render;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;
import net.minecraft.client.renderer.ItemRenderer;

public class LowHands extends Module {
    public LowHands() {
        super("LowOffhand", Category.RENDER);
    }
    Setting off;
    ItemRenderer itemRenderer = mc.entityRenderer.itemRenderer;

    public void setup(){
        off = new Setting("Height", this, 0.5, 0, 1, false);
        ManateePlus.getInstance().settingsManager.rSetting(off);
    }

    public void onUpdate(){
        itemRenderer.equippedProgressOffHand = (float)off.getValDouble();
    }
}
