package scc402.userstudy;

import java.util.HashMap;

public class SystemSettingManager {

    public enum Setting {
        BLUETOOTH,
        WIFI,
        VOLUME
    }

    private static final HashMap<Setting, Object> settings = new HashMap<>();

    //init
    static{
        settings.put(Setting.BLUETOOTH, false);
        settings.put(Setting.WIFI, false);
        settings.put(Setting.VOLUME, 50);
    }

    public static Object getSetting(Setting setting){
        return settings.get(setting);
    }

    public static void toggleSetting(Setting setting){
        boolean currentValue = (Boolean) settings.get(setting);
        settings.put(setting, !currentValue);
    }

    public static void adjustVolume(int newVolume){
        settings.put(Setting.VOLUME, newVolume);
    }

    public static void resetSettings(){
        settings.put(Setting.BLUETOOTH, false);
        settings.put(Setting.WIFI, false);
        settings.put(Setting.VOLUME, 50);
    }
}
