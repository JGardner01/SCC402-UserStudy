package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestIntroductionController {
    @FXML
    private Text nameText;
    @FXML
    private Text instructionsText;
    @FXML
    private Button startTaskButton;

    @FXML
    public void initialize(){
        if (StateManager.getCurrentTest() == StateManager.Test.TEST1){
            nameText.setText("Task 1 of 2");
            instructionsText.setText(TaskManager.task1Instructions);
        } else if (StateManager.getCurrentTest() == StateManager.Test.TEST2) {
            nameText.setText("Task 2 of 2");
            instructionsText.setText(TaskManager.task2Instructions);
        }
    }

    @FXML protected void onStartTaskButton(){
        //start timer in results class
        TaskManager.startTask();
        //close window
        Stage stage = (Stage) startTaskButton.getScene().getWindow();
        stage.close();
    }
}
