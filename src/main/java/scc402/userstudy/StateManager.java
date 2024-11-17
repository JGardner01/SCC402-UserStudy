package scc402.userstudy;

public class StateManager {
    public enum Mode{
        DEMO,
        TEST
    }

    private static Mode currentMode = Mode.DEMO;

    public static Mode getCurrentMode(){
        return currentMode;
    }

    public static void setCurrentMode(Mode mode){
        currentMode = mode;
    }
}