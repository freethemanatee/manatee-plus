package me.manatee.plus.module.modules.chat;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.command.Command;
import me.manatee.plus.module.Module;

import java.util.ArrayList;
import java.util.List;

public class Spammer extends Module {
    public Spammer() {
        super("Spammer", Category.CHAT);
        text = new ArrayList<>();
    }

    public static List<String> text;
    int waitCounter;
    Setting delay;
    int i = -1;

    public void setup(){
        ManateePlus.getInstance().settingsManager.rSetting(delay = new Setting("SpammerDelay", this, 5, 1, 100, true));
    }

    public void onUpdate(){
        if(text.size() <= 0 || text.isEmpty()){
            Command.sendClientMessage("Spammer list empty, disabling");
            disable();
        }
        if (waitCounter < delay.getValDouble() * 100) {
            waitCounter++;
            return;
        } else {
            waitCounter = 0;
        }
        i++;
        if(!(i + 1 > text.size()))
            mc.player.sendChatMessage(text.get(i));
        else
            i = -1;

    }
}
