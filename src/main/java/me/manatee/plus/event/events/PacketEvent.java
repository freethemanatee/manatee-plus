package me.manatee.plus.event.events;

import me.manatee.plus.event.ManateePlusEvent;
import net.minecraft.network.Packet;

public class PacketEvent extends ManateePlusEvent {

    private final Packet packet;

    public PacketEvent(Packet packet) {
        super();
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }

    public static class Receive extends PacketEvent {
        public Receive(Packet packet) {
            super(packet);
        }
    }
    public static class Send extends PacketEvent {
        public Send(Packet packet) {
            super(packet);
        }
    }

}
