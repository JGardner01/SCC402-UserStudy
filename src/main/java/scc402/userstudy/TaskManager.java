package scc402.userstudy;

public class TaskManager {
    public static final String task1Instructions = "Navigate and enable Bluetooth.";
    public static final String task2Instructions = "";
    private static String taskName = StateManager.getCurrentUI() + " " + StateManager.getCurrentTest();


    public static void startTask(){
        /* NEEDS WORK HERE
        if (StateManager.getCurrentTest() != StateManager.Test.TEST1 && StateManager.getCurrentTest() != StateManager.Test.TEST2){
            StateManager.setCurrentTest(StateManager.Test.TEST1);
        } else if (StateManager.getCurrentTest() == StateManager.Test.TEST1){
            StateManager.setCurrentTest(StateManager.Test.TEST2);
        } else {
            StateManager.setCurrentTest(StateManager.Test.TEST2);
        }
        */

        StateManager.setCurrentMode(StateManager.Mode.TEST);
        taskName = StateManager.getCurrentUI() + " " + StateManager.getCurrentTest();
        System.out.println(taskName);
        ResultsManager.startRecording(taskName);

    }


 //call when task completed - on button click
    public static void endTask(){
        ResultsManager.endRecording(taskName);

        /* NEEDS WORK HERE
        if (StateManager.getCurrentTest() == StateManager.Test.TEST2){
            StateManager.
        }
         */
    }


    //ignore button click function
}
