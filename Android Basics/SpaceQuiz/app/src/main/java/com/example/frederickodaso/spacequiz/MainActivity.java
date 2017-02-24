package com.example.frederickodaso.spacequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by frederickodaso on 1/21/17.
 */
public class MainActivity extends AppCompatActivity {


    QuizQuestion quizQuestion1 = new QuizQuestion("True or False: The Earth is an inner planet.", 1, R.drawable.quizimage1, false);
    QuizQuestion quizQuestion2 = new QuizQuestion("How many planets are in the Solar System?", 2, R.drawable.quizimage2, false);
    QuizQuestion quizQuestion3 = new QuizQuestion("Which planets are outer planets?", 3, R.drawable.quizimage3, "Mars", "Jupiter", "Uranus", "Neptune", false);
    QuizQuestion quizQuestion4 = new QuizQuestion("Which planets have rings?", 4, R.drawable.quizimage4, "Mercury", "Venus", "Saturn", "Neptune", false);
    QuizQuestion currentQuestion;
    boolean currentTrueFalseAnswer;
    String currentNumberEntryAnswer;
    boolean checkBox1Checked;
    boolean checkBox2Checked;
    boolean checkBox3Checked;
    boolean checkBox4Checked;
    EditText editTextNumber;
    RadioButton trueButton;
    RadioButton falseButton;
    Button prevButton;
    Button nextButton;
    Button playAgainButton;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    TextView questionTextView;
    ImageView questionImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = (EditText) findViewById(R.id.number_edittext);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        trueButton = (RadioButton) findViewById(R.id.true_button);
        falseButton = (RadioButton) findViewById(R.id.false_button);
        prevButton = (Button) findViewById(R.id.prev_button);
        playAgainButton = (Button) findViewById(R.id.playAgain_button);
        nextButton = (Button) findViewById(R.id.next_button);

        currentQuestion = quizQuestion1;
        displayQuizInfo(quizQuestion1);
        displayQuizImage(quizQuestion1);
    }

    public void displayQuizInfo(QuizQuestion question) {
        questionTextView = (TextView) findViewById(R.id.question_text_view);
        questionTextView.setText(question.getQuestionText());


        if (question == quizQuestion1) {
            editTextNumber.setVisibility(View.INVISIBLE);
            checkBox1.setVisibility(View.INVISIBLE);
            checkBox2.setVisibility(View.INVISIBLE);
            checkBox3.setVisibility(View.INVISIBLE);
            checkBox4.setVisibility(View.INVISIBLE);
            trueButton.setVisibility(View.VISIBLE);
            falseButton.setVisibility(View.VISIBLE);
            prevButton.setVisibility(View.INVISIBLE);
            playAgainButton.setVisibility(View.INVISIBLE);
        }
        else if (question == quizQuestion2) {
            editTextNumber.setVisibility(View.VISIBLE);
            trueButton.setVisibility(View.INVISIBLE);
            falseButton.setVisibility(View.INVISIBLE);
            prevButton.setVisibility(View.VISIBLE);
            checkBox1.setVisibility(View.INVISIBLE);
            checkBox2.setVisibility(View.INVISIBLE);
            checkBox3.setVisibility(View.INVISIBLE);
            checkBox4.setVisibility(View.INVISIBLE);
        }

        else if (question == quizQuestion3) {
            editTextNumber.setVisibility(View.INVISIBLE);
            checkBox1.setChecked(false);
            checkBox2.setChecked(false);
            checkBox3.setChecked(false);
            checkBox4.setChecked(false);
            checkBox1.setVisibility(View.VISIBLE);
            checkBox2.setVisibility(View.VISIBLE);
            checkBox3.setVisibility(View.VISIBLE);
            checkBox4.setVisibility(View.VISIBLE);
            checkBox1.setText(quizQuestion3.getChoice1());
            checkBox2.setText(quizQuestion3.getChoice2());
            checkBox3.setText(quizQuestion3.getChoice3());
            checkBox4.setText(quizQuestion3.getChoice4());
        }

        else if (question == quizQuestion4) {
            checkBox1.setChecked(false);
            checkBox2.setChecked(false);
            checkBox3.setChecked(false);
            checkBox4.setChecked(false);
            checkBox1.setText(quizQuestion4.getChoice1());
            checkBox2.setText(quizQuestion4.getChoice2());
            checkBox3.setText(quizQuestion4.getChoice3());
            checkBox4.setText(quizQuestion4.getChoice4());
            nextButton.setText(R.string.submit);

        }
    }

    public void displayQuizImage(QuizQuestion question) {
        questionImageView = (ImageView) findViewById(R.id.quizPicture_image_view);
        questionImageView.setImageResource(question.getQuestionImage());
    }

    public void answerChoiceTrue (View view) {
        currentTrueFalseAnswer = true;
    }

    public void answerChoiceFalse (View view) {
        currentTrueFalseAnswer = false;
    }

    public void checkQuestion1Answer() {
        if (currentTrueFalseAnswer) {
            quizQuestion1.setAnsweredCorrectly(true);
        }

        else {
            quizQuestion1.setAnsweredCorrectly(false);
        }
    }

    public void checkQuestion2Answer() {
        currentNumberEntryAnswer = editTextNumber.getText().toString().trim();

        if (currentNumberEntryAnswer.compareTo("8") == 0) {
            quizQuestion2.setAnsweredCorrectly(true);
        }

        else {
            quizQuestion2.setAnsweredCorrectly(false);
        }
    }

    public void checkQuestion3Answer() {
        checkBox1Checked = checkBox1.isChecked();
        checkBox2Checked = checkBox2.isChecked();
        checkBox3Checked = checkBox3.isChecked();
        checkBox4Checked = checkBox4.isChecked();
        if (!checkBox1Checked && checkBox2Checked && checkBox3Checked && checkBox4Checked) {
            quizQuestion3.setAnsweredCorrectly(true);
        }
        else {
            quizQuestion3.setAnsweredCorrectly(false);
        }
    }

    public void checkQuestion4Answer() {
        checkBox1Checked = checkBox1.isChecked();
        checkBox2Checked = checkBox2.isChecked();
        checkBox3Checked = checkBox3.isChecked();
        checkBox4Checked = checkBox4.isChecked();
        if (!checkBox1Checked && !checkBox2Checked && checkBox3Checked && checkBox4Checked) {
            quizQuestion4.setAnsweredCorrectly(true);
        }
        else {
            quizQuestion4.setAnsweredCorrectly(false);
        }
    }

    public void nextQuestion (View view) {
        if (currentQuestion == quizQuestion1) {
            checkQuestion1Answer();
            currentQuestion = quizQuestion2;
            displayQuizInfo(quizQuestion2);
            displayQuizImage(quizQuestion2);
        }
        else if (currentQuestion == quizQuestion2) {
            checkQuestion2Answer();
            currentQuestion = quizQuestion3;
            displayQuizInfo(quizQuestion3);
            displayQuizImage(quizQuestion3);

        }
        else if (currentQuestion == quizQuestion3) {
            checkQuestion3Answer();
            currentQuestion = quizQuestion4;
            displayQuizInfo(quizQuestion4);
            displayQuizImage(quizQuestion4);


        }
        else if (currentQuestion == quizQuestion4) {
            checkQuestion4Answer();
            nextButton.setVisibility(View.INVISIBLE);
            prevButton.setVisibility(View.INVISIBLE);
            playAgainButton.setVisibility(View.VISIBLE);
            checkBox1.setVisibility(View.INVISIBLE);
            checkBox2.setVisibility(View.INVISIBLE);
            checkBox3.setVisibility(View.INVISIBLE);
            checkBox4.setVisibility(View.INVISIBLE);
            questionTextView.setVisibility(View.INVISIBLE);
            Intent nameIntent = getIntent();
            String name = nameIntent.getStringExtra(StartActvity.EXTRA_NAME);
            if (quizQuestion1.isAnsweredCorrectly() && quizQuestion2.isAnsweredCorrectly() &&
                    quizQuestion3.isAnsweredCorrectly() && quizQuestion4.isAnsweredCorrectly()) {
                int score = 4;
                questionImageView.setImageResource(R.drawable.quizimagewin);
                Toast.makeText(MainActivity.this, "Your Score: " + score + " out of 4.", Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Congratulations " + name + "!" + " You answered all four correctly!", Toast.LENGTH_SHORT).show();
            }
            else {
                int incorrect = 0;
                questionImageView.setImageResource(R.drawable.quizimageloss);
                if (!quizQuestion1.isAnsweredCorrectly()) {
                    incorrect += 1;
                    Toast.makeText(MainActivity.this, name + ", you answered Question " + quizQuestion1.getQuestionNumber() + " incorrectly.", Toast.LENGTH_SHORT).show();
                }
                if (!quizQuestion2.isAnsweredCorrectly()) {
                    incorrect += 1;
                    Toast.makeText(MainActivity.this, name + ", you answered Question " + quizQuestion2.getQuestionNumber() + " incorrectly.", Toast.LENGTH_SHORT).show();
                }
                if (!quizQuestion3.isAnsweredCorrectly()) {
                    incorrect += 1;
                    Toast.makeText(MainActivity.this, name + ", you answered Question " + quizQuestion3.getQuestionNumber() + " incorrectly.", Toast.LENGTH_SHORT).show();
                }
                if (!quizQuestion4.isAnsweredCorrectly()) {
                    incorrect += 1;
                    Toast.makeText(MainActivity.this, name + ", you answered Question " + quizQuestion4.getQuestionNumber() + " incorrectly.", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Your Score: " + incorrect + " out of 4.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void previousQuestion(View view) {
        if (currentQuestion == quizQuestion2) {
            currentQuestion = quizQuestion1;
            displayQuizInfo(quizQuestion1);
            displayQuizImage(quizQuestion1);
        }

        else if (currentQuestion == quizQuestion3) {
            currentQuestion = quizQuestion2;
            displayQuizInfo(quizQuestion2);
            displayQuizImage(quizQuestion2);
        }

        else if (currentQuestion == quizQuestion4) {
            currentQuestion = quizQuestion3;
            displayQuizInfo(quizQuestion3);
            displayQuizImage(quizQuestion3);
        }
    }

    public void playAgain(View view) {
        Intent restartIntent = new Intent(view.getContext(), StartActvity.class);
        startActivity(restartIntent);
    }
}
