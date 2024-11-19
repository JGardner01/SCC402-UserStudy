package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PredictiveAboutController {
    @FXML
    private Button backButton;

    //hidden buttons
    @FXML
    private Button hiddenHardwareButton;
    @FXML
    private Button hiddenSoftwareButton;

    @FXML
    public void initialize() {
        hiddenHardwareButton.setOpacity(0);
        hiddenSoftwareButton.setOpacity(0);
    }

    @FXML
    protected void onHiddenHardwareButtonClick(){
        ResultsManager.incrementClickCount();
        loadScene("predictive-hardware-menu.fxml");
    }

    @FXML
    protected void onHiddenSoftwareButtonClick(){
        ResultsManager.incrementClickCount();
        loadScene("predictive-software-menu.fxml");
    }

    @FXML
    protected void onBackButtonClick() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-main-menu.fxml");
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
