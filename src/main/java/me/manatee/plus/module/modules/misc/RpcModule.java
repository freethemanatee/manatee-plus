package me.manatee.plus.module.modules.misc;

import me.manatee.plus.RPCUtil;
import me.manatee.plus.command.Command;
import me.manatee.plus.module.Module;

public class RpcModule extends Module {
    public RpcModule() {
        super("DiscordRPC", Category.MISC);
        setDrawn(false);
    }

    public void onEnable(){
        RPCUtil.init();
        if(mc.player != null)
            Command.sendClientMessage("discord rpc started");
    }

    public void onDisable(){
        Command.sendClientMessage("you need to restart your game disable rpc");
    }
}
