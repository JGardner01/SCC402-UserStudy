package scc402.userstudy;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;

public class TaskManager {

    //TODO Task between click and on which click the error occurred

    public enum RunSlow{
        NORMAL, //work as normal
        SLOW,   //act slow dont process
        BACK    //send back to target page
    }

    public static final String task1Instructions = "Navigate the interface and toggle Bluetooth.";
    public static final String task2Instructions = "Navigate the interface and toggle Wi-Fi";
    private static String taskName = StateManager.getCurrentUI() + " " + StateManager.getCurrentTest();

    public static int completedTasks = 0;

    //task tracking variables
    private static int [] ignoreButtonCounts = {0, 0}; //TASK 1 - 2 buttons not registering
    private static int runslowCount = 0; //TASK 2 - system running slow
    private static boolean disabled = true; //TASK 2 - used for disabling invisible buttons on predictive UI

    public static boolean clickNotRegistered(int index, int threshold){
        if (StateManager.getCurrentTest() == StateManager.Test.TEST1 && StateManager.getCurrentMode() == StateManager.Mode.TEST) {
            ignoreButtonCounts[index]++;
            if (ignoreButtonCounts[index] <= threshold) {
                System.out.println("Click ignored");
                return true;
            } else {
                System.out.println("Click processed");
                return false;
            }
        } else {
            return false;
        }
    }

    //running slow function
    //return number depending on mode
    public static RunSlow runningSlow(int threshold){
        if (StateManager.getCurrentTest() == StateManager.Test.TEST2 && StateManager.getCurrentMode() == StateManager.Mode.TEST) {
            runslowCount++;
            if (runslowCount < threshold + 1) {
                System.out.println("Running Slow");
                disabled = true;
                return RunSlow.SLOW;
            } else if (runslowCount == threshold + 1) {
                System.out.println("Running Normal Again Send To Relevant Page");
                disabled = false;
                return RunSlow.BACK;
            }else {
                disabled = false;
                System.out.println("Processed");
                return RunSlow.NORMAL;
            }
        } else{
            disabled = false;
            return RunSlow.NORMAL;
        }
    }


    public static void endTask1(Button backButton){
        if (StateManager.getCurrentTest() == StateManager.Test.TEST1 && StateManager.getCurrentMode() == StateManager.Mode.TEST) {
            //reset task variables
            Arrays.fill(ignoreButtonCounts, 0);

            endTask(backButton);
        }
    }

    public static void endTask2(Button backButton){
        if (StateManager.getCurrentTest() == StateManager.Test.TEST2 && StateManager.getCurrentMode() == StateManager.Mode.TEST) {
            //reset task variable
            runslowCount = 0;

            endTask(backButton);
        }
    }

    public static String getTaskName(){
        return taskName;
    }

    public static boolean getDisabledButton(){
        return disabled;
    }

    public static int getCompletedTasks(){
        return completedTasks;
    }

    public static void startTask(){
        StateManager.setCurrentMode(StateManager.Mode.TEST);
        taskName = StateManager.getCurrentUI() + " " + StateManager.getCurrentTest();
        System.out.println(taskName);
        ResultsManager.startRecording();

    }

 //call when task completed - on button click
    public static void endTask(Button backButton){
        ResultsManager.endRecording(taskName);

        completedTasks++;

        for (int i = 0; i < 4; i++){
            if (ResultsManager.pastTasks[i].equals("")){
                ResultsManager.pastTasks[i] = taskName;
                break;
            }
        }

        //check if final ui test is complete -> end testing and present finish screen
        if (StateManager.getCurrentMode() == StateManager.Mode.TEST && StateManager.getCurrentTest() == StateManager.Test.TEST2 && completedTasks >= 4) {
            //end testing -> dont route
            //export results
            System.out.println("Testing completed");
            ResultsManager.exportResults();

            try {
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try{
                FXMLLoader fxmlLoader = new FXMLLoader(TaskManager.class.getResource("ending-user-study.fxml"));
                Scene taskCompleteScene = new Scene(fxmlLoader.load());

                Stage taskCompleteStage = new Stage();
                taskCompleteStage.setTitle("User Study Complete");

                taskCompleteStage.setScene(taskCompleteScene);
                taskCompleteStage.showAndWait();

            } catch (Exception e){
                e.printStackTrace();
            }

        }
        //check if test 1 is complete -> change to test 2
        else if (StateManager.getCurrentTest() == StateManager.Test.TEST1){
            //change test
            StateManager.setCurrentTest(StateManager.Test.TEST2);
            System.out.println("Changed to test 2");

            //reset variables
            SystemSettingManager.resetSettings();
            //keep consistent for user -> enable bluetooth as the same as last task
            SystemSettingManager.toggleSetting(SystemSettingManager.Setting.BLUETOOTH);

            //load ui again - On bluetooth page
            if (StateManager.getCurrentUI() == StateManager.UI.STANDARD) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(TaskManager.class.getResource("standard-bluetooth.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    stage.setScene(scene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(TaskManager.class.getResource("predictive-bluetooth.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    stage.setScene(scene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //load completed task scene
            displayTaskComplete();
        }
        //check if test 2 is completed -> change to other UI
        else if (StateManager.getCurrentTest() == StateManager.Test.TEST2 && completedTasks < 4){
            StateManager.setCurrentMode(StateManager.Mode.DEMO);
            StateManager.setCurrentTest(StateManager.Test.TEST1);

            if (StateManager.getCurrentUI() == StateManager.UI.STANDARD) {
                StateManager.setCurrentUI(StateManager.UI.PREDICTIVE);
            } else {
                StateManager.setCurrentUI((StateManager.UI.STANDARD));
            }
            disabled = true;

            //reset variables
            SystemSettingManager.resetSettings();

            //load completed task scene
            displayTaskComplete();

            //open start screen
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(TaskManager.class.getResource("start-scene.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void displayTaskComplete(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(TaskManager.class.getResource("task-complete.fxml"));
            Scene taskCompleteScene = new Scene(fxmlLoader.load());

            Stage taskCompleteStage = new Stage();
            taskCompleteStage.setTitle("Task Complete");
            taskCompleteStage.initModality(Modality.APPLICATION_MODAL);

            taskCompleteStage.setOnCloseRequest(event -> {
                event.consume(); // Prevents the window from closing
            });

            taskCompleteStage.setScene(taskCompleteScene);
            taskCompleteStage.showAndWait();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
