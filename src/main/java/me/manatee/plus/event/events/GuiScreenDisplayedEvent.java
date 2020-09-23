package me.manatee.plus.event.events;

import me.manatee.plus.event.ManateePlusEvent;
import net.minecraft.client.gui.GuiScreen;

public class GuiScreenDisplayedEvent extends ManateePlusEvent {
    private final GuiScreen guiScreen;
    public GuiScreenDisplayedEvent(GuiScreen screen){
        super();
        guiScreen = screen;
    }

    public GuiScreen getScreen(){
        return guiScreen;
    }

}
