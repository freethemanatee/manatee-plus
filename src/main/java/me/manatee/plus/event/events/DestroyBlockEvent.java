package me.manatee.plus.event.events;

import me.manatee.plus.event.ManateePlusEvent;
import net.minecraft.util.math.BlockPos;

public class DestroyBlockEvent extends ManateePlusEvent {
    BlockPos pos;
    public DestroyBlockEvent(BlockPos blockPos){
        super();
        pos = blockPos;
    }

    public BlockPos getBlockPos(){
        return pos;
    }
}
