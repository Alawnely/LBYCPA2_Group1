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
    public void initialize() {
        quesList = MainApplication.questionList;
        quesNum.setText("Question 2");
        quesDetails.setText(quesList.get(2).getQuestionDetails());
        String[] choices = quesList.get(2).getChoices();
        choice1.setText(choices[0]);
        choice2.setText(choices[1]);
        choice3.setText(choices[2]);
        choice4.setText(choices[3]);

    }

}
