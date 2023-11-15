package lbycpa2.module6;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SocialEditor {
    @FXML
    private TextField nameField;
    @FXML
    private TextField statusField;
    @FXML
    private TextArea quoteField;
    @FXML
    private ImageView displayImage;
    private Profile profile;

    @FXML
    private void changeImage() throws IOException {
        FileChooser fileChooser = new FileChooser();
        URL initialPath = getClass().getResource("img");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.gif", "*.jpeg", "*.jpg", "*.png")
        );
        fileChooser.setInitialDirectory(initialPath == null ? null :
                new File(URLDecoder.decode(initialPath.getPath(), StandardCharsets.UTF_8)));
        File file = fileChooser.showOpenDialog(displayImage.getScene().getWindow());
        if (file != null) {
            displayImage.setImage(new Image(file.getCanonicalPath()));
        }
    }

    public void loadProfile(Profile profile) {
        this.profile = profile;
        nameField.setText(profile.getName());
        statusField.setText(profile.getStatus());
        quoteField.setText(profile.getQuote());
        displayImage.setImage(Objects.requireNonNullElse(profile.getImage(), SocialViewer.defaultImage));
    }

    public void editProfile() {
        String name = nameField.getText();
        if (name.isBlank()) {
            System.out.println("New profile name invalid");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Name cannot be blank");
            alert.setHeaderText("Invalid name");
            //alert.initOwner(nameField.getScene().getWindow());
            alert.getDialogPane().getStylesheets().add(SocialApplication.getStylesheet());
            alert.show();
        } else {
            profile.setName(name);
        }
        profile.setStatus(statusField.getText());
        profile.setQuote(quoteField.getText());
        profile.setImage(displayImage.getImage());
    }
}
