package me.manatee.plus.module.modules.gui;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

public class NotificationsHud extends Module {
    public NotificationsHud() {
        super("HudNotifications", Category.GUI);
    }

    int count;
    int waitCounter;
    int color;
    Setting x;
    Setting y;
    Setting sortUp;
    Setting right;
    //static TextComponentString s = new TextComponentString("No Notifications");
    static List<TextComponentString> list = new ArrayList<>();

    public void setup(){
        x = new Setting("notX", this, 2, 0, 1000, true);
        y = new Setting("notY", this, 12, 0, 1000, true);
        ManateePlus.getInstance().settingsManager.rSetting(x);
        ManateePlus.getInstance().settingsManager.rSetting(y);
        sortUp = new Setting("notSortUp", this, true);
        ManateePlus.getInstance().settingsManager.rSetting(sortUp);
        right = new Setting("notAlignRight", this, false);
        ManateePlus.getInstance().settingsManager.rSetting(right);
    }

    public void onUpdate(){
            if (waitCounter < 500) {
                waitCounter++;
                return;
            } else {
                waitCounter = 0;
            }
            if(list.size() > 0)
                list.remove(0);
    }

    public void onRender(){
        count = 0;
        for(TextComponentString s : list) {
            count = list.indexOf(s) + 1;
            try {
                color = s.getStyle().getColor().getColorIndex();
            } catch(NullPointerException e){
                color = 0xffffffff;
            }
            if (sortUp.getValBoolean()) {
                if (right.getValBoolean()) {
                    mc.fontRenderer.drawStringWithShadow(s.getText(), (int) x.getValDouble() - mc.fontRenderer.getStringWidth(s.getText()), (int) y.getValDouble() + (count * 10), color);
                } else {
                    mc.fontRenderer.drawStringWithShadow(s.getText(), (int) x.getValDouble(), (int) y.getValDouble() + (count * 10), color);
                }
                count++;
            } else {
                if (right.getValBoolean()) {
                    mc.fontRenderer.drawStringWithShadow(s.getText(), (int) x.getValDouble() - mc.fontRenderer.getStringWidth(s.getText()), (int) y.getValDouble() + (count * -10), color);
                } else {
                    mc.fontRenderer.drawStringWithShadow(s.getText(), (int) x.getValDouble(), (int) y.getValDouble() + (count * -10), color);
                }
                count++;
            }
        }
    }

    public static void addMessage(TextComponentString m){
        if(list.size() < 3) {
            list.remove(m);
            list.add(m);
        }else {
            list.remove(0);
            list.remove(m);
            list.add(m);
        }
    }
}
