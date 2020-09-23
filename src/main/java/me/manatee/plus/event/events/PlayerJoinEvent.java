package me.manatee.plus.event.events;

import me.manatee.plus.event.ManateePlusEvent;

public class PlayerJoinEvent extends ManateePlusEvent {
    private final String name;

    public PlayerJoinEvent(String n){
        super();
        name = n;
    }

    public String getName(){
        return name;
    }
}
