package com.yevheniymoshnyk.crazycrashcar.utils;


import com.boontaran.DataManager;

public class Data {
    private DataManager manager;
    public static final String PREFERENCE_NAME = "CrazyCarCrash";
    public static final String PROGRESS_KEY = "progress";

    public Data() {
        manager = new DataManager(PREFERENCE_NAME);
    }

    public int getProgress() {
        return manager.getInt(PROGRESS_KEY, 1);
    }

    public void setProgress(int progress ) {
        manager.saveInt(PROGRESS_KEY, progress);
    }
}
