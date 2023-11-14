package lbycpa2.module6;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Profile {
    private String name;
    private String status;
    private String quote;
    private Image image;

    private int index;
    private List<Profile> friends = new ArrayList<>();

    public Profile(String name) {
        this.name = name;
    }

    public Profile(String name, int index) {

        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Profile> getFriends() {
        return Collections.unmodifiableList(friends);
    }

    public void addFriend(Profile profile) {
        friends.add(profile);
    }

    public void removeFriend(Profile profile) {
        friends.remove(profile);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
