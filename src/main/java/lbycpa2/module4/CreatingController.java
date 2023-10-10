package lbycpa2.module4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatingController {
    @FXML
    private TextArea question;
    @FXML
    private TextField choice1;
    @FXML
    private TextField choice2;
    @FXML
    private TextField choice3;
    @FXML
    private TextField choice4;
    @FXML
    private Button back;
    @FXML
    Label status;


    @FXML
    protected void goHome (ActionEvent event) {
        MainApplication main = new MainApplication();
        main.switchScene("start");
    }


    @FXML
    protected void addQuestion (ActionEvent event) {
        if(!checkTextboxes()==true){
            status.setText("Error adding question. Fill out all textfield.");
            throw new RuntimeException("Question not added. Incomplete details.");
        }
        if (MainApplication.questionList.empty()){
            status.setText("Error adding question. Question list is full.");
            throw new RuntimeException("Question not added. Full queue.");
        }
        String[] choices= {choice1.getText(),choice2.getText(),choice3.getText(),choice4.getText()};
        Question newQuestion = new Question(MainApplication.questionList.size(),question.getText(),choices,choice1.getText());
        MainApplication.questionList.push(newQuestion);
        status.setText("Succesfully added the question. Thank you.");
    }

    public boolean checkTextboxes(){
        if (!choice1.getText().isEmpty() && !choice2.getText().isEmpty() && !choice3.getText().isEmpty() && !choice4.getText().isEmpty() && !question.getText().isEmpty()){
            return true;
        }
        return false;
    }
}
