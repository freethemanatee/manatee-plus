package me.manatee.plus.event;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.Hero.clickgui.ClickGUI;
import de.Hero.clickgui.Panel;
import me.manatee.plus.hud.HudComponentManager;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.command.Command;
import me.manatee.plus.command.CommandManager;
import me.manatee.plus.event.events.PacketEvent;
import me.manatee.plus.event.events.PlayerJoinEvent;
import me.manatee.plus.event.events.PlayerLeaveEvent;
import me.manatee.plus.module.ModuleManager;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;

import com.google.common.collect.Maps;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class EventProcessor {
    public static EventProcessor INSTANCE;
    Minecraft mc = Minecraft.getMinecraft();
    CommandManager commandManager = new CommandManager();
    float hue = 0;
    Color c;
    int rgb;
    int speed = 2;

    public EventProcessor(){
        INSTANCE = this;
    }

    public int getRgb(){
        return rgb;
    }
    public Color getC(){
        return c;
    }
    public void setRainbowSpeed(int s){
        speed = s;
    }
    public int getRainbowSpeed(){
        return speed;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        //rainbow stuff
        c = Color.getHSBColor(hue, 1f, 1f);
        rgb = Color.HSBtoRGB(hue, 1f, 1f);
        hue += speed / 2000f;
        //Module onUpdate
        if (mc.player != null)
            ModuleManager.onUpdate();
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event) {
        if (event.isCanceled()) return;
        ModuleManager.onWorldRender(event);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        ManateePlus.EVENT_BUS.post(event);
        if(event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            //module onRender
            ModuleManager.onRender();
            //HudCompnent stuff
            for(Panel p : HudComponentManager.hudComponents){
                if(p.isHudComponent && p.isHudComponentPinned && p.extended && p.visible && !(mc.currentScreen instanceof ClickGUI))
                    p.drawHud();
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            ModuleManager.onBind(Keyboard.getEventKey());
            ManateePlus.getInstance().macroManager.getMacros().forEach(m -> {
                if(m.getKey() == Keyboard.getEventKey())
                    m.onMacro();
            });
        }
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event){
        if(Mouse.getEventButtonState())
            ManateePlus.EVENT_BUS.post(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatSent(ClientChatEvent event) {

        if (event.getMessage().startsWith(Command.getPrefix())) {
            event.setCanceled(true);
            try {
                mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
                commandManager.callCommand(event.getMessage().substring(1));
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendClientMessage(ChatFormatting.DARK_RED + "Error: " + e.getMessage());
            }
            //event.setMessage("");
        }
    }

    @SubscribeEvent
    public void onRenderScreen(RenderGameOverlayEvent.Text event) {
        ManateePlus.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event){
        ManateePlus.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        ManateePlus.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event){
        ManateePlus.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onDrawBlockHighlight(DrawBlockHighlightEvent event){
        ManateePlus.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event){ ManateePlus.EVENT_BUS.post(event); }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event){
        ManateePlus.EVENT_BUS.post(event);
    }
    @SubscribeEvent
    public void onLivingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        ManateePlus.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onInputUpdate(InputUpdateEvent event){
        ManateePlus.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event){
        ManateePlus.EVENT_BUS.post(event);}

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        ManateePlus.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        ManateePlus.EVENT_BUS.post(event);
    }

    @EventHandler
    private Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if (event.getPacket() instanceof SPacketPlayerListItem) {
            try {
                SPacketPlayerListItem packet = (SPacketPlayerListItem) event.getPacket();
                if (packet.getAction() == SPacketPlayerListItem.Action.ADD_PLAYER) {
                    for (SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
                        if (playerData.getProfile().getId() != mc.session.getProfile().getId()) {
                            new Thread(() -> {
                                String name = resolveName(playerData.getProfile().getId().toString());
                                if (name != null) {
                                    if (mc.player != null && mc.player.ticksExisted >= 1000)
                                        ManateePlus.EVENT_BUS.post(new PlayerJoinEvent(name));
                                }
                            }).start();
                        }
                    }
                }
                if (packet.getAction() == SPacketPlayerListItem.Action.REMOVE_PLAYER) {
                    for (SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
                        if (playerData.getProfile().getId() != mc.session.getProfile().getId()) {
                            new Thread(() -> {
                                final String name = resolveName(playerData.getProfile().getId().toString());
                                if (name != null) {
                                    if (mc.player != null && mc.player.ticksExisted >= 1000)
                                        ManateePlus.EVENT_BUS.post(new PlayerLeaveEvent(name));
                                }
                            }).start();
                        }
                    }
                }
            } catch(Exception e){e.printStackTrace();}
        }
    });

    private final Map<String, String> uuidNameCache = Maps.newConcurrentMap();

    public String resolveName(String uuid) {
        uuid = uuid.replace("-", "");
        if (uuidNameCache.containsKey(uuid)) {
            return uuidNameCache.get(uuid);
        }

        final String url = "https://api.mojang.com/user/profiles/" + uuid + "/names";
        try {
            final String nameJson = (new URL(url)).toString();
            if (nameJson != null && nameJson.length() > 0) {
                final JSONArray jsonArray = (JSONArray) JSONValue.parseWithException(nameJson);
                if (jsonArray != null) {
                    final JSONObject latestName = (JSONObject) jsonArray.get(jsonArray.size() - 1);
                    if (latestName != null) {
                        return latestName.get("name").toString();
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void init(){
        ManateePlus.EVENT_BUS.subscribe(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

}
