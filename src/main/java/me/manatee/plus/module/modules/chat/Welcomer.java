package me.manatee.plus.module.modules.chat;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.command.Command;
import me.manatee.plus.event.events.PlayerJoinEvent;
import me.manatee.plus.event.events.PlayerLeaveEvent;
import me.manatee.plus.module.Module;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class Welcomer extends Module {
    public Welcomer() {
        super("Welcomer", Category.CHAT);
        ManateePlus.getInstance().settingsManager.rSetting(publicS = new Setting("wPublic", this, false));
    }
    Setting publicS;

    @EventHandler
    private Listener<PlayerJoinEvent> listener1 = new Listener<>(event -> {
        if(publicS.getValBoolean()) mc.player.sendChatMessage(event.getName() + " joined the game");
        else Command.sendClientMessage(event.getName() + " joined the game");
    });

    @EventHandler
    private Listener<PlayerLeaveEvent> listener2 = new Listener<>(event -> {
        if(publicS.getValBoolean()) mc.player.sendChatMessage(event.getName() + " left the game");
        else Command.sendClientMessage(event.getName() + " left the game");
    });

    public void onEnable(){
        ManateePlus.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        ManateePlus.EVENT_BUS.unsubscribe(this);
    }
}
