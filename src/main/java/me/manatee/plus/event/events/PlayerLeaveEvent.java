package me.manatee.plus.event.events;

import me.manatee.plus.event.ManateePlusEvent;
public class PlayerLeaveEvent extends ManateePlusEvent {

    private final String name;

    public PlayerLeaveEvent(String n){
        super();
        name = n;
    }

    public String getName(){
        return name;
    }


}
