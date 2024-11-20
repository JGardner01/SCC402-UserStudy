package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.lang.Math.round;

public class PredictiveVolumeController {
    @FXML
    private Button backButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Text volumeText;

    //hidden and disabled buttons
    @FXML
    private Button hiddenMainMenuButton;
    @FXML
    private Button disabledButton1;
    @FXML
    private Button disabledButton2;
    @FXML
    private Button hiddenConnectivityButton;
    @FXML
    private Button disabledButton3;
    @FXML
    private Button disabledButton4;

    @FXML
    public void initialize() {
        disabledButton1.setVisible(false);
        disabledButton2.setVisible(false);
        disabledButton3.setVisible(false);
        disabledButton4.setVisible(false);

        if (StateManager.getCurrentMode() == StateManager.Mode.TEST) {
            hiddenMainMenuButton.setOpacity(0);
            hiddenConnectivityButton.setOpacity(0);
        }

        updateVolumeStatus();
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            volumeText.setText("Volume: " + round(newValue.intValue()));
            SystemSettingManager.adjustVolume(newValue.intValue());
        });
    }

    @FXML
    protected void onHiddenMainMenuButton() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-main-menu.fxml");
    }

    @FXML
    protected void onBackButtonClick() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-hardware-menu.fxml");
    }

    @FXML
    protected void onHiddenConnectivityButton() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-connectivity.fxml");
    }

    private void updateVolumeStatus(){
        double volume = ((Number) SystemSettingManager.getSetting(SystemSettingManager.Setting.VOLUME)).doubleValue();
        volumeSlider.setValue(volume);
        volumeText.setText("Volume: " + round(volume));

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
