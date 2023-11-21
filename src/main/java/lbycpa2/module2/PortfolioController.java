package lbycpa2.module2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;

public class PortfolioController {
    private static final LinkedList<String> portfolioList = new LinkedList<>(Arrays.asList("drapiza", "guanzon", "lat", "yu"));
    @FXML
    private Button nextButton;
    @FXML
    private VBox worksVBox;
    private int currentPortfolio;

    @FXML
    protected void switchToMainScene() {
        MainApplication main = new MainApplication();
        main.switchScene("intro-test");
    }

    @FXML
    private void initialize() {
        worksVBox.getChildren().clear();
    }

    void addWorks(String text, String imagePath) {
        addWorks(text, imagePath, 0);
    }

    void addWorks(String text, String imagePath, int width) {
        Label label = new Label(text);

        InputStream url = getClass().getResourceAsStream("outputs/" + imagePath);
        ImageView imageView = new ImageView(new Image(url));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width == 0 ? MainApplication.getWindow().getWidth() - 80 : width);

        VBox vbox = new VBox(label, imageView);
        worksVBox.getChildren().add(vbox);
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
