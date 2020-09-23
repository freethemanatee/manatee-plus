package me.manatee.plus.module.modules.gui;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;
import java.util.ArrayList;

public class Crystals extends Module {
    public Crystals() {
        super("Crystals", Category.GUI);
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
        red = new Setting("CrystalsRed", this, 255, 0, 255, true);
        green = new Setting("CrystalsGreen", this, 255, 0, 255, true);
        blue = new Setting("CrystalsBlue", this, 255, 0, 255, true);
        ManateePlus.getInstance().settingsManager.rSetting(red);
        ManateePlus.getInstance().settingsManager.rSetting(green);
        ManateePlus.getInstance().settingsManager.rSetting(blue);
        ManateePlus.getInstance().settingsManager.rSetting(rainbow = new Setting("cryRainbow", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(customFont = new Setting("cryCFont", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(mode = new Setting("cryText", this, "Short", modes));
    }

    public void onEnable(){
        disable();
    }
}
