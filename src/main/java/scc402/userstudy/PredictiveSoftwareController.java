package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class PredictiveSoftwareController {
    @FXML
    private Button backButton;

    //hidden buttons
    @FXML
    private Button hiddenHardwareButton;
    @FXML
    private Button hiddenAboutButton;

    @FXML
    protected void onHiddenHardwareButtonClick(){
        ResultsManager.incrementClickCount();
        loadScene("predictive-hardware-menu.fxml");
    }

    @FXML
    protected void onBackButtonClick() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-main-menu.fxml");
    }

    @FXML
    protected void onHiddenAboutButtonClick() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-about.fxml");
    }

    @FXML
    protected void onNotificationsButtonClick() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-notifications.fxml");
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
