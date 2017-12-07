package com.yevheniymoshnyk.crazycrashcar.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.boontaran.games.ActorClip;
import com.yevheniymoshnyk.crazycrashcar.CrazyCrashCar;
import com.yevheniymoshnyk.crazycrashcar.levels.Level;
import com.yevheniymoshnyk.crazycrashcar.utils.Setting;


public class Player extends ActorClip implements IBody {

    private Image carImgage, driverImage, driverFallImage, frontWheelImage, rearWheelImage;

    private Group frontWheelCont, rearWheelCont, driverFallCont;

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

    public void touchGround() {
        isTouchGround = true;
    }

    public boolean touchedGround() {
        if (jumpWait > 0) return false;
        return isTouchGround;
    }
    @Override
    public Body createBody(World world) {
        this.world = world;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.linearDamping = 0;

        frontWheelCont = new Group();
        frontWheelImage = new Image(CrazyCrashCar.atlas.findRegion("front_wheel"));

        frontWheelCont.addActor(frontWheelImage);
        frontWheelImage.setX(-frontWheelImage.getWidth()/2);
        frontWheelImage.setY(-frontWheelImage.getHeight()/2);

        getParent().addActor(frontWheelCont);

        UserData data = new UserData();
        data.actor = frontWheelCont;
        frontWheel.setUserData(data);

        RevoluteJointDef rDef = new RevoluteJointDef();
        rDef.initialize(car, frontWheel, new Vector2(frontWheel.getPosition()));
        frontWheelJoint = world.createJoint(rDef);

        rearWheelCont = new Group();
        rearWheelImage = new Image(CrazyCrashCar.atlas.findRegion("rear_wheel"));

        rearWheelCont.addActor(rearWheelImage);
        rearWheelImage.setX(-rearWheelImage.getWidth()/2);
        rearWheelImage.setY(-rearWheelImage.getHeight()/2);

        getParent().addActor(rearWheelCont);

        data = new UserData();
        data.actor = rearWheelCont;
        rearWheel.setUserData(data);

        rDef.initialize(car, rearWheel, new Vector2(rearWheel.getPosition()));
        rearWheelJoint = world.createJoint(rDef);


        return car;
    }

    private Body createWheel(World world, float rad){

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.linearDamping = 0;
        def.angularDamping = 1f;

        Body body = world.createBody(def);
        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(rad);

        fDef.shape = shape;
        fDef.restitution = 0.5f;
        fDef.friction = 0.4f;
        fDef.density = 1;

        body.createFixture(fDef);
        shape.dispose();


        return body;
    }
}
