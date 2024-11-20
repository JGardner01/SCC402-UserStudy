package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PredictiveConnectivityController {

    @FXML
    private Button backButton;

    //hidden buttons
    @FXML
    private Button hiddenMainMenuButton;
    @FXML
    private Button hiddenVolumeButton;

    //disabled buttons
    @FXML
    private Button disabledButton1;
    @FXML
    private Button disabledButton2;

    @FXML
    public void initialize() {
        if (StateManager.getCurrentMode() == StateManager.Mode.TEST) {
            hiddenMainMenuButton.setOpacity(0);
            hiddenVolumeButton.setOpacity(0);
        }
        disabledButton1.setVisible(false);
        disabledButton2.setVisible(false);
    }

    @FXML
    protected void onHiddenMainMenuButton(){
        ResultsManager.recordClick(false);
        loadScene("predictive-main-menu.fxml");

    }

    @FXML
    protected void onWifiButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("predictive-wi-fi.fxml");
    }

    @FXML
    protected void onBluetoothButtonClick() {
        if (!TaskManager.clickNotRegistered(1,1)) {
            ResultsManager.recordClick(false);
            loadScene("predictive-bluetooth.fxml");
        } else {
            ResultsManager.recordClick(true);
        }
    }

    @FXML
    protected void onHiddenVolumeButton(){
        ResultsManager.recordClick(false);
        loadScene("predictive-volume.fxml");

    }

    @FXML
    protected void onBackButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("predictive-hardware-menu.fxml");
    }

    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
