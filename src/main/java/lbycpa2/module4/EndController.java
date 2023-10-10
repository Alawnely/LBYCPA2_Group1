package lbycpa2.module4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndController {
    @FXML
    private Label totalCorrectLabel;
    @FXML
    private Label totalWrongLabel;

    protected void setStats(int correct, int wrong) {
        totalCorrectLabel.setText("Total Correct Answers: " + correct);
        totalWrongLabel.setText("Total Wrong Answers: " + wrong);
    }

    @FXML
    private void goHome() {
        MainApplication main = new MainApplication();
        main.switchScene("start");
    }
}
