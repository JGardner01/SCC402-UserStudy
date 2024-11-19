package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PredictiveWiFiController {
    private final String ENABLED = "Status: Enabled";
    private final String DISABLED = "Status: Disabled";

    @FXML
    private Button backButton;
    @FXML
    private Button wifiButton;
    @FXML
    private Text statusText;

    //disabled button
    @FXML
    private Button disabledButton1;
    @FXML
    private Button hiddenBluetoothButton;

    @FXML
    public void initialize() {
        disabledButton1.setVisible(false);

        if (StateManager.getCurrentMode() == StateManager.Mode.TEST) {
            hiddenBluetoothButton.setOpacity(0);
        }

        updateWifiStatus();
    }

    @FXML
    protected void onBackButtonClick() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-connectivity.fxml");
    }

    @FXML
    protected void onHiddenBluetoothButtonClick() {
        ResultsManager.incrementClickCount();
        loadScene("predictive-bluetooth.fxml");
    }


    @FXML
    protected void changeStatus() {
        SystemSettingManager.toggleSetting(SystemSettingManager.Setting.WIFI);
        updateWifiStatus();
        TaskManager.endTask2(backButton);
    }

    private void updateWifiStatus(){
        boolean enabled = (Boolean) SystemSettingManager.getSetting(SystemSettingManager.Setting.WIFI);
        if (enabled){
            statusText.setText(ENABLED);
            wifiButton.setText("Disable Wi-Fi");
        } else {
            statusText.setText(DISABLED);
            wifiButton.setText("Enable Wi-Fi");
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
