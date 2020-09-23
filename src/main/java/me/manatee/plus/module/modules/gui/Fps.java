package me.manatee.plus.module.modules.gui;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;

public class Fps extends Module {
    public Fps() {
        super("FPS", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("FpsRed", this, 255, 0, 255, true);
        green = new Setting("FpsGreen", this, 255, 0, 255, true);
        blue = new Setting("FpsBlue", this, 255, 0, 255, true);
        ManateePlus.getInstance().settingsManager.rSetting(red);
        ManateePlus.getInstance().settingsManager.rSetting(green);
        ManateePlus.getInstance().settingsManager.rSetting(blue);
        ManateePlus.getInstance().settingsManager.rSetting(rainbow = new Setting("fpsRainbow", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(customFont = new Setting("fpsCFont", this, false));
    }

    public void onEnable(){
        disable();
    }
}
