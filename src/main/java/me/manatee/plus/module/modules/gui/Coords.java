package me.manatee.plus.module.modules.gui;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;

public class Coords extends Module {
    public Coords() {
        super("Coordinates", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("CoordsRed", this, 255, 0, 255, true);
        green = new Setting("CoordsGreen", this, 255, 0, 255, true);
        blue = new Setting("CoordsBlue", this, 255, 0, 255, true);
        ManateePlus.getInstance().settingsManager.rSetting(red);
        ManateePlus.getInstance().settingsManager.rSetting(green);
        ManateePlus.getInstance().settingsManager.rSetting(blue);
        ManateePlus.getInstance().settingsManager.rSetting(rainbow = new Setting("coordsRainbow", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(customFont = new Setting("coordsCFont", this, false));
    }

    public void onEnable(){
        disable();
    }
}
