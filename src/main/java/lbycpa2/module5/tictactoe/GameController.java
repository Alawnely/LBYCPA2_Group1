package lbycpa2.module5.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lbycpa2.module4.MainApplication;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class GameController {
    boolean isUserTurn;
    public static boolean isUserX;

    Image userImage;

    Image aiImage;

    @FXML
    ImageView cell1;

    @FXML
    ImageView cell2;

    @FXML
    ImageView cell3;

    @FXML
    ImageView cell4;

    @FXML
    ImageView cell5;

    @FXML
    ImageView cell6;

    @FXML
    ImageView cell7;

    @FXML
    ImageView cell8;

    @FXML
    ImageView cell9;

    public void initialize(){
        resetAllCells();
        if (isUserX){
            setUserImg("xcircle.png");
            setAiImage("ocircle.png");
        } else {
            setUserImg("ocircle.png");
            setAiImage("xcircle.png");
        }
        isUserTurn = true;
    }

    @FXML
    private void atButtonPress(ActionEvent event){
        Button currButton = (Button) event.getSource();
        ImageView currImgView = (ImageView) currButton.getGraphic();
        if (isUserTurn) {
            currImgView.setImage(userImage);
            isUserTurn = false;
        } else {
            currImgView.setImage(aiImage);
            isUserTurn = true;
        }
        currButton.setDisable(true);
    }

    private void setUserImg(String fileName){
        userImage = new Image(getClass().getResourceAsStream(fileName));
    }

    private void setAiImage(String fileName) {
        aiImage = new Image(getClass().getResourceAsStream(fileName));
    }

    private void resetAllCells() {
        ImageView[] cells = new ImageView[]{cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9};
        String fileName = "blankcircle.png";
        for (ImageView cell : cells) {
            cell.setImage(new Image(getClass().getResourceAsStream(fileName)));
        }
    }
}
