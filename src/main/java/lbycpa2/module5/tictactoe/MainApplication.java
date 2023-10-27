package lbycpa2.module5.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    private static Stage window;
    private static String stylesheet;

    @Override
    public void start(Stage stage) {
        window = stage;
        stylesheet = Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm();

        switchScene("start");
        stage.setTitle("ttt");
        stage.setResizable(false);
        stage.show();
    }

    public FXMLLoader switchScene(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(stylesheet);
            window.setScene(scene);
            scene.getRoot().requestFocus();
            return fxmlLoader;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Stage getWindow() {
        return window;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void startGame() {
        switchScene("choose");
    }

    @FXML
    private void startX() {
        FXMLLoader gameLoader = switchScene("game");
        Object controller = gameLoader.getController();
        if (controller instanceof GameController) {
            ((GameController) controller).setUserX(true);
        }
    }

    @FXML
    private void startO() {
        FXMLLoader gameLoader = switchScene("game");
        Object controller = gameLoader.getController();
        if (controller instanceof GameController) {
            ((GameController) controller).setUserX(false);
        }
    }
}
