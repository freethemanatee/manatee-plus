package me.manatee.plus.command.commands;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.command.Command;
import me.manatee.plus.util.font.CFontRenderer;

import java.awt.*;

public class FontCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{
                "font", "setfont"
        };
    }

    @Override
    public String getSyntax() {
        return "font <Name> <Size>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        String font = args[0].replace("_", " ");
        int size = Integer.parseInt(args[1]);
        ManateePlus.fontRenderer = new CFontRenderer(new Font(font, Font.PLAIN, size), true, false);
        ManateePlus.fontRenderer.setFontName(font);
        ManateePlus.fontRenderer.setFontSize(size);
    }
}
