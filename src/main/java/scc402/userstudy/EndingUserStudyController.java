package scc402.userstudy;

import javafx.fxml.FXML;

public class EndingUserStudyController {
    @FXML
    protected void onExitButtonClick(){
        //potentially export results here??
        System.out.println("User study ended. Exiting now");
        System.exit(0);
    }
}
