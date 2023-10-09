package lbycpa2.module4;

import java.util.Arrays;

public class Question {

    private String questionDetails;
    private String[] choices;

    private String correctAns;

    public Question(String questionDetails, String[] choices, String correctAns) {
        this.questionDetails = questionDetails;
        this.choices = choices;
        this.correctAns = correctAns;
    }

    public String getQuestionDetails() {
        return questionDetails;
    }

    public void setQuestionDetails(String questionDetails) {
        this.questionDetails = questionDetails;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionDetails='" + questionDetails + '\'' +
                ", choices=" + Arrays.toString(choices) +
                ", correctAns='" + correctAns + '\'' +
                '}';
    }
}
