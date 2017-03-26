package com.mygdx.controler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class AnalogStick extends Touchpad {

    private static Touchpad.TouchpadStyle touchpadStyle;
    private static Skin touchpadSkin;
    private static Drawable touchBackground;
    private static Drawable touchKnob;

    public AnalogStick(){

        super(1, getTouchpadStyle());
        setBounds(15, 15, 250, 250);
      
    }

    private static Touchpad.TouchpadStyle getTouchpadStyle() {

        touchpadSkin = new Skin();
       
        Texture touchBackgroundTex = new Texture("background.png");
        Texture touchKnobTex = new Texture("knob.png");
        
       // touchBackgroundTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
       // touchKnobTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        touchpadSkin.add("touchBackground", touchBackgroundTex);
        touchpadSkin.add("touchKnob", touchKnobTex);
//        
//        touchpadSkin.getDrawable("touchBackground").setMinHeight(10);
//        touchpadSkin.getDrawable("touchBackground").setMinWidth(10);
//        
        touchpadSkin.getDrawable("touchKnob").setMinHeight(100);
        touchpadSkin.getDrawable("touchKnob").setMinWidth(100);
        
        
        
        touchpadStyle = new Touchpad.TouchpadStyle();

        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        return touchpadStyle;
    }
}