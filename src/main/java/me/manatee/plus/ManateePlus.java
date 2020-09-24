package me.manatee.plus;

import de.Hero.clickgui.ClickGUI;
import me.manatee.plus.hud.HudComponentManager;
import de.Hero.settings.SettingsManager;
import me.manatee.plus.command.CommandManager;
import me.manatee.plus.event.EventProcessor;
import me.manatee.plus.macro.MacroManager;
import me.manatee.plus.module.ModuleManager;
import me.manatee.plus.util.CapeUtils;
import me.manatee.plus.util.ConfigUtils;
import me.manatee.plus.friends.Friends;
import me.manatee.plus.util.TpsUtils;
import me.manatee.plus.util.font.CFontRenderer;
import me.manatee.plus.waypoint.WaypointManager;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.awt.*;

@Mod(modid = ManateePlus.MODID, name = ManateePlus.MODNAME, version = ManateePlus.MODVER, clientSideOnly = true)
public class ManateePlus {
    public static final String MODID = "manateeplus";
    public static final String MODNAME = "Manatee+";
    public static final String MODVER = "0.1.1";

    public static final Logger log = LogManager.getLogger(MODNAME);
    public ClickGUI clickGui;
    public SettingsManager settingsManager;
    public Friends friends;
    public ModuleManager moduleManager;
    public ConfigUtils configUtils;
    public CapeUtils capeUtils;
    public MacroManager macroManager;
    EventProcessor eventProcessor;
    public WaypointManager waypointManager;
    public static CFontRenderer fontRenderer;

    public static final EventBus EVENT_BUS = new EventManager();

    @Mod.Instance
    private static ManateePlus INSTANCE;

    public ManateePlus(){
        INSTANCE = this;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        //log.info("PreInitialization complete!\n");

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        eventProcessor = new EventProcessor();
        eventProcessor.init();
        fontRenderer = new CFontRenderer(new Font("Arial", Font.PLAIN, 20), true, false);
        TpsUtils tpsUtils = new TpsUtils();

        settingsManager = new SettingsManager();
        log.info("Settings initialized!");

        friends = new Friends();
        log.info("Friends initialized!");

        moduleManager = new ModuleManager();
        log.info("Modules initialized!");

        clickGui = new ClickGUI();
        HudComponentManager hudComponentManager = new HudComponentManager(0, 0, clickGui);
        log.info("ClickGUI initialized!");

        macroManager = new MacroManager();
        log.info("Macros initialised!");

        configUtils = new ConfigUtils();
        Runtime.getRuntime().addShutdownHook(new ShutDownHookerino());
        log.info("Config loaded!");

        CommandManager.initCommands();
        log.info("Commands initialised!");

        waypointManager = new WaypointManager();
        log.info("Waypoints initialised!");

        log.info("Initialization complete!\n");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        Display.setTitle(MODNAME + " " + MODVER);

        capeUtils = new CapeUtils();
        log.info("Capes initialised!");

        //WelcomeWindow ww = new WelcomeWindow();
        //ww.setVisible(false);
        log.info("PostInitialization complete!\n");
    }

    public static ManateePlus getInstance(){
        return INSTANCE;
    }

}
