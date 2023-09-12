package lbycpa2.module1_q4;

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
    private void switchToStaticArray() {
        switchScene("1-static-array");
    }

    @FXML
    private void switchToDynamicArray() {
        switchScene("2-dynamic-array");
    }

    @FXML
    private void switchToSinglyLinkedList() {
        switchScene("3-singly-linked-list");
    }

    @FXML
    private void switchToDoublyLinkedList() {
        switchScene("4-doubly-linked-list");
    }

    @FXML
    private void switchToStack() {
        switchScene("5-stack");
    }

    @FXML
    private void switchToQueue() {
        switchScene("6-queue");
    }

    @FXML
    private void switchToBinarySearchTree() {
        switchScene("7-binary-search-tree");
    }

    @FXML
    private void switchToHashTable() {
        switchScene("8-hash-table");
    }
}