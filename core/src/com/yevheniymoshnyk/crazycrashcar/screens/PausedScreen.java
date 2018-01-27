package com.yevheniymoshnyk.crazycrashcar.screens;

import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.yevheniymoshnyk.crazycrashcar.CrazyCrashCar;


public class PausedScreen extends Group {

    public static final int ON_RESUME = 1;
    public static final int ON_QUIT = 2;

    private Image title;
    private ImageButton quit, resume;

    private float w, h;

    public PausedScreen(float w, float h) {
        this.w = w;
        this.h = h;

        title = new Image(CrazyCrashCar.atlas.findRegion("paused"));
        title.setX((w - title.getWidth() / 2));
        title.setX(h);
        addActor(title);


    }
}
