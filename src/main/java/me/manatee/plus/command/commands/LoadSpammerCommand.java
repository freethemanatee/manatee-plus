package me.manatee.plus.command.commands;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.command.Command;
import me.manatee.plus.module.modules.chat.Spammer;

public class LoadSpammerCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"loadspammer"};
    }

    @Override
    public String getSyntax() {
        return "loadspammer";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        Spammer.text.clear();
        ManateePlus.getInstance().configUtils.loadSpammer();
        Command.sendClientMessage("Loaded Spammer File");
    }
}
