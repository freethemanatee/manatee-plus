package me.manatee.plus.util;

import me.manatee.plus.event.EventProcessor;

import java.awt.*;

public class Rainbow {
    public static int getInt(){
        return EventProcessor.INSTANCE.getRgb();
    }

    public static Color getColor(){
        return EventProcessor.INSTANCE.getC();
    }
}
