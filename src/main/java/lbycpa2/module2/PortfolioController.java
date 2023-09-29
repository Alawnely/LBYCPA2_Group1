package lbycpa2.module2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;

public class PortfolioController {
    private static final LinkedList<String> portfolioList = new LinkedList<>(Arrays.asList("drapiza", "guanzon", "lat", "yu"));
    @FXML
    private Button nextButton;
    private int currentPortfolio;

    @FXML
    protected void switchToMainScene() {
        MainApplication main = new MainApplication();
        main.switchScene("main-menu");
    }

    @FXML
    private void goNext() {
        MainApplication main = new MainApplication();
        main.switchScene(portfolioList.get(currentPortfolio+1));
    }

    protected void setCurrentPortfolio(URL location) {
        String filename = new File(location.getFile()).getName();
        filename = filename.substring(0, filename.length()-5);
        currentPortfolio = portfolioList.indexOf(filename);

        if (currentPortfolio >= portfolioList.size()-1) {
            nextButton.setVisible(false);
        }
    }
}
