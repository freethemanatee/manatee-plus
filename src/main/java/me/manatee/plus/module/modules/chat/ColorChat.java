package me.manatee.plus.module.modules.chat;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.command.Command;
import me.manatee.plus.event.events.PacketEvent;
import me.manatee.plus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

import java.util.ArrayList;

public class ColorChat extends Module {
    public ColorChat() {
        super("ColorChat", Category.CHAT);
    }

    Setting mode;

    public void setup(){
        ArrayList<String> modes = new ArrayList<>();
        modes.add("Green");
        modes.add("Blue");
        ManateePlus.getInstance().settingsManager.rSetting(mode = new Setting("ccColor", this, "Green", modes));
    }

    @EventHandler
    private Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if(event.getPacket() instanceof CPacketChatMessage){
            if(((CPacketChatMessage) event.getPacket()).getMessage().startsWith("/") || ((CPacketChatMessage) event.getPacket()).getMessage().startsWith(Command.getPrefix())) return;
            String message = ((CPacketChatMessage) event.getPacket()).getMessage();
            String prefix = "";
            if(mode.getValString().equalsIgnoreCase("Green")) prefix = ">";
            if(mode.getValString().equalsIgnoreCase("Blue")) prefix = "`";
            String s = prefix + message;
            if(s.length() > 255) return;
            ((CPacketChatMessage) event.getPacket()).message = s;
        }
    });

    public void onEnable(){
        ManateePlus.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        ManateePlus.EVENT_BUS.unsubscribe(this);
    }
}
