package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.lang.Math.round;

public class StandardVolumeController {
    @FXML
    private Button backButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Text volumeText;

    @FXML
    public void initialize() {
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            volumeText.setText("Volume: " + round(newValue.intValue()));
        });
    }

    @FXML
    protected void onBackButtonClick() {
        loadScene("hard-hardware-menu.fxml");
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
