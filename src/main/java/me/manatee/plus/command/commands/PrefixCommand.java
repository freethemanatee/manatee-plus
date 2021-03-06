package me.manatee.plus.command.commands;

import me.manatee.plus.command.Command;

public class PrefixCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"prefix", "setprefix"};
    }

    @Override
    public String getSyntax() {
        return "prefix <character>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        Command.setPrefix(args[0]);
        Command.sendClientMessage("Command prefix set to " + Command.getPrefix());
    }
}
