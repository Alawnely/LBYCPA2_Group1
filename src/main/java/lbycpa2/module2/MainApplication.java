package lbycpa2.module2;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {
    private static Stage window;
    @Override
    public void start(Stage stage) {
        window = stage;

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

//            Object controller = fxmlLoader.getController();
//            if (controller instanceof PortfolioController) {
//                ((PortfolioController) controller).setCurrentPortfolio(location);
//            }
            return fxmlLoader;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void switchToDrapiza() {
        FXMLLoader loader = switchScene("showcase-test");
        Object controller = loader.getController();
        if (controller instanceof PortfolioController) {
            ((PortfolioController) controller).addWorks("Digital Artistry", "yu - digital artistry.png");
            ((PortfolioController) controller).addWorks("Hangman", "yu - hangman.png");
            ((PortfolioController) controller).addWorks("SocialNet", "yu - socialnet.png");
            ((PortfolioController) controller).addWorks("Pokemon Card Collection", "yu - pokemon1.png", 200);
        }
    }

    @FXML
    private void switchToGuanzon() {
        FXMLLoader loader = switchScene("showcase-test");
        Object controller = loader.getController();
        if (controller instanceof PortfolioController) {
            ((PortfolioController) controller).addWorks("Digital Artistry", "Guanzon - digital artistry.png");
            ((PortfolioController) controller).addWorks("Hangman", "Guanzon - hangman.png");
            ((PortfolioController) controller).addWorks("SocialNet", "Guanzon - socialnet.png");
            ((PortfolioController) controller).addWorks("Pokemon Card Collection", "Guanzon - pokemon 1.png");
            ((PortfolioController) controller).addWorks("", "Guanzon - pokemon 2.png", 200);
        }
    }

    @FXML
    private void switchToLat() {
        FXMLLoader loader = switchScene("showcase-test");
        Object controller = loader.getController();
        if (controller instanceof PortfolioController) {
            ((PortfolioController) controller).addWorks("Chess", "Lat - Chess.png");
            ((PortfolioController) controller).addWorks("Hangman", "Lat - Hangman.png");
            ((PortfolioController) controller).addWorks("Pokemon Card Collection", "Lat -Pokemon 1.png");
            ((PortfolioController) controller).addWorks("3D Modeling", "Lat - Modeling1.png");
        }
    }

    @FXML
    private void switchToYu() {
        FXMLLoader loader = switchScene("showcase-test");
        Object controller = loader.getController();
        if (controller instanceof PortfolioController) {
            ((PortfolioController) controller).addWorks("Digital Artistry", "yu - digital artistry.png");
            ((PortfolioController) controller).addWorks("Hangman", "yu - hangman.png");
            ((PortfolioController) controller).addWorks("SocialNet", "yu - socialnet.png");
            ((PortfolioController) controller).addWorks("Pokemon Card Collection", "yu - pokemon1.png", 200);
            ((PortfolioController) controller).addWorks("", "yu - pokemon 2.png", 200);
        }
    }

    @FXML
    private void switchToStudentWorks() {
        switchScene("drapiza");
    }

    @FXML
    private void switchToSocialNet() {
        switchScene("1-static-array");
    }

    @FXML
    private void switchToDigitalArtistry() {
        switchScene("6-queue");
    }

    @FXML
    private void switchToPokemon() {
        switchScene("7-binary-search-tree");
    }

    @FXML
    private void switchToHangman() {
        switchScene("8-hash-table");
    }
}