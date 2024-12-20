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

    //disabled buttons
    @FXML
    private Button disabledButton1;
    @FXML
    private Button disabledButton2;
    @FXML
    private Button disabledButton3;
    @FXML
    private Button disabledButton4;

    @FXML
    public void initialize() {
        if (StateManager.getCurrentMode() == StateManager.Mode.TEST){
            hiddenHardwareButton.setOpacity(0);
            hiddenSoftwareButton.setOpacity(0);
        }
        disabledButton1.setVisible(false);
        disabledButton2.setVisible(false);
        disabledButton3.setVisible(false);
        disabledButton4.setVisible(false);
    }

    @FXML
    protected void onHiddenHardwareButtonClick(){
        ResultsManager.recordClick(false);
        loadScene("predictive-hardware-menu.fxml");
    }

    @FXML
    protected void onHiddenSoftwareButtonClick(){
        ResultsManager.recordClick(false);
        loadScene("predictive-software-menu.fxml");
    }

    @FXML
    protected void onBackButtonClick() {
        ResultsManager.recordClick(false);
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
