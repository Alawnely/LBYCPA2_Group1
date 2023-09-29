package lbycpa2.module2;

import javafx.fxml.FXML;

public class DataStructureController {
    @FXML
    protected void switchToMainScene() {
        MainApplication main = new MainApplication();
        main.switchScene("main-menu");
    }
}
