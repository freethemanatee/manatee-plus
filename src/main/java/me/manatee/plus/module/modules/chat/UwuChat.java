package me.manatee.plus.module.modules.chat;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.command.Command;
import me.manatee.plus.event.events.PacketEvent;
import me.manatee.plus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

public class UwuChat extends Module  {
    public UwuChat() {
        super("UwuChat", Category.CHAT);
    }

    @EventHandler
    private Listener<PacketEvent.Send> packetSendListener = new Listener<>(event -> {
        if(event.getPacket() instanceof CPacketChatMessage){
            if(((CPacketChatMessage) event.getPacket()).getMessage().startsWith("/") || ((CPacketChatMessage) event.getPacket()).getMessage().startsWith(Command.getPrefix())) return;
            String old = ((CPacketChatMessage) event.getPacket()).getMessage();
            String s = old.replace("r", "w").replace("R", "W").replace("ll", "ww").replace("LL", "WW") + " uwu";
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
