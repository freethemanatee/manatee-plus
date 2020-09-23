package me.manatee.plus.module.modules.misc;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;

import java.text.DecimalFormat;

public class ClinetTimer extends Module {
    public ClinetTimer() {
        super("ClinetTimer", Category.MISC);
        ManateePlus.getInstance().settingsManager.rSetting(speedUsual = new Setting("ctSpeed", this, 4.2, 1, 10, false));
        ManateePlus.getInstance().settingsManager.rSetting(fastUsual = new Setting("ctFastSpeed", this, 10, 1, 1000, false));
        ManateePlus.getInstance().settingsManager.rSetting(tickToFast = new Setting("ctTickToFast", this, 4, 0, 20, false));
        ManateePlus.getInstance().settingsManager.rSetting(tickToNoFast = new Setting("ctTickToDisableFast", this, 7, 0, 20, false));
    }

    int tickWait = 0;
    float hudInfo = 0;
    Setting speedUsual;
    Setting fastUsual;
    Setting tickToFast;
    Setting tickToNoFast;



    public void onDisable() {
        mc.timer.tickLength = 50.0F;
    }

    public void onUpdate() {
        if ((float)tickWait == (float)tickToFast.getValDouble()) {
            mc.timer.tickLength = 50.0F / (float)fastUsual.getValDouble();
            hudInfo = (float)fastUsual.getValDouble();
        }

        if ((float)this.tickWait >= (float)tickToNoFast.getValDouble()) {
            this.tickWait = 0;
            mc.timer.tickLength = 50.0F / (float)speedUsual.getValDouble();
            hudInfo = (float)speedUsual.getValDouble();
        }

        ++this.tickWait;
    }

    public String getHudInfo(){
        return new DecimalFormat("0.##").format(hudInfo);
    }


}
