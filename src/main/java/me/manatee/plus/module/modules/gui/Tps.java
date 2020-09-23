package me.manatee.plus.module.modules.gui;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;

public class Tps extends Module {
    public static Tps INSTANCE;
    public Tps() {
        super("TPS", Category.GUI);
        setDrawn(false);
        INSTANCE = this;
    }



    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("TpsRed", this, 255, 0, 255, true);
        green = new Setting("TpsGreen", this, 255, 0, 255, true);
        blue = new Setting("TpsBlue", this, 255, 0, 255, true);
        ManateePlus.getInstance().settingsManager.rSetting(red);
        ManateePlus.getInstance().settingsManager.rSetting(green);
        ManateePlus.getInstance().settingsManager.rSetting(blue);
        ManateePlus.getInstance().settingsManager.rSetting(rainbow = new Setting("tpsRainbow", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(customFont = new Setting("tpsCFont", this, false));
    }

    public void onEnable(){
        disable();
    }
}
