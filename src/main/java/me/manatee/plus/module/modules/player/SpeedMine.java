package me.manatee.plus.module.modules.player;

import me.manatee.plus.module.Module;

public class SpeedMine extends Module {
    public SpeedMine() {
        super("SpeedMine", Category.PLAYER);
    }

    public void onUpdate(){
        if(mc.playerController.curBlockDamageMP >= 0)
            mc.playerController.curBlockDamageMP = 1;
    }
}
