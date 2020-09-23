package me.manatee.plus.module.modules.misc;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.command.Command;
import me.manatee.plus.event.events.GuiScreenDisplayedEvent;
import me.manatee.plus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.GuiGameOver;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", Category.MISC);
    }

    Setting coords;

    public void setup(){
        ManateePlus.getInstance().settingsManager.rSetting(coords = new Setting("DeathCoords", this, true));
    }

    @EventHandler
    private Listener<GuiScreenDisplayedEvent> listener = new Listener<>(event -> {
        if(event.getScreen() instanceof GuiGameOver) {
            if(coords.getValBoolean())
                Command.sendClientMessage(String.format("You died at x%d y%d z%d", (int)mc.player.posX, (int)mc.player.posY, (int)mc.player.posZ));
            if(mc.player != null)
                mc.player.respawnPlayer();
            mc.displayGuiScreen(null);
        }
    });

    public void onEnable(){
        ManateePlus.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        ManateePlus.EVENT_BUS.unsubscribe(this);
    }
}
