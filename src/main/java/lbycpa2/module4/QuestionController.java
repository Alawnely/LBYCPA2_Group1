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
import java.util.Collections;
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
    public static MyQueue<Question> quesQueue;
    private int quesCount;
    private int totalQues, totalCorrect, totalWrong;

    private Question currQuestion;

    public void initialize() {
        quesQueue = MainApplication.questionList;
        quesCount = 0;
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
        if (currButton.getText().equalsIgnoreCase(currQuestion.getCorrectAns())) {
            setStyleCorrect(currButton);
            totalCorrect++;
        } else {
            findCorrectAnswer();
            setStyleWrong(currButton);
            totalWrong++;
        }
        next.setVisible(true);
    }

    protected void setQuestion(Question question) {
        Scene mainScene = MainApplication.getWindow().getScene();
        WritableImage snapshot = mainScene.snapshot(null);

        quesCount++;
        currQuestion = question;
        quesNum.setText("Question "+quesCount);
        quesDetails.setText(question.getQuestionDetails());
        String[] choices = question.getChoices();

        Random random = new Random();
        int choiceIndex;
        ArrayList<String> listOfChoices = new ArrayList<>(Arrays.asList(choices));

        choiceIndex = random.nextInt(listOfChoices.size());
        choice1.setText(listOfChoices.get(choiceIndex));
        listOfChoices.remove(choiceIndex);

        choiceIndex = random.nextInt(listOfChoices.size());
        choice2.setText(listOfChoices.get(choiceIndex));
        listOfChoices.remove(choiceIndex);

        choiceIndex = random.nextInt(listOfChoices.size());
        choice3.setText(listOfChoices.get(choiceIndex));
        listOfChoices.remove(choiceIndex);

        choiceIndex = random.nextInt(listOfChoices.size());
        choice4.setText(listOfChoices.get(choiceIndex));
        listOfChoices.remove(choiceIndex);

        resetStyle(choice1);
        resetStyle(choice2);
        resetStyle(choice3);
        resetStyle(choice4);
        next.setVisible(false);
        totalQues--;
        updateStats();

        transitionAway(snapshot);
    }

    private void transitionAway(WritableImage snapshot) {
        Scene mainScene = MainApplication.getWindow().getScene();
        Parent root = mainScene.getRoot();
//        System.out.println(root);

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
        button.getStyleClass().clear();
        button.getStyleClass().add("buttonCorrect");
    }

    private void setStyleWrong(Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("buttonWrong");
    }

    private void resetStyle(Button button){
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
    }

    private void findCorrectAnswer() {
        String correct = currQuestion.getCorrectAns();
        if (choice1.getText().equalsIgnoreCase(correct)){
            setStyleCorrect(choice1);
        } else if (choice2.getText().equalsIgnoreCase(correct)) {
            setStyleCorrect(choice2);
        } else if (choice3.getText().equalsIgnoreCase(correct)) {
            setStyleCorrect(choice3);
        } else if (choice4.getText().equalsIgnoreCase(correct)) {
            setStyleCorrect(choice4);
        }
    }

    private void updateStats () {
        totalLabel.setText(String.valueOf(totalQues));
        totalCorrectLabel.setText(String.valueOf(totalCorrect));
        totalWrongLabel.setText(String.valueOf(totalWrong));
    }

    private boolean checkIndex(int[] index, int number) {
        for (int i = 0; i < index.length; i++) {
            if (index[i] == number) {
                index[i] = Integer.parseInt(null);
                return false;
            }
        }
        return true;
    }

}
