package me.manatee.plus;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;

public class RPCUtil {
    private static final String ClientId = "757749447680852009";
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final DiscordRPC rpc = DiscordRPC.INSTANCE;
    public static DiscordRichPresence presence = new DiscordRichPresence();
    private static String details;
    private static String state;

    public static void init(){
        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + String.valueOf(var1) + ", var2: " + var2));
        rpc.Discord_Initialize(ClientId, handlers, true, "");
        presence.startTimestamp = System.currentTimeMillis() / 1000L;
        presence.details = "Version " + ManateePlus.MODVER;
        presence.state = "On da menu";
        presence.largeImageKey = "bigmanatee";
        presence.largeImageText = "Manatee+";
        presence.smallImageKey = "bigblurrymanatee";
        presence.smallImageKey = "By dnger and zopac";

        rpc.Discord_UpdatePresence(presence);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    rpc.Discord_RunCallbacks();
                    details = "Version " + ManateePlus.MODVER;
                    state = "";
                    if (mc.isIntegratedServerRunning()) {
                        state = "Freeing manatee alone";
                    }
                    else if (mc.getCurrentServerData() != null) {
                        if (!mc.getCurrentServerData().serverIP.equals("")) {
                                    state = "Freeing mantee on " + mc.getCurrentServerData().serverIP;
                                }

                    } else {
                            state = "On da menu";
                    }
                    if (!details.equals(presence.details) || !state.equals(presence.state)) {
                        presence.startTimestamp = System.currentTimeMillis() / 1000L;
                    }
                    presence.details = details;
                    presence.state = state;
                    rpc.Discord_UpdatePresence(presence);
                } catch(Exception e2){
                    e2.printStackTrace();
                }
                try {
                    Thread.sleep(5000L);
                } catch(InterruptedException e3){
                    e3.printStackTrace();
                }
            }
            return;
        }, "Discord-RPC-Callback-Handler").start();
    }
}
