package lbycpa2.module2;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;

public class MainApplication extends Application {
    private static Stage window;
    @Override
    public void start(Stage stage) {
        window = stage;

        initializeData();

        switchScene("intro-test");
        stage.setTitle("Data Structures Portfolio");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getWindow() {
        return window;
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
        FXMLLoader loader = switchScene("showcase-test");
        Object controller = loader.getController();
        if (controller instanceof PortfolioController) {
            ((PortfolioController) controller).setCurrentPortfolio(0);
        }
    }

    @FXML
    private void switchToGuanzon() {
        FXMLLoader loader = switchScene("showcase-test");
        Object controller = loader.getController();
        if (controller instanceof PortfolioController) {
            ((PortfolioController) controller).setCurrentPortfolio(1);
        }
    }

    @FXML
    private void switchToLat() {
        FXMLLoader loader = switchScene("showcase-test");
        Object controller = loader.getController();
        if (controller instanceof PortfolioController) {
            ((PortfolioController) controller).setCurrentPortfolio(2);
        }
    }

    @FXML
    private void switchToYu() {
        FXMLLoader loader = switchScene("showcase-test");
        Object controller = loader.getController();
        if (controller instanceof PortfolioController) {
            ((PortfolioController) controller).setCurrentPortfolio(3);
        }
    }
}