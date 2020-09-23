package me.manatee.plus.module.modules.gui;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;

public class Watermark extends Module {
    public static Watermark INSTANCE;
    public Watermark() {
        super("Watermark", Category.GUI);
        setDrawn(false);
        INSTANCE = this;
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("MarkRed", this, 255, 0, 255, true);
        green = new Setting("MarkGreen", this, 255, 0, 255, true);
        blue = new Setting("MarkBlue", this, 255, 0, 255, true);
        ManateePlus.getInstance().settingsManager.rSetting(red);
        ManateePlus.getInstance().settingsManager.rSetting(green);
        ManateePlus.getInstance().settingsManager.rSetting(blue);
        rainbow = new Setting("MarkRainbow", this, true);
        ManateePlus.getInstance().settingsManager.rSetting(rainbow);
        ManateePlus.getInstance().settingsManager.rSetting(customFont = new Setting("markCustomFont", this, true));
    }

    public void onEnable(){
        disable();
    }
}
