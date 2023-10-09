package lbycpa2.module4;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.LinkedList;

public class QuestionController {

    @FXML
    private Label quesNum;
    @FXML
    private Label quesDetails;
    @FXML
    private Button choice1;
    @FXML
    private Button choice2;
    @FXML
    private Button choice3;
    @FXML
    private Button choice4;


    public static MyQueue<Question> quesQueue;
    private int quesCount;

    private Question currQuestion;

    public void initialize() {
        quesQueue = MainApplication.questionList;
        quesCount = 0;
        setQuestion(quesQueue.pop());
    }

    protected void setQuestion(Question question) {
        Scene mainScene = MainApplication.getWindow().getScene();
        WritableImage snapshot = mainScene.snapshot(null);

        quesCount++;
        currQuestion = question;
        quesNum.setText("Question "+quesCount);
        quesDetails.setText(question.getQuestionDetails());
        String[] choices = question.getChoices();
        choice1.setText(choices[0]);
        choice2.setText(choices[1]);
        choice3.setText(choices[2]);
        choice4.setText(choices[3]);

        transitionAway(snapshot);
    }

    private void transitionAway(WritableImage snapshot) {
        Scene mainScene = MainApplication.getWindow().getScene();
        Parent root = mainScene.getRoot();
        System.out.println(root);

        if (root instanceof StackPane) {
            return;
        }

        StackPane transitionPane = new StackPane();
        transitionPane.setMinWidth(mainScene.getWidth());
        transitionPane.setMinHeight(mainScene.getHeight());

        ImageView snapshotImage = new ImageView(snapshot);
        transitionPane.getChildren().addAll(root, snapshotImage);
        mainScene.setRoot(transitionPane);

        snapshotImage.translateXProperty().set(0);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), new KeyValue(snapshotImage.translateXProperty(), -transitionPane.getWidth(), Interpolator.EASE_IN)));
        timeline.setOnFinished(e -> {
            transitionPane.getChildren().remove(root);
            mainScene.setRoot(root);
        });
        timeline.play();
    }

    @FXML
    private void goNext() {
        if (!quesQueue.empty()) {
            setQuestion(quesQueue.pop());
        }
    }
}
