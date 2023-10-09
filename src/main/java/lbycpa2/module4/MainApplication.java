package lbycpa2.module4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class MainApplication extends Application {

    public static MyQueue<Question> questionList;
    private static Stage window;
    private static String stylesheet;
    @Override
    public void start(Stage stage) {
        questionList = loadQuestions("question_list.txt");
        window = stage;
        stylesheet = Objects.requireNonNull(getClass().getResource("temporarycss.css")).toExternalForm();

        switchScene("start");
        stage.setTitle("Who Wants to be a Kahoot-ionare?!");
        stage.setResizable(false);
        stage.show();
    }

    public FXMLLoader switchScene(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(stylesheet);
            window.setScene(scene);
            scene.getRoot().requestFocus();
            return fxmlLoader;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        questionList = loadQuestions("question_list.txt");
        // I think the queue will be used to store questions that one got right and questions that one got wrong
        /* Debugging */
         int i = 0;
           while(!questionList.empty()){

                Question currQuestion = questionList.pop();

                System.out.println("Question " + (i + 1) + ":");
                System.out.println(currQuestion.getQuestionDetails());
                String[] choices =currQuestion.getChoices();
                for (int j = 0; j < choices.length; j++) {
                    System.out.println((j+1)+": "+choices[j]);

                }
                System.out.println("Correct Answer: "+currQuestion.getCorrectAns());
                System.out.println();
                i++;
            }

        launch(args);
    }

    public static MyQueue<Question> loadQuestions (String fileName) {
        MyQueue<Question> questionList = new MyQueue<>(15);

        String fileLoc = "src/main/java/lbycpa2/module4/";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLoc+fileName))) {
            String line;
            String question = "";
            String[] choices = new String[4];
            int choiceIndex = 0;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Correct Answer: ")) {
                    /* Create a Question object and add it to the list */
                    String correctAns = line.substring(16);
                    questionList.push(new Question(question, choices, correctAns));
                    /* Reset variables for the next question */
                    question = "";
                    choices = new String[4];
                    choiceIndex = 0;
                } else if (line.startsWith("a) ") || line.startsWith("b) ") || line.startsWith("c) ") || line.startsWith("d) ")) {
                    choices[choiceIndex++] = line.substring(3);
                } else {
                    question += line + "\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    @FXML
    private void startGame(ActionEvent event) {
        switchScene("question-main");
    }
}
