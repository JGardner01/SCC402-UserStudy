package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TaskManager {
    public static final String task1Instructions = "Navigate and enable Bluetooth.";
    public static final String task2Instructions = "Test 2 Instructions";
    private static String taskName = StateManager.getCurrentUI() + " " + StateManager.getCurrentTest();

    //task tracking variables
    private static int [] ignoreButtonCounts = {0, 0, 0}; //TASK 1 not sure how many we need
    //private static int [] //TASK 2

    public static boolean clickNotRegistered(int index, int threshold){
        if (StateManager.getCurrentTest() == StateManager.Test.TEST1) {
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
    //public static boolean


    public static void endTask1(Button backButton){
        if (StateManager.getCurrentTest() == StateManager.Test.TEST1){
            endTask(backButton);
        }
    }

    public static void endTask2(Button backButton){
        if (StateManager.getCurrentTest() == StateManager.Test.TEST2){
            endTask(backButton);
        }
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

        //check if final predictive ui test is complete -> end testing and present finish screen
        if (StateManager.getCurrentUI() == StateManager.UI.PREDICTIVE && StateManager.getCurrentMode() == StateManager.Mode.TEST && StateManager.getCurrentTest() == StateManager.Test.TEST2) {
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

            //reset variables
            SystemSettingManager.resetSettings();

            //load ui again
            if (StateManager.getCurrentUI() == StateManager.UI.STANDARD) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(TaskManager.class.getResource("standard-main-menu.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    stage.setScene(scene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(TaskManager.class.getResource("predictive-main-menu.fxml"));
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
        //check if test 2 of standard ui is completed -> change to predictive UI
        else if (StateManager.getCurrentUI() == StateManager.UI.STANDARD && StateManager.getCurrentTest() == StateManager.Test.TEST2){
            StateManager.setCurrentMode(StateManager.Mode.DEMO);
            StateManager.setCurrentUI(StateManager.UI.PREDICTIVE);

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
