package com.yevheniymoshnyk.crazycrashcar.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.boontaran.games.StageGame;
import com.yevheniymoshnyk.crazycrashcar.CrazyCrashCar;
import com.yevheniymoshnyk.crazycrashcar.media.LevelIcon;
import com.yevheniymoshnyk.crazycrashcar.utils.Setting;


public class LevelList extends StageGame {

    public static final int ON_BACK = 1;
    public static final int ON_LEVEL_SELECTED = 2;
    public static final int ON_OPEN_MARKET = 3;
    public static final int ON_SHARE = 4;

    private Group container;
    private int selectedLevelId = 0;

    public LevelList() {
        Image bg = new Image(CrazyCrashCar.atlas.findRegion("intro_bg"));
        addBackground(bg, true, false);

        container = new Group();
        addChild(container);

        int row = 4, col = 4;
        float space = 20;

        float iconWidht = 0, iconHeight = 0;
        int id = 1;
        int x, y;

        int progress = CrazyCrashCar.data.getProgress();

        for (y = 0; y < row; y++) {
            for (x = 0; x < col; x++) {
                LevelIcon icon = new LevelIcon(id);
                container.addActor(icon);

                if (iconWidht == 0) {
                    iconWidht = icon.getWidth();
                    iconHeight = icon.getHeight();
                }

                icon.setX(x * (iconWidht + space));
                icon.setY(((row - 1) - y) * (iconHeight + space));

                if (id <= progress) {
                    icon.setHilite();
                }
                if (Setting.DEBUG_GAME) {
                    icon.setLock(false);
                }

                icon.addListener(iconListener);
                id++;
            }
        }

        container.setWidth(col * iconWidht + (col - 1) * space);
        container.setHeight(row * iconHeight + (row - 1) * space);

        container.setX(30);
        container.setY(getHeight() - container.getHeight() - 30);

        container.setColor(1, 1, 1, 0);
        container.addAction(Actions.alpha(1, 0.4f));
    }
}
