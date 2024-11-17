package scc402.userstudy;

public class StateManager {
    public enum Mode{
        DEMO,
        TEST
    }

    public enum Test{
        Test1,
        Test2
    }

    private static Mode currentMode;
    private static Test currentTest;


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