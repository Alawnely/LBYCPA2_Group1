package lbycpa2.module4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainApplication {

    public static LinkedList<Question> questionList = loadQuestions("question_list.txt");
    public static void main(String[] args) {
        // I think the queue will be used to store questions that one got right and questions that one got wrong
        //TODO: Implement QUEUE

        // Print the questions from Linked List
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println("Question " + (i + 1) + ":");
            System.out.println(questionList.get(i).getQuestionDetails());
            String[] choices = questionList.get(i).getChoices();
            for (int j = 0; j < choices.length; j++) {
                System.out.println((j+1)+": "+choices[j]);

            }
            System.out.println("Correct Answer: "+questionList.get(i).getCorrectAns());
            System.out.println();
        }
    }

    public static LinkedList<Question> loadQuestions (String fileName) {
        LinkedList<Question> questionList = new LinkedList<>();

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
                    questionList.add(new Question(question, choices, correctAns));
                    /* Reset variables for the next question */
                    question = "";
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
}
