package com.yevheniymoshnyk.crazycrashcar.player;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.boontaran.games.ActorClip;
import com.yevheniymoshnyk.crazycrashcar.CrazyCrashCar;
import com.yevheniymoshnyk.crazycrashcar.levels.Level;
import com.yevheniymoshnyk.crazycrashcar.utils.Setting;


public class Player extends ActorClip implements IBody {

    private Image carImgage, driverImage, driverFallImage;

    private Group frontWheelCont, rearWheelContr, driverFallCont;

    public Body car, frontWheel, rearWheel, driver;

    private Joint frontWheelJoint, rearWheelJoint, driverWheelJoint;

    private World world;

    private boolean hasDestroyed = false;
    private boolean destroyOnNextUpdate = false;

    private boolean isTouchGround = true;

    private float jumpImpulse = Setting.JUMP_IMPULS;
    private float jumpWait = 0;

    private Level level;

    public Player(Level level) {
        this.level = level;

        carImgage = new Image(CrazyCrashCar.atlas.findRegion("rover"));
        childs.addActor(carImgage);
        carImgage.setX(-carImgage.getWidth()/2);
        carImgage.setY(-15);

        driverImage = new Image(CrazyCrashCar.atlas.findRegion("astronaut"));
        childs.addActor(driverImage);
        driverImage.setX(-35);
        driverImage.setY(28);

        driverFallCont = new Group();

        driverFallImage = new Image(CrazyCrashCar.atlas.findRegion("astronaut_fall"));
        driverFallCont.addActor(driverFallImage);

        driverFallImage.setX(-driverFallImage.getWidth()/2);
        driverFallImage.setY(-driverFallImage.getHeight()/2);



    }









    @Override
    public Body createBody(World world) {
        return null;
    }
}
