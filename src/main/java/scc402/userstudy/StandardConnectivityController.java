package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StandardConnectivityController {

    @FXML
    private Button backButton;

    @FXML
    protected void onBackButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("standard-hardware-menu.fxml");
    }

    @FXML
    protected void onWifiButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("standard-wi-fi.fxml");
    }

    @FXML
    protected void onBluetoothButtonClick() {
        if (!TaskManager.clickNotRegistered(1,1)) {
            ResultsManager.recordClick(false);
            loadScene("standard-bluetooth.fxml");
        } else{
            ResultsManager.recordClick(true);
        }
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
