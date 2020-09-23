package me.manatee.plus.module.modules.gui;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;
import java.util.ArrayList;

public class Exp extends Module {
    public Exp() {
        super("Exp", Category.GUI);
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
        red = new Setting("ExpRed", this, 255, 0, 255, true);
        green = new Setting("ExpGreen", this, 255, 0, 255, true);
        blue = new Setting("ExpBlue", this, 255, 0, 255, true);
        ManateePlus.getInstance().settingsManager.rSetting(red);
        ManateePlus.getInstance().settingsManager.rSetting(green);
        ManateePlus.getInstance().settingsManager.rSetting(blue);
        ManateePlus.getInstance().settingsManager.rSetting(rainbow = new Setting("expRainbow", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(customFont = new Setting("expCFont", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(mode = new Setting("expText", this, "Short", modes));
    }

    public void onEnable(){
        disable();
    }
}
