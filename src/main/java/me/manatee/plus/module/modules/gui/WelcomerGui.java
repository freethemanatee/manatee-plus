package me.manatee.plus.module.modules.gui;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;

import java.util.ArrayList;


public class WelcomerGui extends Module {
    public WelcomerGui() {
        super("Welcome", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting message;
    public Setting customFont;
    ArrayList<String> messages;

    public void setup(){
        messages = new ArrayList<>();
        messages.add("Welcome1");
        messages.add("Welcome2");
        messages.add("Hello1");
        messages.add("Hello2");
        red = new Setting("welRed", this, 255, 0, 255, true);
        green = new Setting("welGreen", this, 255, 0, 255, true);
        blue = new Setting("welBlue", this, 255, 0, 255, true);
        ManateePlus.getInstance().settingsManager.rSetting(red);
        ManateePlus.getInstance().settingsManager.rSetting(green);
        ManateePlus.getInstance().settingsManager.rSetting(blue);
        rainbow = new Setting("welRainbow", this, true);
        ManateePlus.getInstance().settingsManager.rSetting(rainbow);
        ManateePlus.getInstance().settingsManager.rSetting(message = new Setting("welMessage", this, "Welcome1", messages));
        ManateePlus.getInstance().settingsManager.rSetting(customFont = new Setting("welCFont", this, false));
    }

    public void onEnable(){
        disable();
    }
}
