package com.example.frederickodaso.spacequiz;

/**
 * Created by frederickodaso on 1/21/17.
 */
public class QuizQuestion {

    private String mQuestionText;
    private int mQuestionNumber;
    private int mQuestionImage;
    private boolean mAnsweredCorrectly;
    private String mChoice1;
    private String mChoice2;
    private String mChoice3;
    private String mChoice4;

    /**
     *
     * @param questionText
     * @param questionNumber
     * @param quizImage
     * @param answeredCorrectly
     *
     * True and false constructor. Can only be answered with True or False Radio Buttons.
     * Also used for number entry question as well.
     */

    public QuizQuestion(String questionText, int questionNumber, int quizImage, boolean answeredCorrectly) {
        mQuestionText = questionText;
        mQuestionNumber = questionNumber;
        mQuestionImage = quizImage;
        mAnsweredCorrectly = answeredCorrectly;
    }

    /**
     *
     * @param questionText
     * @param questionNumber
     * @param quizImage
     * @param choice1
     * @param choice2
     * @param choice3
     * @param choice4
     * @param answeredCorrectly
     *
     * Multiple choice QuizQuestion Constructor. Can only be answered with CheckBox choices.
     */

    public QuizQuestion(String questionText, int questionNumber, int quizImage, String choice1, String choice2, String choice3, String choice4, boolean answeredCorrectly) {
        mQuestionText = questionText;
        mQuestionNumber = questionNumber;
        mQuestionImage = quizImage;
        mAnsweredCorrectly = answeredCorrectly;
        mChoice1 = choice1;
        mChoice2 = choice2;
        mChoice3 = choice3;
        mChoice4 = choice4;
    }

    public int getQuestionNumber() {
        return mQuestionNumber;
    }

    public String getChoice1() {
        return mChoice1;
    }

    public String getChoice4() {
        return mChoice4;
    }

    public String getChoice3() {
        return mChoice3;
    }

    public String getChoice2() {
        return mChoice2;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public int getQuestionImage() {
        return mQuestionImage;
    }

    public boolean isAnsweredCorrectly() {
        return mAnsweredCorrectly;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        mAnsweredCorrectly = answeredCorrectly;
    }
}
