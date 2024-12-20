package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StandardBluetoothController {
    private final String ENABLED = "Status: Enabled";
    private final String DISABLED = "Status: Disabled";

    @FXML
    private Button backButton;
    @FXML
    private Button bluetoothButton;
    @FXML
    private Text statusText;

    @FXML
    protected void onBackButtonClick() {
        TaskManager.RunSlow status = TaskManager.runningSlow(2);
        System.out.println(status);
        if (status == TaskManager.RunSlow.SLOW){
            //ignore click
            ResultsManager.recordClick(true);
        } else if (status == TaskManager.RunSlow.BACK){
            ResultsManager.recordClick(false);
            loadScene("standard-main-menu.fxml");
        } else{
            ResultsManager.recordClick(false);
            loadScene("standard-connectivity.fxml");
        }
    }

    @FXML
    public void initialize() {
        updateBluetoothStatus();
    }

    @FXML
    protected void changeStatus() {
        SystemSettingManager.toggleSetting(SystemSettingManager.Setting.BLUETOOTH);
        updateBluetoothStatus();
        TaskManager.endTask1(backButton);
    }

    private void updateBluetoothStatus(){
        boolean enabled = (Boolean) SystemSettingManager.getSetting(SystemSettingManager.Setting.BLUETOOTH);
        if (enabled){
            statusText.setText(ENABLED);
            bluetoothButton.setText("Disable Bluetooth");
        } else {
            statusText.setText(DISABLED);
            bluetoothButton.setText("Enable Bluetooth");
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
