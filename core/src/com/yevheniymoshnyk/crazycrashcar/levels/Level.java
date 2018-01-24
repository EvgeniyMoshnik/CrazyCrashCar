package com.yevheniymoshnyk.crazycrashcar.levels;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.boontaran.games.StageGame;
import com.yevheniymoshnyk.crazycrashcar.CrazyCrashCar;
import com.yevheniymoshnyk.crazycrashcar.controls.CButton;
import com.yevheniymoshnyk.crazycrashcar.controls.JoyStick;
import com.yevheniymoshnyk.crazycrashcar.player.Player;
import com.yevheniymoshnyk.crazycrashcar.utils.Setting;


public class Level extends StageGame {

    private String directory;

    public static final float WORLD_SCALE = 30;

    public static final int ON_RESTART = 1;
    public static final int ON_QUIT = 2;
    public static final int ON_COMPLETED = 3;
    public static final int ON_FAILED = 4;
    public static final int ON_PAUSED = 5;
    public static final int ON_RESUME = 6;

    public static final int PLAY = 1;
    public static final int LEVEL_FAILED = 2;
    public static final int LEVEL_COMPLETED = 3;
    public static final int PAUSED = 4;

    private int state = 1;

    private int mapWidth, mapHeight, tilePixelWidth, mapPixelHeight, levelWidth, levelHeight;

    private Player player;
    private Body finish;

    private boolean moveFrontKey, moveBackKey;
    private Image pleaseWait;

    private JoyStick  joyStick;
    private CButton jumpBackBtn, jumpForwardBtn;

    private String musicName;
    private boolean musicHasLoaded;

    private String customBackground = null;

    public static final float LAND_RESTITUTION = 0.5f;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Array<Body> bodies = new Array<Body>();

    private boolean hasBeenBuilt = false;

    private TiledMap map;

    public Level(String directory) {
        this.directory = directory;

        pleaseWait = new Image(CrazyCrashCar.atlas.findRegion("please_wait"));
        addOverlayChild(pleaseWait);
        centerActorXY(pleaseWait);

        delayCall("build_level", 0.2f);
    }

    @Override
    protected void onDelayCall(String code) {
        if (code.equals("build_level")) {
            build();
        } else {
            if (code.equals("resumeLevel2")) {
                resumeLevel2();
            }
        }
        
    }

    private void setBackGround(String region) {
        clearBackground();
        Image bg = new Image(CrazyCrashCar.atlas.findRegion(region));
        addBackground(bg, true, false);
    }

    private void resumeLevel2() {
    }

    private void build() {
        hasBeenBuilt = true;

        world = new World(new Vector2(0, Setting.GRAVITY), true);
        world.setContactListener(contactListener);
        debugRenderer = new Box2DDebugRenderer();

        //loadMap();

        if (player == null) {
            throw new Error("player not defined");
        }
        if (finish == null) {
           throw new Error("finish not defined");
        }

        //addRectangleLand();

        int count = 60;
        while (count-- > 0) {
            world.step(1f / 60, 10, 10);

            joyStick = new JoyStick(mmToPx(10));
            addOverlayChild(joyStick);
            joyStick.setPosition(15, 15);

            jumpBackBtn = new CButton(
                    new Image(CrazyCrashCar.atlas.findRegion("jump1")),
                    new Image(CrazyCrashCar.atlas.findRegion("jump1_down")),
                    mmToPx(10)
            );

            addOverlayChild(jumpBackBtn);

            jumpForwardBtn = new CButton(
                    new Image(CrazyCrashCar.atlas.findRegion("jump2")),
                    new Image(CrazyCrashCar.atlas.findRegion("jump2_down")),
                    mmToPx(10)
            );

            addOverlayChild(jumpForwardBtn);

            jumpForwardBtn.setPosition(getWidth() - jumpForwardBtn.getWidth() - 15, 15);
            jumpBackBtn.setPosition(jumpForwardBtn.getX() - jumpBackBtn.getWidth() - 15, 15);

            jumpBackBtn.addListener(new ClickListener() {

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (state == PLAY) {
                        if (player.touchedGround()) {
                            return true;
                        }
                    }

                    return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);
                }
            });
        }
    }

    public void addChild(Actor actor) {
        this.stage.addActor(actor);
    }

    public void addChild(Actor actor, float x, float y) {
        this.addChild(actor);
        actor.setX(x);
        actor.setY(y);

    }
}
