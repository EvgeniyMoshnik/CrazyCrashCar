package com.yevheniymoshnyk.crazycrashcar.controls;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.yevheniymoshnyk.crazycrashcar.CrazyCrashCar;


public class JumpGauge extends Group {

    private Array<Image> offs, ons;
    private final float Time = 0.5f;
    private float time = 0;
    private boolean counting = false;

    public JumpGauge() {
        setTouchable(Touchable.disabled);
        Image box;

        offs = new Array<Image>();
        ons = new Array<Image>();
        NinePatch patch = new NinePatch(CrazyCrashCar.atlas.findRegion("jump_gauge_off"), 3, 3, 3, 3);
    }
}
