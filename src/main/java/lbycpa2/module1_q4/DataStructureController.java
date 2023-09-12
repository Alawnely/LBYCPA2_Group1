package lbycpa2.module1_q4;

import javafx.fxml.FXML;

public class DataStructureController {
    @FXML
    protected void switchToMainScene() {
        MainApplication main = new MainApplication();
        main.switchScene("main-menu");
    }
}
