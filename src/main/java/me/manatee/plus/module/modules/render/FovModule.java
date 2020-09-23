package me.manatee.plus.module.modules.render;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;

public class FovModule extends Module {
    public FovModule() {
        super("FOV", Category.RENDER);
        ManateePlus.getInstance().settingsManager.rSetting(fov = new Setting("FovAmount", this, 90, 0, 180, true));
        setDrawn(false);
    }

    Setting fov;

    public void onUpdate(){
        mc.gameSettings.fovSetting = (float)fov.getValInt();
    }
}
