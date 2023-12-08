package lbycpa2.module2;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;

public class MainApplication extends Application {
    private static Stage window;
    private static WritableImage snapshot;

    public static void main(String[] args) {
        launch();
    }

    public static Stage getWindow() {
        return window;
    }

    @Override
    public void start(Stage stage) {
        window = stage;

        initializeData();

        switchScene("main-menu");
        stage.setTitle("Data Structures Portfolio");
        stage.setResizable(false);
        stage.show();
    }

    public FXMLLoader switchScene(String fxml) {
        try {
            URL location = getClass().getResource(fxml + ".fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            Scene scene = new Scene(fxmlLoader.load());
            window.setScene(scene);
            scene.getRoot().requestFocus();

            return fxmlLoader;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initializeData() {
        LinkedHashMap<String, String> drapizaPortfolio = new LinkedHashMap<>();
        drapizaPortfolio.put("Digital Artistry", "Drapiza_Artistry.png");
        drapizaPortfolio.put("Pokemon", "Drapiza_Pokemon.jpg");
        drapizaPortfolio.put("SocialNet", "Drapiza_SocialNet.png");
        PortfolioController.portfolioList.add(drapizaPortfolio);

        LinkedHashMap<String, String> guanzonPortfolio = new LinkedHashMap<>();
        guanzonPortfolio.put("Digital Artistry", "Guanzon - digital artistry.png");
        guanzonPortfolio.put("Hangman", "Guanzon - hangman.png");
        guanzonPortfolio.put("SocialNet", "Guanzon - socialnet.png");
        guanzonPortfolio.put("Pokemon Card Collection", "Guanzon - pokemon 1.png");
        guanzonPortfolio.put("Pokemon Card Collection 2", "Guanzon - pokemon 2.png");
        PortfolioController.portfolioList.add(guanzonPortfolio);

        LinkedHashMap<String, String> latPortfolio = new LinkedHashMap<>();
        latPortfolio.put("Chess", "Lat - Chess.png");
        latPortfolio.put("Hangman", "Lat - Hangman.png");
        latPortfolio.put("Pokemon Card Collection", "Lat -Pokemon 1.png");
        latPortfolio.put("3D Modeling", "Lat - Modeling1.png");
        PortfolioController.portfolioList.add(latPortfolio);

        LinkedHashMap<String, String> yuPortfolio = new LinkedHashMap<>();
        yuPortfolio.put("Digital Artistry", "yu - digital artistry.png");
        yuPortfolio.put("Hangman", "yu - hangman.png");
        yuPortfolio.put("SocialNet", "yu - socialnet.png");
        yuPortfolio.put("Pokemon Card Collection", "yu - pokemon1.png");
        yuPortfolio.put("Pokemon Card Collection 2", "yu - pokemon 2.png");
        PortfolioController.portfolioList.add(yuPortfolio);
    }

    @FXML
    private void switchToDrapiza() {
        switchToPortfolio(0);
    }

    @FXML
    private void switchToGuanzon() {
        switchToPortfolio(1);
    }

    @FXML
    private void switchToLat() {
        switchToPortfolio(2);
    }

    @FXML
    private void switchToYu() {
        switchToPortfolio(3);
    }

    private void switchToPortfolio(int index) {
        snapshot = window.getScene().snapshot(null);

        FXMLLoader loader = switchScene("portfolio-showcase");
        Object controller = loader.getController();
        if (controller instanceof PortfolioController) {
            ((PortfolioController) controller).setCurrentPortfolio(index);
            transition(null);
        }
    }

    public static void transition(WritableImage snapshot2) {
        Scene mainScene = window.getScene();
        Parent root = mainScene.getRoot();

        if (root instanceof StackPane) {
            return;
        }

        StackPane transitionPane = new StackPane();
        transitionPane.setMinWidth(mainScene.getWidth());
        transitionPane.setMinHeight(mainScene.getHeight());

        ImageView snapshotImage = new ImageView(snapshot);
        if (snapshot2 != null) {
            ImageView snapshot2Image = new ImageView(snapshot2);
            transitionPane.getChildren().addAll(root, snapshot2Image, snapshotImage);
        } else {
            transitionPane.getChildren().addAll(root, snapshotImage);
        }
        mainScene.setRoot(transitionPane);

        if (snapshot2 != null) {
            snapshotImage.scaleXProperty().set(1.15);
            snapshotImage.scaleYProperty().set(1.15);
            snapshotImage.opacityProperty().set(0);
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(400),
                new KeyValue(snapshotImage.scaleXProperty(), snapshot2 == null ? 1.15 : 1, Interpolator.EASE_IN),
                new KeyValue(snapshotImage.scaleYProperty(), snapshot2 == null ? 1.15 : 1, Interpolator.EASE_IN),
                new KeyValue(snapshotImage.opacityProperty(), snapshot2 == null ? 0 : 1, Interpolator.EASE_IN)
        ));
        timeline.setOnFinished(e -> {
            transitionPane.getChildren().remove(root);
            root.getStyleClass().remove("root");
            mainScene.setRoot(root);
        });
        timeline.play();
    }
}