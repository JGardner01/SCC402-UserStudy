package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StandardWiFiController {
    private final String ENABLED = "Status: Enabled";
    private final String DISABLED = "Status: Disabled";

    private boolean enabled;

    @FXML
    private Button backButton;
    @FXML
    private Button wifiButton;
    @FXML
    private Text statusText;

    @FXML
    protected void onBackButtonClick() {
        loadScene("standard-connectivity.fxml");
    }

    @FXML
    public void initialize() {
        updateWifiStatus();
    }

    @FXML
    protected void changeStatus() {
        SystemSettingManager.toggleSetting(SystemSettingManager.Setting.WIFI);
        updateWifiStatus();
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
