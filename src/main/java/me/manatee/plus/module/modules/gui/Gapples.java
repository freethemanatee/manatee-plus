package me.manatee.plus.module.modules.gui;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;
import java.util.ArrayList;

public class Gapples extends Module {
    public Gapples() {
        super("Gapples", Category.GUI);
        setDrawn(false);
    }


    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;
    public Setting mode;

    public void setup(){
        ArrayList<String> modes = new ArrayList<>();
        modes.add("Short");
        modes.add("Full");
        red = new Setting("GapplesRed", this, 255, 0, 255, true);
        green = new Setting("GapplesGreen", this, 255, 0, 255, true);
        blue = new Setting("GapplesBlue", this, 255, 0, 255, true);
        ManateePlus.getInstance().settingsManager.rSetting(red);
        ManateePlus.getInstance().settingsManager.rSetting(green);
        ManateePlus.getInstance().settingsManager.rSetting(blue);
        ManateePlus.getInstance().settingsManager.rSetting(rainbow = new Setting("gapRainbow", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(customFont = new Setting("gapCFont", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(mode = new Setting("gapText", this, "Short", modes));
    }

    public void onEnable(){
        disable();
    }
}
