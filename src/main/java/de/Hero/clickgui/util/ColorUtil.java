package de.Hero.clickgui.util;

import java.awt.Color;

import me.manatee.plus.ManateePlus;
import me.manatee.plus.util.Rainbow;

/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit me
 *
 *  @author HeroCode
 */
public class ColorUtil {
	
	public static Color getClickGUIColor(){
		if(ManateePlus.getInstance().settingsManager.getSettingByName("GuiRainbow").getValBoolean()) {
			return Rainbow.getColor();
		}else
			return new Color((int) ManateePlus.getInstance().settingsManager.getSettingByName("GuiRed").getValDouble(), (int) ManateePlus.getInstance().settingsManager.getSettingByName("GuiGreen").getValDouble(), (int) ManateePlus.getInstance().settingsManager.getSettingByName("GuiBlue").getValDouble());
	}
}
