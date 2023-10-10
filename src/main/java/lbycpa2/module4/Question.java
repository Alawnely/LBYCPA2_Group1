package lbycpa2.module4;

import java.util.Arrays;

public class Question {

    private String questionDetails;
    private String[] choices;
    private int myIndex;

    private String correctAns;

    public Question(int myIndex, String questionDetails, String[] choices, String correctAns) {
        this.myIndex = myIndex;
        this.questionDetails = questionDetails;
        this.choices = choices;
        this.correctAns = correctAns;
    }

    public int getMyIndex() {
        return myIndex;
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
