package me.manatee.plus.module.modules.chat;

import me.manatee.plus.command.Command;
import me.manatee.plus.module.Module;
import me.manatee.plus.module.ModuleManager;
import me.manatee.plus.module.modules.misc.Notifications;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VisualRange extends Module {
    public VisualRange() {
        super("VisualRange", Category.CHAT);
    }
    List<Entity> knownPlayers = new ArrayList<>();;
    List<Entity> players;

    public void onUpdate(){
        if(mc.player == null) return;
        players = mc.world.loadedEntityList.stream().filter(e-> e instanceof EntityPlayer).collect(Collectors.toList());
        try {
            for (Entity e : players) {
                if (e instanceof EntityPlayer && !e.getName().equalsIgnoreCase(mc.player.getName())) {
                    if (!knownPlayers.contains(e)) {
                        knownPlayers.add(e);
                        Command.sendClientMessage(e.getName() + " entered visual range.");
                        if(ModuleManager.isModuleEnabled("Notifications"))
                            Notifications.sendNotification(e.getName() + " entered visual range.", TrayIcon.MessageType.INFO);
                    }
                }
            }
        } catch(Exception e){} // ez no crasherino
            try {
                for (Entity e : knownPlayers) {
                    if (e instanceof EntityPlayer && !e.getName().equalsIgnoreCase(mc.player.getName())) {
                        if (!players.contains(e)) {
                            knownPlayers.remove(e);;
                        }
                    }
                }
            } catch(Exception e){} // ez no crasherino pt.2
    }

    public void onDisable(){
        knownPlayers.clear();
    }
}
