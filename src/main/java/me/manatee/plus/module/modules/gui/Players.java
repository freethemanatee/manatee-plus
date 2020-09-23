package me.manatee.plus.module.modules.gui;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.Hero.settings.Setting;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.Module;
import me.manatee.plus.friends.Friends;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;

import java.text.DecimalFormat;

public class Players extends Module {
    public Players() {
        super("Players", Category.GUI);
        setDrawn(false);
    }


    Setting x;
    Setting y;
    Setting right;
    Setting customFont;
    String s = "";
    int count;
    DecimalFormat decimalFormat = new DecimalFormat("00.0");
    ChatFormatting cf;

    public void setup(){
        x = new Setting("pX", this, 2, 0, 1000, true);
        y = new Setting("pY", this, 2, 0, 1000, true);
        ManateePlus.getInstance().settingsManager.rSetting(x);
        ManateePlus.getInstance().settingsManager.rSetting(y);
        right = new Setting("pAlignRight", this, false);
        ManateePlus.getInstance().settingsManager.rSetting(right);
        ManateePlus.getInstance().settingsManager.rSetting(customFont = new Setting("pCFont", this, false));
    }

    public void onRender(){
        boolean font = customFont.getValBoolean();
        count = 0;
        mc.world.loadedEntityList.stream()
                .filter(e->e instanceof EntityPlayer)
                .filter(e->e != mc.player)
                .forEach(e->{
                    if(Friends.isFriend(e.getName())) cf = ChatFormatting.AQUA;
                    else if(((EntityPlayer) e).isPotionActive(MobEffects.STRENGTH)) cf = ChatFormatting.RED;
                    else cf = ChatFormatting.GRAY;
                    if((((EntityPlayer) e).getHealth() + ((EntityPlayer) e).getAbsorptionAmount()) <= 5) s = ChatFormatting.RED +" "+ decimalFormat.format((((EntityPlayer) e).getHealth() + ((EntityPlayer) e).getAbsorptionAmount()));
                    if((((EntityPlayer) e).getHealth() + ((EntityPlayer) e).getAbsorptionAmount()) > 5 && (((EntityPlayer) e).getHealth() + ((EntityPlayer) e).getAbsorptionAmount()) <=15) s = ChatFormatting.YELLOW +" "+ decimalFormat.format((((EntityPlayer) e).getHealth() + ((EntityPlayer) e).getAbsorptionAmount()));
                    if((((EntityPlayer) e).getHealth() + ((EntityPlayer) e).getAbsorptionAmount()) >15) s = ChatFormatting.GREEN +" "+ decimalFormat.format((((EntityPlayer) e).getHealth() + ((EntityPlayer) e).getAbsorptionAmount()));
                    if(right.getValBoolean()) {
                            if(font) ManateePlus.fontRenderer.drawStringWithShadow(cf + e.getName() + s, (int) x.getValDouble() - ManateePlus.fontRenderer.getStringWidth(cf + e.getName() + s), (int) y.getValDouble() + count, 0xffffffff);
                            else mc.fontRenderer.drawStringWithShadow(cf + e.getName() + s, (int) x.getValDouble() - mc.fontRenderer.getStringWidth(cf + e.getName() + s), (int) y.getValDouble() + count, 0xffffffff);
                    } else {
                            if(font) ManateePlus.fontRenderer.drawStringWithShadow(cf + e.getName() + s, (int) x.getValDouble(), (int) y.getValDouble() + count, 0xffffffff);
                            else mc.fontRenderer.drawStringWithShadow(cf + e.getName() + s, (int) x.getValDouble(), (int) y.getValDouble() + count, 0xffffffff);
                    }
                    count += 10;
                });
    }
}
