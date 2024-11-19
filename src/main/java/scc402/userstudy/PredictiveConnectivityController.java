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
    private Button hiddenHardwareButton;
    @FXML
    private Button hiddenVolumeButton;

    @FXML
    public void initialize() {
        if (StateManager.getCurrentMode() == StateManager.Mode.TEST) {
            hiddenHardwareButton.setOpacity(0);
            hiddenVolumeButton.setOpacity(0);
        }
    }

    @FXML
    protected void onHiddenHardwareButton(){
        ResultsManager.incrementClickCount();
        loadScene("predictive-hardware-menu.fxml");

    }

    @FXML
    protected void onWifiButtonClick() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-wi-fi.fxml");
    }

    @FXML
    protected void onBluetoothButtonClick() {
        ResultsManager.incrementClickCount();
        if (!TaskManager.clickNotRegistered(1,1)) {
            loadScene("predictive-bluetooth.fxml");
        }
    }

    @FXML
    protected void onHiddenVolumeButton(){
        ResultsManager.incrementClickCount();
        loadScene("predictive-volume.fxml");

    }

    @FXML
    protected void onBackButtonClick() {
        ResultsManager.incrementClickCount();
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
