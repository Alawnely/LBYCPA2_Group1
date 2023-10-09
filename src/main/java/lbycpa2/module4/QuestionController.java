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




    private static LinkedList<Question> quesList;
    private static MyQueue<Question> quesQueue;
    public void initialize() {
        quesList = MainApplication.questionList;
        quesQueue = new MyQueue<>(quesList.size());

    }

    protected void setQuestion(int questionIndex) {

        quesNum.setText("Question "+questionIndex);
        quesDetails.setText(quesList.get(questionIndex).getQuestionDetails());
        String[] choices = quesList.get(questionIndex).getChoices();
        choice1.setText(choices[0]);
        choice2.setText(choices[1]);
        choice3.setText(choices[2]);
        choice4.setText(choices[3]);

    }

}
