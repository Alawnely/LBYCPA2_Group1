package lbycpa2.module5.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

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

    @FXML
    Label scoresLabel;

    @FXML
    Button replayButton;

    private int aiScore = 0, uScore = 0;
    private MinimaxTree tree;
    private State state;
    private ImageView[] cells;

    public void initialize(){
        replayButton.setVisible(false);
        tree = new MinimaxTree();
        String[] board = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8"};
        state = new State(0, board);

        cells = new ImageView[]{cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9};

        resetAllCells();
        if (isUserX){
            setUserImg("xcircle.png");
            setAiImage("ocircle.png");
        } else {
            setUserImg("ocircle.png");
            setAiImage("xcircle.png");
        }
        isUserTurn = false;

        makeAMove(new Random().nextInt(9));
    }

    @FXML
    private void atButtonPress(ActionEvent event){
        Button currButton = (Button) event.getSource();
        ImageView currImgView = (ImageView) currButton.getGraphic();
        if (isUserTurn) {
            makeAMove(Arrays.asList(cells).indexOf(currImgView));
        }
    }

    private void makeAMove(int decision) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            Parent parent = cells[decision].getParent();
            if (parent != null) {
                parent.setDisable(true);
            }

            if (isUserTurn) {
                cells[decision].setImage(userImage);
                state.changeState(decision, "O");
                isUserTurn = false;
                // magmmove na agad yung AI
                makeAMove(tree.minMaxDecision(state));
                return;
            } else {
                cells[decision].setImage(aiImage);
                state.changeState(decision, "X");
                isUserTurn = true;
            }

            if(tree.isOver(state)){
                int winner = tree.utilityOf(state);
                if (winner == 1){
                    aiScore++;
                } else if (winner == -1){
                    uScore++;
                }
                scoresLabel.setText(uScore + " - " + aiScore);
                for (ImageView cell : cells) {
                    Parent parent2 = cell.getParent();
                    if (parent2 != null) {
                        parent2.setDisable(true);
                    }
                }
                replayButton.setVisible(true);
            }
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void setUserImg(String fileName){
        userImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
    }

    private void setAiImage(String fileName) {
        aiImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
    }

    private void resetAllCells() {
        String fileName = "blankcircle.png";
        for (ImageView cell : cells) {
            cell.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(fileName))));
            Parent parent = cell.getParent();
            if (parent != null) {
                parent.setDisable(false);
            }
        }
    }
}
