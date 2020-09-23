package me.manatee.plus.hud.components;

import de.Hero.clickgui.ClickGUI;
import de.Hero.clickgui.Panel;
import de.Hero.clickgui.util.ColorUtil;
import de.Hero.clickgui.util.FontUtil;
import me.manatee.plus.ManateePlus;
import me.manatee.plus.module.ModuleManager;
import me.manatee.plus.module.modules.gui.Ping;
import me.manatee.plus.util.Rainbow;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.text.DecimalFormat;

public class PingComponent extends Panel {
    public PingComponent(double ix, double iy, ClickGUI parent) {
        super("Ping", ix, iy, 10, 10, false, parent);
        this.isHudComponent = true;

    }



    Ping mod = ((Ping) ModuleManager.getModuleByName("Ping"));

    Color c;
    boolean font;
    Color text;
    Color color;
    DecimalFormat decimalFormat = new DecimalFormat("##.#");


    public void drawHud(){
        doStuff();
        String ping = mod.getPing() + "ms";
        if(font) ManateePlus.fontRenderer.drawStringWithShadow(ping, (float)x, (float)y, text.getRGB());
        else mc.fontRenderer.drawStringWithShadow(ping, (float)x, (float)y, text.getRGB());
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        doStuff();
        String ping = mod.getPing() + "ms";
        double w = mc.fontRenderer.getStringWidth(ping) + 2;
        c = new Color(50, 50, 50, 100);
        if(isHudComponentPinned) c = new Color(ColorUtil.getClickGUIColor().darker().getRed(), ColorUtil.getClickGUIColor().darker().getGreen(), ColorUtil.getClickGUIColor().darker().getBlue(), 100);
        if (this.dragging) {
            x = x2 + mouseX;
            y = y2 + mouseY;
        }
        this.width = w;
        this.height = FontUtil.getFontHeight() + 2;
        Gui.drawRect((int)x, (int)y, (int)x + (int)width, (int)y + (int)height, c.getRGB());
        FontUtil.drawStringWithShadow(title, x, y + height / 2 - FontUtil.getFontHeight()/2f, 0xffffffff);

        if(extended) {
            double startY = y + height;
            Gui.drawRect((int) x, (int) startY, (int) x + (int) width, (int) startY + (int) height, c.getRGB());
            if (font) ManateePlus.fontRenderer.drawStringWithShadow(ping, (float) x, (float) startY, text.getRGB());
            else mc.fontRenderer.drawStringWithShadow(ping, (float) x, (float) startY, text.getRGB());
        }
    }

    private void doStuff() {
        color = new Color(mod.red.getValInt(), mod.green.getValInt(), mod.blue.getValInt());
        text = mod.rainbow.getValBoolean() ? Rainbow.getColor() : color;
        font = mod.customFont.getValBoolean();
    }
}
