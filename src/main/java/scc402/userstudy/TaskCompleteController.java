package scc402.userstudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TaskCompleteController {

    @FXML
    private Button continueButton;

    @FXML protected void onContinueButton(){
        if (TaskManager.getCompletedTasks() % 2 != 0){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("test-introduction.fxml"));
                Scene testIntroScene = new Scene(fxmlLoader.load());

                Stage testIntroStage = new Stage();
                testIntroStage.setTitle("Test Instructions");
                testIntroStage.initModality(Modality.APPLICATION_MODAL);

                testIntroStage.setOnCloseRequest(event -> {
                    event.consume(); // Prevents the window from closing
                });

                Stage stage = (Stage) continueButton.getScene().getWindow();
                stage.close();

                testIntroStage.setScene(testIntroScene);
                testIntroStage.showAndWait();

            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            Stage stage = (Stage) continueButton.getScene().getWindow();
            stage.close();
        }
    }
}
