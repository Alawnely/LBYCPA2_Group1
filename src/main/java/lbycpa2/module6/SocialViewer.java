package lbycpa2.module6;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SocialViewer {
    @FXML
    private Label nameLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label quoteLabel;
    @FXML
    private Label numfriendLabel;
    @FXML
    private ImageView displayImage;
    @FXML
    private Button addfButton;
    @FXML
    private Button removefButton;
    @FXML
    private Button editButton;
    @FXML
    private Button nameMiniButton;
    @FXML
    private TextField searchField;
    @FXML
    private VBox searchPane;
    @FXML
    private VBox friendsPane;
    private Profile profile;
    private Profile stalkingWho;
    public static final Image defaultImage = new Image(SocialViewer.class.getResource("img/unknown.png").toExternalForm());

    @FXML
    private void signOut() {
        System.out.println("Signed out: "+profile.getName());

        SocialApplication socialApp = new SocialApplication();
        socialApp.switchScene("main");
    }

    @FXML
    private void viewSignedIn() {
        loadProfile(profile);
    }

    public void peekProfile(Profile profile) {
        System.out.println("Viewing profile: "+profile.getName());
        stalkingWho = profile;

        String s = profile.getName();
        nameLabel.setText(s == null || s.isBlank() ? "null" : s);

        s = profile.getStatus();
        statusLabel.setText(s == null || s.isBlank() ? "Status not set." : s);

        s = profile.getQuote();
        quoteLabel.setText(s == null || s.isBlank() ? "Quote not set." : s);

        Image image = profile.getImage();
        displayImage.setImage(Objects.requireNonNullElse(image, defaultImage));

        friendsPane.getChildren().clear();
        for (Profile p : profile.getFriends()) {
            Button button = createProfileButton(p);
            friendsPane.getChildren().add(button);
        }
        int friendCount = friendsPane.getChildren().size();
        numfriendLabel.setText(friendCount+" Friend"+(friendCount == 1 ? "" : "s"));

        editButton.setManaged(stalkingWho == this.profile);
        editButton.setVisible(editButton.isManaged());

        addfButton.setManaged(!editButton.isManaged() && !stalkingWho.getFriends().contains(this.profile));
        addfButton.setVisible(addfButton.isManaged());

        removefButton.setManaged(!editButton.isManaged() && !addfButton.isManaged());
        removefButton.setVisible(removefButton.isManaged());
    }

    public void loadProfile(Profile profile) {
        this.profile = profile;
        peekProfile(profile);
        nameMiniButton.setText(profile.getName());
        ((ImageView) nameMiniButton.getGraphic()).setImage(Objects.requireNonNullElse(profile.getImage(), defaultImage));
    }

    @FXML
    public void editProfile() throws IOException {
        System.out.println("Editing profile: "+profile.getName());

        // https://stackoverflow.com/a/36024666
        Dialog<ButtonType> dialog = new Dialog<>();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("social-edit.fxml"));
        DialogPane dialogPane = fxmlLoader.load();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Edit Profile");
        dialog.setResizable(false);
        dialog.initOwner(editButton.getScene().getWindow());

        SocialEditor controller = fxmlLoader.getController();
        controller.loadProfile(profile);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.APPLY) {
            controller.editProfile();
            loadProfile(profile);
        }
    }

    @FXML
    private void searchProfile() {
        searchPane.getChildren().clear();

        String query = searchField.getText();
        if (query.isBlank()) {
            return;
        }

        for (Profile p : SocialApplication.getProfiles()) {
            if (profile != p && p.getName().contains(query)) {
                Button button = createProfileButton(p);
                searchPane.getChildren().add(button);
            }
        }
    }

    @FXML
    private void addFriend() {
        System.out.println("Profile "+profile.getName()+" added as friend: "+stalkingWho.getName());

        profile.addFriend(stalkingWho);
        stalkingWho.addFriend(profile);

        peekProfile(stalkingWho);
    }

    @FXML
    private void removeFriend() {
        System.out.println("Profile "+profile.getName()+" removed as friend: "+stalkingWho.getName());

        profile.removeFriend(stalkingWho);
        stalkingWho.removeFriend(profile);

        peekProfile(stalkingWho);
    }

    private Button createProfileButton(Profile profile) {
        ImageView origImageView = (ImageView) nameMiniButton.getGraphic();
        ImageView imageView = new ImageView(Objects.requireNonNullElse(profile.getImage(), defaultImage));
        imageView.setStyle(origImageView.getStyle());
        imageView.setFitWidth(origImageView.getFitWidth());
        imageView.setFitHeight(origImageView.getFitHeight());

        Button button = new Button(profile.getName(), imageView);
        button.setStyle(nameMiniButton.getStyle());
        button.setFont(nameMiniButton.getFont());
        button.setMaxWidth(nameMiniButton.getMaxWidth());
        button.setAlignment(nameMiniButton.getAlignment());
        button.setOnMouseClicked(mouseEvent -> peekProfile(profile));
        return button;
    }

    @FXML
    private void deleteProfile() {
        Window owner = editButton.getScene().getWindow();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setHeaderText("Delete Profile");
        dialog.setContentText("Are you sure you want to delete your own profile?");
        dialog.setTitle("Delete Profile");
        dialog.setResizable(false);
        dialog.initOwner(owner);
        dialog.getDialogPane().getStylesheets().add(SocialApplication.getStylesheet());
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            System.out.println("Profile deleted: "+profile.getName());

            List<Profile> profiles = SocialApplication.getProfiles();
            profiles.remove(profile);
            for (Profile p : profiles) {
                p.removeFriend(profile);
                profile.removeFriend(p);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Profile deleted successfully");
            alert.setHeaderText("Deleted");
            alert.setTitle("Success");
            alert.initOwner(owner);
            alert.getDialogPane().getStylesheets().add(SocialApplication.getStylesheet());
            alert.show();

            signOut();
        }
    }
}
