package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestIntroductionController {
    public final String task1Instructions = "Navigate and enable Bluetooth.";
    public final String task2Instructions = "Adjust the volume to 100.";

    @FXML
    private Text nameText;
    @FXML
    private Text instructionsText;
    @FXML
    private Button startTaskButton;

    @FXML
    public void initialize(){
        if (StateManager.getCurrentTest() == StateManager.Test.Test1){
            nameText.setText("Task 1 of 2");
            instructionsText.setText(task1Instructions);
        } else if (StateManager.getCurrentTest() == StateManager.Test.Test2) {
            nameText.setText("Task 2 of 2");
            instructionsText.setText(task2Instructions);
        }
    }

    @FXML protected void onStartTaskButton(){
        //start timer in results class

        //close window
        Stage stage = (Stage) startTaskButton.getScene().getWindow();
        stage.close();
    }
}
