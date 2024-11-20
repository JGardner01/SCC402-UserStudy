package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PredictiveNotificationsController {

    private final String ENABLED = "Status: Enabled";
    private final String DISABLED = "Status: Disabled";

    private boolean enabled;

    @FXML
    private Button backButton;
    @FXML
    private Button notificationButton;
    @FXML
    private Text statusText;

    //hidden and disabled buttons
    @FXML
    private Button disabledButton1;
    @FXML
    private Button hiddenMainMenuButton;
    @FXML
    private Button disabledButton2;
    @FXML
    private Button disabledButton3;
    @FXML
    private Button disabledButton4;
    @FXML
    private Button disabledButton5;

    @FXML
    public void initialize() {
        disabledButton1.setVisible(false);
        disabledButton2.setVisible(false);
        disabledButton3.setVisible(false);
        disabledButton4.setVisible(false);
        disabledButton5.setVisible(false);

        if (StateManager.getCurrentMode() == StateManager.Mode.TEST) {
            hiddenMainMenuButton.setOpacity(0);
        }

        enabled = false;
        statusText.setText(DISABLED);
        notificationButton.setText("Enable Notifications");
    }

    @FXML
    protected void onHiddenMainMenuButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("predictive-main-menu.fxml");
    }

    @FXML
    protected void onBackButtonClick() {
        ResultsManager.recordClick(false);
        loadScene("predictive-software-menu.fxml");
    }

    @FXML
    protected void changeStatus() {
        if (!enabled){
            statusText.setText(ENABLED);
            notificationButton.setText("Disable Notifications");
            enabled = true;
        } else {
            statusText.setText(DISABLED);
            notificationButton.setText("Enable Notifications");
            enabled = false;
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
