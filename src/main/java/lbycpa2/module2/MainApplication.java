package lbycpa2.module2;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static Stage window;
    @Override
    public void start(Stage stage) {
        window = stage;

        switchScene("main-menu");
        stage.setTitle("Data Structures");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public FXMLLoader switchScene(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            window.setScene(scene);
            scene.getRoot().requestFocus();
            return fxmlLoader;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void switchToSocialNet() {
        switchScene("1-static-array");
    }

    @FXML
    private void switchToDrapiza() {
        switchScene("drapiza");
    }

    @FXML
    private void switchToGuanzon() {
        switchScene("guanzon");
    }

    @FXML
    private void switchToLat() {
        switchScene("lat");
    }

    @FXML
    private void switchToYu() {
        switchScene("yu");
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