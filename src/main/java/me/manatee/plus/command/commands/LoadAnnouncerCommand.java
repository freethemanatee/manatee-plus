package me.manatee.plus.command.commands;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.command.Command;

public class LoadAnnouncerCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{
                "loadannouncer"
        };
    }

    @Override
    public String getSyntax() {
        return "loadannouncer";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        ManateePlus.getInstance().configUtils.loadAnnouncer();
        sendClientMessage("Loaded Announcer file");
    }
}
