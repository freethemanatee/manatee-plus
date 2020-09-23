package me.manatee.plus.module.modules.render;

import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.event.events.RenderEvent;
import me.manatee.plus.friends.Friends;
import me.manatee.plus.module.Module;
import me.manatee.plus.util.ManateePlusTessellator;
import me.manatee.plus.util.Rainbow;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;

public class HitboxESP extends Module {
    public HitboxESP() {
        super("HitboxESP", Category.RENDER);
        ManateePlus.getInstance().settingsManager.rSetting(players = new Setting("hiePlayers", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(passive = new Setting("hiePassive", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(mobs = new Setting("hieMobs", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(exp = new Setting("hieXpBottles", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(epearls = new Setting("hieEpearls", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(crystals = new Setting("hieCrystals", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(items = new Setting("hieItems", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(rainbow = new Setting("hieRainbow", this, false));
        ManateePlus.getInstance().settingsManager.rSetting(r = new Setting("hieRed", this, 255, 1, 255, true));
        ManateePlus.getInstance().settingsManager.rSetting(g = new Setting("hieGreen", this, 255, 1, 255, true));
        ManateePlus.getInstance().settingsManager.rSetting(b = new Setting("hieBlue", this, 255, 1, 255, true));
        ManateePlus.getInstance().settingsManager.rSetting(a = new Setting("hieAlpha", this, 50, 1, 255, true));

    }

    Setting players;
    Setting passive;
    Setting mobs;
    Setting exp;
    Setting epearls;
    Setting crystals;
    Setting items;

    Setting rainbow;
    Setting r;
    Setting g;
    Setting b;
    Setting a;
    Color c;

    public void onWorldRender(RenderEvent event){
        c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), a.getValInt());
        if(rainbow.getValBoolean()) c = new Color(Rainbow.getColor().getRed(), Rainbow.getColor().getGreen(), Rainbow.getColor().getBlue(), a.getValInt());
        Color enemy = new Color(255, 0, 0, a.getValInt());
        Color friend = new Color(0, 255, 255, a.getValInt());
        mc.world.loadedEntityList.stream()
                .filter(entity -> entity != mc.player)
                .forEach(e -> {
                    ManateePlusTessellator.prepareGL();
                    if(players.getValBoolean() && e instanceof EntityPlayer) {
                        if (Friends.isFriend(e.getName())) ManateePlusTessellator.drawBoundingBox(e.getRenderBoundingBox(), 1, friend.getRGB());
                        else ManateePlusTessellator.drawBoundingBox(e.getRenderBoundingBox(), 1, enemy.getRGB());
                    }
                    if(mobs.getValBoolean() && GlowESP.isMonster(e)){
                        ManateePlusTessellator.drawBoundingBox(e.getRenderBoundingBox(), 1, c.getRGB());
                    }
                    if(passive.getValBoolean() && GlowESP.isPassive(e)){
                        ManateePlusTessellator.drawBoundingBox(e.getRenderBoundingBox(), 1, c.getRGB());
                    }
                    if(exp.getValBoolean() && e instanceof EntityExpBottle){
                        ManateePlusTessellator.drawBoundingBox(e.getRenderBoundingBox(), 1, c.getRGB());
                    }
                    if(epearls.getValBoolean() && e instanceof EntityEnderPearl){
                        ManateePlusTessellator.drawBoundingBox(e.getRenderBoundingBox(), 1, c.getRGB());
                    }
                    if(crystals.getValBoolean() && e instanceof EntityEnderCrystal){
                        ManateePlusTessellator.drawBoundingBox(e.getRenderBoundingBox(), 1, c.getRGB());
                    }
                    if(items.getValBoolean() && e instanceof EntityItem){
                        ManateePlusTessellator.drawBoundingBox(e.getRenderBoundingBox(), 1, c.getRGB());
                    }
                    ManateePlusTessellator.releaseGL();
                });
    }
}
