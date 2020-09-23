package me.manatee.plus.module.modules.gui;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;

public class Bps extends Module {
    public Bps() {
        super("BPS", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("bpsRed", this, 255, 0, 255, true);
        green = new Setting("bpsGreen", this, 255, 0, 255, true);
        blue = new Setting("bpsBlue", this, 255, 0, 255, true);
        ManateePlus.getInstance().settingsManager.rSetting(red);
        ManateePlus.getInstance().settingsManager.rSetting(green);
        ManateePlus.getInstance().settingsManager.rSetting(blue);
        rainbow = new Setting("bpsRainbow", this, false);
        ManateePlus.getInstance().settingsManager.rSetting(rainbow);
        ManateePlus.getInstance().settingsManager.rSetting(customFont = new Setting("bpsCFont", this, false));
    }

    public void onEnable(){
        disable();
    }


}
