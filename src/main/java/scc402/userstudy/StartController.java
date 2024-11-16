package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
    @FXML
    private Button startButton;

    @FXML
    protected void onStartButtonClick() {
        System.out.println("Starting User Study");
        //load next scene
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("standard-main-menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(scene);


        } catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }
}