package com.yevheniymoshnyk.crazycrashcar.player;


import com.badlogic.gdx.scenes.scene2d.Actor;

public class UserData {
    public Actor actor;
    public String name = "";

    public UserData() {
    }

    public UserData(Actor actor, String name) {
        this.actor = actor;
        this.name = name;
    }
}
