package lbycpa2.module6;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SocialApplication extends Application {
    @FXML
    private TextField nameField;
    @FXML
    private TextField newProfileField;
    @FXML
    private ListView<String> profilesList;
    private static Stage window;
    private static String stylesheet;
    private static List<Profile> profiles;
    private static ProfileGraph graph;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        stylesheet = Objects.requireNonNull(getClass().getResource("social.css")).toExternalForm();
        profiles = new ArrayList<>();
        graph = new ProfileGraph(1);

        Profile profile = new Profile("Bill Gates");
        profile.setStatus("Bill Gates is speaking...");
        profile.setQuote("\"Success is a lousy teacher. It seduces smart people into thinking they can't lose\"");
        profiles.add(profile);
        graph.addUser(profile);

        switchScene("main");
        stage.setTitle("SocialNet");
        stage.setResizable(false);
        stage.show();

        System.out.println("Loading application...");
    }

    public FXMLLoader switchScene(String fxml, boolean avoidFocus) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("social-" + fxml + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            window.setScene(scene);
            if (avoidFocus) {
                scene.getRoot().requestFocus();
            }
            return fxmlLoader;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FXMLLoader switchScene(String fxml) {
        return switchScene(fxml, false);
    }

    public static List<Profile> getProfiles() {
        return profiles;
    }

    public static ProfileGraph getGraph() {
        return graph;
    }

    public static String getStylesheet() {
        return stylesheet;
    }

    @FXML
    private void initialize() {
        listProfiles();
    }

    private void listProfiles() {
        profilesList.getItems().clear();

        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Profile profile : profiles) {
            observableList.add(profile.getName());
        }
        profilesList.setItems(observableList);
    }

    @FXML
    private void signIn() {
        Profile profile = null;
        String name = nameField.getText();
        for (Profile p : profiles) {
            if (p.getName().equalsIgnoreCase(name)) {
                profile = p;
                break;
            }
        }
        if (profile == null) {
            System.out.println("Sign in failed: "+name);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect name!");
            alert.setHeaderText("Error");
            alert.initOwner(window);
            alert.getDialogPane().getStylesheets().add(stylesheet);
            alert.show();

            return;
        }

        System.out.println("Successful sign in: "+name);

        FXMLLoader loader = switchScene("profile", true);
        SocialViewer viewer = loader.getController();
        viewer.loadProfile(profile);
    }

    @FXML
    private void createProfile() {
        String name = newProfileField.getText();
        if (name.isBlank()) {
            return;
        }

        System.out.println("New profile created: "+name);

        Profile newP = new Profile(name);
        profiles.add(newP);
        graph.addUser(newP);
        newProfileField.clear();
        listProfiles();
    }
}
