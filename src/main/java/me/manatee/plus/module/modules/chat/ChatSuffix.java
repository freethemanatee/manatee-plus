package me.manatee.plus.module.modules.chat;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.command.Command;
import me.manatee.plus.event.events.PacketEvent;
import me.manatee.plus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

import java.util.ArrayList;

public class ChatSuffix extends Module {
    public ChatSuffix() {
        super("ChatSuffix", Category.CHAT);
    }

    Setting mode;

    public void setup() {
        ArrayList<String> modes = new ArrayList<>();
        modes.add("Default");
        modes.add("Default + Version");
        ManateePlus.getInstance().settingsManager.rSetting(mode = new Setting("Mode", this, "Default", modes));
    }

    @EventHandler
    private Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketChatMessage) {
            if (((CPacketChatMessage) event.getPacket()).getMessage().startsWith("/") || ((CPacketChatMessage) event.getPacket()).getMessage().startsWith(Command.getPrefix())) return;
            String old = ((CPacketChatMessage) event.getPacket()).getMessage();
            String suffix = "";
            if (mode.getValString().equalsIgnoreCase("Default")) suffix = " " + toUnicode(ManateePlus.MODNAME);
            if (mode.getValString().equalsIgnoreCase("Default + Version")) suffix = " " + toUnicode(ManateePlus.MODNAME) + " " + toUnicode(ManateePlus.MODVER);
            String s = old + suffix;
            if (s.length() > 255) return;
            ((CPacketChatMessage) event.getPacket()).message = s;
        }
    });

    public void onEnable() {
        ManateePlus.EVENT_BUS.subscribe(this);
    }

    public void onDisable() {
        ManateePlus.EVENT_BUS.unsubscribe(this);
    }

    public String toUnicode(String s) {
        return s.toLowerCase()
                .replace("a", "\u1d00")
                .replace("b", "\u0299")
                .replace("c", "\u1d04")
                .replace("d", "\u1d05")
                .replace("e", "\u1d07")
                .replace("f", "\ua730")
                .replace("g", "\u0262")
                .replace("h", "\u029c")
                .replace("i", "\u026a")
                .replace("j", "\u1d0a")
                .replace("k", "\u1d0b")
                .replace("l", "\u029f")
                .replace("m", "\u1d0d")
                .replace("n", "\u0274")
                .replace("o", "\u1d0f")
                .replace("p", "\u1d18")
                .replace("q", "\u01eb")
                .replace("r", "\u0280")
                .replace("s", "\ua731")
                .replace("t", "\u1d1b")
                .replace("u", "\u1d1c")
                .replace("v", "\u1d20")
                .replace("w", "\u1d21")
                .replace("x", "\u02e3")
                .replace("y", "\u028f")
                .replace("z", "\u1d22")
                //Numbers!
                .replace("1", "")
                .replace("2", "")
                .replace("3", "")
                .replace("4", "")
                .replace("5", "")
                .replace("6", "")
                .replace("7", "")
                .replace("8", "")
                .replace("9", "")
                .replace("0", "")
                //Characters!
                .replace("!", "")
                .replace("@", "")
                .replace("#", "")
                .replace("$", "")
                .replace("%", "")
                .replace("^", "")
                .replace("&", "")
                .replace("*", "")
                .replace("(", "")
                .replace(")", "")
                .replace("`", "")
                .replace("~", "")
                .replace("-", "")
                .replace("=", "")
                .replace("_", "")
                .replace("+", "")
                .replace("[", "")
                .replace("]", "")
                .replace("{", "")
                .replace("}", "")
//Well, shit.    .replace("\", "") //Doesn't work :(
                .replace("|", "")
                .replace(";", "")
                .replace("'", "")
                .replace(":", "")
//Well, shit.    .replace(""", "") //Doesn't work :(
                .replace(",", "")
                .replace(".", "")
                .replace("/", "")
                .replace("<", "")
                .replace(">", "")
                .replace("?", "");
    }
}