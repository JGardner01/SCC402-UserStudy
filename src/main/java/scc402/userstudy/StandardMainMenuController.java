package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class StandardMainMenuController {
    @FXML
    private Button hardwareButton;
    @FXML
    private Button startTestButton;

    @FXML
    public void initialize(){
        startTestButton.setVisible(StateManager.getCurrentMode() == StateManager.Mode.DEMO);
    }

    @FXML
    protected void onHardwareButtonClick() {
        loadScene("standard-hardware-menu.fxml");
    }

    @FXML
    protected void onSoftwareButtonClick() {
        loadScene("standard-software-menu.fxml");
    }

    @FXML
    protected void onAboutButtonClick() {
        loadScene("standard-about.fxml");
    }

    @FXML
    protected void onStartTestButtonClick(){
        StateManager.setCurrentMode(StateManager.Mode.TEST);
        loadScene("standard-main-menu.fxml");
    }

    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) hardwareButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
