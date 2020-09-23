package me.manatee.plus.module.modules.misc;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.event.events.PacketEvent;
import me.manatee.plus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.*;

public class XCarry extends Module {
    public XCarry() {
        super("XCarry", Category.MISC);
    }

    @EventHandler
    private Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if(event.getPacket() instanceof CPacketCloseWindow){
            if(((CPacketCloseWindow)event.getPacket()).windowId == mc.player.inventoryContainer.windowId){
                event.cancel();
            }
        }
    });

    public void onEnable(){
        ManateePlus.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        ManateePlus.EVENT_BUS.unsubscribe(this);
    }
}
