package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class StandardMainMenuController {
    @FXML
    private Button hardwareButton;
    @FXML
    private Button startTestButton;

    @FXML
    public void initialize(){
        if (StateManager.getCurrentMode() == StateManager.Mode.DEMO){
            startTestButton.setVisible(true);
        } else{
            startTestButton.setVisible(false);
        }
    }

    @FXML
    protected void onHardwareButtonClick() {
        if (!TaskManager.clickNotRegistered(0, 1)){
            ResultsManager.recordClick(false);
            loadScene("standard-hardware-menu.fxml");
        } else {
            ResultsManager.recordClick(true);
        }
    }

    @FXML
    protected void onSoftwareButtonClick() {
        if (!TaskManager.clickNotRegistered(0, 1)){
            ResultsManager.recordClick(false);
            loadScene("standard-software-menu.fxml");
        } else {
            ResultsManager.recordClick(true);
        }
    }

    @FXML
    protected void onAboutButtonClick() {
        if (!TaskManager.clickNotRegistered(0, 1)){
            ResultsManager.recordClick(false);
            loadScene("standard-about.fxml");
        } else {
            ResultsManager.recordClick(true);
        }
    }

    @FXML
    protected void onStartTestButtonClick(){
        ResultsManager.endDemoRecording();
        StateManager.setCurrentMode(StateManager.Mode.TEST);
        StateManager.setCurrentTest(StateManager.Test.TEST1);
        loadScene("standard-main-menu.fxml");

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("test-introduction.fxml"));
            Scene testIntroScene = new Scene(fxmlLoader.load());

            Stage testIntroStage = new Stage();
            testIntroStage.setTitle("Test Instructions");
            testIntroStage.initModality(Modality.APPLICATION_MODAL);

            testIntroStage.setOnCloseRequest(event -> {
                event.consume(); // Prevents the window from closing
            });

            testIntroStage.setScene(testIntroScene);
            testIntroStage.showAndWait();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) hardwareButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
