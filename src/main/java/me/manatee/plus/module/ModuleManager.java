package me.manatee.plus.module;

import me.manatee.plus.event.events.RenderEvent;
import me.manatee.plus.util.ManateePlusTessellator;
import me.manatee.plus.module.modules.chat.*;
import me.manatee.plus.module.modules.combat.*;
import me.manatee.plus.module.modules.gui.*;
import me.manatee.plus.module.modules.misc.*;
import me.manatee.plus.module.modules.movement.*;
import me.manatee.plus.module.modules.player.*;
import me.manatee.plus.module.modules.render.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class ModuleManager {
    public static ArrayList<Module> modules;

    public ModuleManager(){
        modules = new ArrayList<>();
        //Combat
        addMod(new KillAura());
        addMod(new AutoCrystal());
        addMod(new Surround());
        addMod(new AutoOffhand());
        addMod(new AutoTrap());
        addMod(new AntiChainPop());
        addMod(new HoleFill());
        addMod(new Criticals());
        addMod(new SmartOffhand());
        //Player
        addMod(new Blink());
        addMod(new PortalGodMode());
        addMod(new FastUse());
        addMod(new NoSwing());
        addMod(new SpeedMine());
        //Movement
        addMod(new Sprint());
        addMod(new Velocity());
        addMod(new NoPush());
        addMod(new GuiMove());
        addMod(new ElytraFly());
        addMod(new NoSlow());
        addMod(new Speed());
    //    addMod(new Jesus());
        //Misc
        addMod(new Timer());
        addMod(new NoEntityTrace());
        addMod(new XCarry());
        addMod(new AutoNomadHut());
        addMod(new RpcModule());
        addMod(new Notifications());
        addMod(new LogoutSpots());
        addMod(new SnakeModule());
        addMod(new AutoRespawn());
        addMod(new AutoBackdoor());
        addMod(new CropNuker());
        addMod(new MiddleClickFriends());
        addMod(new DeathWaypoint());
        addMod(new ClinetTimer());
        addMod(new TotemPopCounter());
        //Chat
        addMod(new VisualRange());
        addMod(new BetterChat());
        addMod(new ToggleMsgs());
        addMod(new Announcer());
        addMod(new UwuChat());
        addMod(new AutoGG());
        addMod(new DotGodSpammer());
        addMod(new Spammer());
        addMod(new AutoReply());
        addMod(new Welcomer());
        addMod(new ColorChat());
        addMod(new ChatSuffix());
        addMod(new KettuLinuxDupe());
        //Render
        addMod(new GlowESP());
        addMod(new CameraClip());
        addMod(new Brightness());
        addMod(new LowHands());
        addMod(new HoleESP());
        addMod(new StorageESP());
        addMod(new BlockHighlight());
        addMod(new NoRender());
        addMod(new Tracers());
        addMod(new CsgoESP());
        addMod(new CapesModule());
    //    addMod(new Nametags());
        addMod(new HitboxESP());
        addMod(new FovModule());
        addMod(new BoxESP());
        //GUI
        addMod(new ModList());
        addMod(new ClickGuiModule());
        addMod(new Watermark());
        addMod(new Totems());
        addMod(new Crystals());
        addMod(new Gapples());
        addMod(new Exp());
        addMod(new Coords());
        addMod(new Fps());
        addMod(new Time());
        addMod(new Players());
        addMod(new Tps());
        addMod(new Ping());
        addMod(new PvpInfo());
        addMod(new WelcomerGui());
        addMod(new Bps());
        addMod(new PotionEffects());
        addMod(new NotificationsHud());
    }

    public static void addMod(Module m){
        modules.add(m);
    }

    public static void onUpdate() {
        modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
    }

    public static void onRender() {
        modules.stream().filter(Module::isEnabled).forEach(Module::onRender);
    }

    public static void onWorldRender(RenderWorldLastEvent event) {
        Minecraft.getMinecraft().profiler.startSection("osiris");

        Minecraft.getMinecraft().profiler.startSection("setup");
//        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.disableDepth();

        GlStateManager.glLineWidth(1f);
        Vec3d renderPos = Surround.getInterpolatedPos(Minecraft.getMinecraft().player, event.getPartialTicks());

        RenderEvent e = new RenderEvent(ManateePlusTessellator.INSTANCE, renderPos, event.getPartialTicks());
        e.resetTranslation();
        Minecraft.getMinecraft().profiler.endSection();

        modules.stream().filter(module -> module.isEnabled()).forEach(module -> {
            Minecraft.getMinecraft().profiler.startSection(module.getName());
            module.onWorldRender(e);
            Minecraft.getMinecraft().profiler.endSection();
        });

        Minecraft.getMinecraft().profiler.startSection("release");
        GlStateManager.glLineWidth(1f);

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
//        GlStateManager.popMatrix();
        ManateePlusTessellator.releaseGL();
        Minecraft.getMinecraft().profiler.endSection();

        Minecraft.getMinecraft().profiler.endSection();
    }


    public static ArrayList<Module> getModules() {
        return modules;
    }

    public static void onBind(int key) {
        if (key == 0) return;
        modules.forEach(module -> {
            if(module.getBind() == key){
                module.toggle();
            }
        });
    }

    public static Module getModuleByName(String name){
        Module m = getModules().stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        return m;
    }

    public static boolean isModuleEnabled(String name){
        Module m = getModules().stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        return m.isEnabled();
    }

    public static boolean isModuleEnabled(Module m){
        return m.isEnabled();
    }

}
