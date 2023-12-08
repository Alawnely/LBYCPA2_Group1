package lbycpa2.module2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Map;

public class PortfolioController {
    protected static final MyLinkedList<Map<String, String>> portfolioList = new MyLinkedList<>();

    @FXML
    private VBox worksVBox;

    @FXML
    private void switchToMainScene() {
        WritableImage snapshot2 = MainApplication.getWindow().getScene().snapshot(null);

        MainApplication main = new MainApplication();
        main.switchScene("main-menu");

        MainApplication.transition(snapshot2);
    }

    @FXML
    private void initialize() {
        worksVBox.getChildren().clear();
    }

    protected void setCurrentPortfolio(int index) {
        Map<String, String> portfolioMap = portfolioList.get(index);
        for (Map.Entry<String, String> entry : portfolioMap.entrySet()) {
            addWorks(entry.getKey(), entry.getValue(), 0);
        }
    }

    private void addWorks(String text, String imagePath, int width) {
        Label label = new Label(text);

        InputStream url = getClass().getResourceAsStream("outputs/" + imagePath);
        ImageView imageView = new ImageView(new Image(url));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width == 0 ? MainApplication.getWindow().getWidth() - 80 : width);

        VBox vbox = new VBox(label, imageView);
        worksVBox.getChildren().add(vbox);
    }
}
