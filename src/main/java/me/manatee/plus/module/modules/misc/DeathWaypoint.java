package me.manatee.plus.module.modules.misc;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.event.events.GuiScreenDisplayedEvent;
import me.manatee.plus.module.Module;
import me.manatee.plus.waypoint.Waypoint;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.GuiGameOver;

import java.awt.*;

public class DeathWaypoint extends Module {
    public DeathWaypoint() {
        super("DeathWaypoint", Category.MISC);
    }

    @EventHandler
    private Listener<GuiScreenDisplayedEvent> listener = new Listener<>(event -> {
        if (event.getScreen() instanceof GuiGameOver) {
            ManateePlus.getInstance().waypointManager.delWaypoint( ManateePlus.getInstance().waypointManager.getWaypointByName("Last Death"));
            ManateePlus.getInstance().waypointManager.addWaypoint(new Waypoint("Last Death", mc.player.posX, mc.player.posY, mc.player.posZ, Color.RED.getRGB()));
        }
    });

    public void onEnable(){
        ManateePlus.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        ManateePlus.EVENT_BUS.unsubscribe(this);
    }

}
