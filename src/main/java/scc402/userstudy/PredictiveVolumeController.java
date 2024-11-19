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
    private Button hiddenHardwareButton;
    @FXML
    private Button disabledButton1;
    @FXML
    private Button disabledButton2;
    @FXML
    private Button hiddenConnectivityButton;

    @FXML
    public void initialize() {
        updateVolumeStatus();
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            volumeText.setText("Volume: " + round(newValue.intValue()));
            SystemSettingManager.adjustVolume(newValue.intValue());
        });
    }

    @FXML
    protected void onHiddenHardwareButton() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-hardware-menu.fxml");
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
