package lbycpa2.module4;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
    @FXML
    private Button next;
    @FXML
    private Label totalLabel;
    @FXML
    Label totalCorrectLabel;
    @FXML
    Label totalWrongLabel;
    private Button[] choices;
    public static MyQueue<Question> quesQueue;
    private int totalQues, totalCorrect, totalWrong;

    private Question currQuestion;

    public void initialize() {
        choices = new Button[]{choice1, choice2, choice3, choice4};
        quesQueue = MainApplication.questionList;
        setQuestion(quesQueue.pop());
        next.setVisible(false);
        totalQues = quesQueue.size()+1;
        totalCorrect = 0;
        totalWrong = 0;
        updateStats();
    }

    @FXML
    protected void onChoiceClick(ActionEvent event) {
        Button currButton = (Button) event.getSource();
        if (currButton.getText().equalsIgnoreCase(currQuestion.getCorrectAns())) { //If correct answer
            setStyleCorrect(currButton);
            totalCorrect++;
        } else {
            findCorrectAnswer();
            setStyleWrong(currButton);
            totalWrong++;
            quesQueue.push(currQuestion);
        }
        for (Button choice : choices) {
            choice.setDisable(true);
        }
        next.setVisible(true);
    }

    protected void setQuestion(Question question) {
        Scene mainScene = MainApplication.getWindow().getScene();
        WritableImage snapshot = mainScene.snapshot(null);

        currQuestion = question;
        quesNum.setText("Question "+ (question.getMyIndex()+1));
        quesDetails.setText(question.getQuestionDetails());
        String[] choicesText = question.getChoices();

        Random random = new Random();
        int choiceIndex;
        ArrayList<String> listOfChoices = new ArrayList<>(Arrays.asList(choicesText));

        for (Button choice : choices) {
            choiceIndex = random.nextInt(listOfChoices.size());
            choice.setText(listOfChoices.get(choiceIndex));
            listOfChoices.remove(choiceIndex);
            resetStyle(choice);
        }

        next.setVisible(false);
        totalQues = quesQueue.size()+1;
        updateStats();

        transitionAway(snapshot);
    }

    private void transitionAway(WritableImage snapshot) {
        Scene mainScene = MainApplication.getWindow().getScene();
        Parent root = mainScene.getRoot();

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
            root.getStyleClass().remove("root");
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

    private void setStyleCorrect(Button button) {
        button.setId("buttonCorrect");
    }

    private void setStyleWrong(Button button) {
        button.setId("buttonWrong");
    }

    private void resetStyle(Button button){
        button.setId(null);
        button.setDisable(false);
    }

    private void findCorrectAnswer() {
        String correct = currQuestion.getCorrectAns();
        for (Button choice : choices) {
            if (choice.getText().equalsIgnoreCase(correct)) {
                setStyleCorrect(choice);
            }
        }
    }

    private void updateStats () {
        totalLabel.setText(String.valueOf(totalQues));
        totalCorrectLabel.setText(String.valueOf(totalCorrect));
        totalWrongLabel.setText(String.valueOf(totalWrong));
    }

}
