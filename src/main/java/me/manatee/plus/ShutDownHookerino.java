package me.manatee.plus;

public class ShutDownHookerino extends Thread {
    @Override
    public void run(){
        saveConfig();
    }

    public static void saveConfig(){
        ManateePlus.getInstance().configUtils.saveMods();
        ManateePlus.getInstance().configUtils.saveSettingsList();
        ManateePlus.getInstance().configUtils.saveBinds();
        ManateePlus.getInstance().configUtils.saveDrawn();
        ManateePlus.getInstance().configUtils.saveFriends();
        ManateePlus.getInstance().configUtils.saveGui();
        ManateePlus.getInstance().configUtils.savePrefix();
        ManateePlus.getInstance().configUtils.saveRainbow();
        ManateePlus.getInstance().configUtils.saveMacros();
        ManateePlus.getInstance().configUtils.saveMsgs();
        ManateePlus.getInstance().configUtils.saveAutoGG();
        ManateePlus.getInstance().configUtils.saveSpammer();
        ManateePlus.getInstance().configUtils.saveAutoReply();
        ManateePlus.getInstance().configUtils.saveAnnouncer();
        ManateePlus.getInstance().configUtils.saveWaypoints();
        ManateePlus.getInstance().configUtils.saveHudComponents();
        ManateePlus.getInstance().configUtils.saveFont();
    }
}
