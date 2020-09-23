package me.manatee.plus.command.commands;

import me.manatee.plus.ShutDownHookerino;
import me.manatee.plus.command.Command;

public class ConfigCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"saveconfig", "savecfg"};
    }

    @Override
    public String getSyntax() {
        return "saveconfig";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        ShutDownHookerino.saveConfig();
        Command.sendClientMessage("Config saved");
    }
}
