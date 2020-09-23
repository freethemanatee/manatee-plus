package me.manatee.plus.module.modules.movement;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.event.events.PacketEvent;
import me.manatee.plus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;

public class Velocity extends Module {
    public Velocity() {
        super("Velocity", Category.MOVEMENT);
    }

    public void onEnable(){
        ManateePlus.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        ManateePlus.EVENT_BUS.unsubscribe(this);
    }

    @EventHandler
    private Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if(event.getPacket() instanceof SPacketEntityVelocity){
            if(((SPacketEntityVelocity) event.getPacket()).getEntityID() == mc.player.getEntityId())
                event.cancel();
        }
        if(event.getPacket() instanceof SPacketExplosion)
            event.cancel();
    });
}
