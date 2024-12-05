package scc402.userstudy;

public class StateManager {
    public enum UI{
        STANDARD,
        PREDICTIVE
    }

    public enum Mode{
        DEMO,
        TEST
    }

    public enum Test{
        TEST1,
        TEST2
    }

    /////////////////////////////////////////////////////////////////
    ////CHANGE THIS DEPENDING ON WHAT TEST YOU WANT TO START WITH////
    //////////////////UI.STANDARD OR UI.PREDICTIVE///////////////////
    /////////////////////////////////////////////////////////////////
    private static UI currentUI = UI.STANDARD;
    /////////////////////////////////////////////////////////////////

    private static Mode currentMode = Mode.DEMO;
    private static Test currentTest = Test.TEST1;

    public static UI getCurrentUI(){
        return currentUI;
    }

    public static void setCurrentUI(UI ui){
        currentUI = ui;
    }

    public static Mode getCurrentMode(){
        return currentMode;
    }

    public static void setCurrentMode(Mode mode){
        currentMode = mode;
    }

    public static Test getCurrentTest(){
        return currentTest;
    }

    public static void setCurrentTest(Test test){
        currentTest = test;
    }
}