package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class StandardMainMenuController {
    @FXML
    private Button hardwareButton;

    @FXML
    protected void onHardwareButtonClick() {
        loadScene("standard-hardware-menu.fxml");
    }

    @FXML
    protected void onSoftwareButtonClick() {
        loadScene("standard-software-menu.fxml");
    }

    @FXML
    protected void onAboutButtonClick() {
        loadScene("standard-about-page.fxml");
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
