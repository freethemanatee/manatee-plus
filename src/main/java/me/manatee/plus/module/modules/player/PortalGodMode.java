package me.manatee.plus.module.modules.player;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.event.events.PacketEvent;
import me.manatee.plus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketConfirmTeleport;

public class PortalGodMode extends Module {
    public PortalGodMode() {
        super("PortalGodmode", Category.PLAYER);
    }

    public void onEnable() {
        ManateePlus.EVENT_BUS.subscribe(this);
    }

    public void onDisable() {
        ManateePlus.EVENT_BUS.unsubscribe(this);
    }

    @EventHandler
    private Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if(event.getPacket() instanceof CPacketConfirmTeleport)
            event.cancel();
    });
}
