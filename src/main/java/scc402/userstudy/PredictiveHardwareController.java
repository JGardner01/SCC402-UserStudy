package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class PredictiveHardwareController {
    @FXML
    private Button backButton;

    //hidden buttons
    @FXML
    private Button hiddenSoftwareButton;
    @FXML
    private Button hiddenAboutButton;

    //disabled buttons
    @FXML
    private Button disabledButton1;
    @FXML
    private Button disabledButton2;

    @FXML
    public void initialize(){
        if (StateManager.getCurrentMode() == StateManager.Mode.TEST) {
            hiddenSoftwareButton.setOpacity(0);
            hiddenAboutButton.setOpacity(0);
        }
        disabledButton1.setVisible(false);
        disabledButton2.setVisible(false);
    }

    @FXML
    protected void onBackButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("predictive-main-menu.fxml");
    }

    @FXML
    protected void onHiddenSoftwareButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("predictive-software-menu.fxml");
    }

    @FXML
    protected void onHiddenAboutButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("predictive-about.fxml");
    }

    @FXML
    protected void onSoundButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("predictive-volume.fxml");
    }

    @FXML
    protected void onConnectivityButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("predictive-connectivity.fxml");
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
