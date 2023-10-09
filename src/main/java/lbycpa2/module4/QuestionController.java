package lbycpa2.module4;

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


    public static MyQueue<Question> quesQueue = MainApplication.questionList;
    private int quesCount;

    private Question currQuestion;

    public void initialize() {
        quesCount = 0;

    }

    protected void setQuestion(Question question) {
        quesCount++;
        quesNum.setText("Question "+quesCount);
        quesDetails.setText(question.getQuestionDetails());
        String[] choices = question.getChoices();
        choice1.setText(choices[0]);
        choice2.setText(choices[1]);
        choice3.setText(choices[2]);
        choice4.setText(choices[3]);
    }

}
