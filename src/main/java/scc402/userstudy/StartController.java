package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartController {
    @FXML
    private Button startButton;

    @FXML
    private Text introText;

    @FXML
    public void initialize() {
        if (StateManager.getCurrentUI() == StateManager.UI.STANDARD){
            introText.setText("Part 1: Standard User Interface");
            startButton.setText("Begin Standard UI Demo");
        } else {
            introText.setText("Part 2: Predictive User Interface");
            startButton.setText("Begin Predictive UI Demo");
        }
    }

    @FXML
    protected void onStartButtonClick() {
        if (StateManager.getCurrentUI() == StateManager.UI.STANDARD) {
            System.out.println("Starting Standard User Study");
            StateManager.setCurrentUI(StateManager.UI.STANDARD);
            StateManager.setCurrentMode(StateManager.Mode.DEMO);
            SystemSettingManager.resetSettings();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("standard-main-menu.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) startButton.getScene().getWindow();
                stage.setScene(scene);


            } catch (Exception e) {
                System.out.println("Exception: ");
                e.printStackTrace();
            }
        } else {
            System.out.println("Starting Predictive User Study");
            StateManager.setCurrentUI(StateManager.UI.PREDICTIVE);
            StateManager.setCurrentMode(StateManager.Mode.DEMO);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("predictive-main-menu.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) startButton.getScene().getWindow();
                stage.setScene(scene);

            } catch (Exception e) {
                System.out.println("Exception: ");
                e.printStackTrace();
            }
        }

        ResultsManager.startDemoRecording();

    }
}