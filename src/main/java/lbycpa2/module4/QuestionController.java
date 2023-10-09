package lbycpa2.module4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        quesCount++;
        currQuestion = question;
        quesNum.setText("Question "+quesCount);
        quesDetails.setText(question.getQuestionDetails());
        String[] choices = question.getChoices();
        choice1.setText(choices[0]);
        choice2.setText(choices[1]);
        choice3.setText(choices[2]);
        choice4.setText(choices[3]);
        resetStyle(choice1);
        resetStyle(choice2);
        resetStyle(choice3);
        resetStyle(choice4);
        next.setVisible(false);
        totalQues--;
        updateStats();
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
}
