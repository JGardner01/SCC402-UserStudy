package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class StandardHardwareController {
    @FXML
    private Button backButton;

    @FXML
    protected void onBackButtonClick() {
        loadScene("standard-main-menu.fxml");
    }

    @FXML
    protected void onSoundButtonClick() {
        loadScene("standard-sound-menu.fxml");
    }

    @FXML
    protected void onConnectivityButtonClick() {
        loadScene("standard-connectivity-page.fxml");
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