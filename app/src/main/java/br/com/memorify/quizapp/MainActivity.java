package br.com.memorify.quizapp;

import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView answerOneTextView;
    private TextView answerTwoTextView;
    private TextView answerThreeTextView;
    private TextView answerFourTextView;

    private RadioButton questionOneSecondAnswerRadio;

    private CheckBox questionTwoFirstAnswerCheck;
    private CheckBox questionTwoSecondAnswerCheck;
    private CheckBox questionTwoThirdAnswerCheck;
    private CheckBox questionTwoFourthAnswerCheck;
    private CheckBox questionTwoFifthAnswerCheck;
    private CheckBox questionTwoSixthAnswerCheck;

    private RadioButton questionThreeThirdAnswerRadio;

    private EditText questionFourAnswerEditText;

    private boolean isQuestionOneCorrect;
    private boolean isQuestionTwoCorrect;
    private boolean isQuestionThreeCorrect;
    private boolean isQuestionFourCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        answerOneTextView = (TextView) findViewById(R.id.question_one_answer);
        answerTwoTextView = (TextView) findViewById(R.id.question_two_answer);
        answerThreeTextView = (TextView) findViewById(R.id.question_three_answer);
        answerFourTextView = (TextView) findViewById(R.id.question_four_answer);
        questionOneSecondAnswerRadio = (RadioButton) findViewById(R.id.question_one_second_answer);
        questionThreeThirdAnswerRadio = (RadioButton) findViewById(R.id.question_three_third_answer);
        questionTwoFirstAnswerCheck = (CheckBox) findViewById(R.id.question_two_first_answer);
        questionTwoSecondAnswerCheck = (CheckBox) findViewById(R.id.question_two_second_answer);
        questionTwoThirdAnswerCheck = (CheckBox) findViewById(R.id.question_two_third_answer);
        questionTwoFourthAnswerCheck = (CheckBox) findViewById(R.id.question_two_fourth_answer);
        questionTwoFifthAnswerCheck = (CheckBox) findViewById(R.id.question_two_fifth_answer);
        questionTwoSixthAnswerCheck = (CheckBox) findViewById(R.id.question_two_sixth_answer);
        questionFourAnswerEditText = (EditText) findViewById(R.id.question_four_answer_text);
    }

    /**
     * Handle clicks on submit button. Should calculate user points and display on a Toast. Also
     * should display all question answers
     */
    public void submit(View view) {
        checkAnswers();
        displayAnswers();
        displayResultMessage();
    }

    private void displayResultMessage() {
        int correctAnswers = isQuestionOneCorrect ? 1 : 0;
        correctAnswers += isQuestionTwoCorrect ? 1 : 0;
        correctAnswers += isQuestionThreeCorrect ? 1 : 0;
        correctAnswers += isQuestionFourCorrect ? 1 : 0;
        Toast.makeText(getBaseContext(), getString(R.string.result_message, correctAnswers, correctAnswers * 10), Toast.LENGTH_LONG).show();
    }

    private void checkAnswers() {
        isQuestionOneCorrect = questionOneSecondAnswerRadio.isChecked();
        isQuestionTwoCorrect = isOnlyCheckSecondAndSixCheckbox();
        isQuestionThreeCorrect = questionThreeThirdAnswerRadio.isChecked();
        isQuestionFourCorrect = questionFourAnswerEditText.getText().toString().trim().equalsIgnoreCase(getString(R.string.question_four_correct_answer));
    }

    private boolean isOnlyCheckSecondAndSixCheckbox() {
        return !questionTwoFirstAnswerCheck.isChecked() &&
               questionTwoSecondAnswerCheck.isChecked() &&
               !questionTwoThirdAnswerCheck.isChecked() &&
               !questionTwoFourthAnswerCheck.isChecked() &&
               !questionTwoFifthAnswerCheck.isChecked() &&
               questionTwoSixthAnswerCheck.isChecked();
    }

    private void displayAnswers() {
        showAnswerForTextView(answerOneTextView, R.string.question_one_answer, isQuestionOneCorrect);
        showAnswerForTextView(answerTwoTextView, R.string.question_two_answer, isQuestionTwoCorrect);
        showAnswerForTextView(answerThreeTextView, R.string.question_three_answer, isQuestionThreeCorrect);
        showAnswerForTextView(answerFourTextView, R.string.question_four_answer, isQuestionFourCorrect);
    }

    private void showAnswerForTextView(TextView answerTextView, @StringRes int answerText,  boolean isCorrect) {
        answerTextView.setVisibility(View.VISIBLE);
        answerTextView.setText(getString(answerText, getString(isCorrect ? R.string.answer_correct : R.string.answer_wrong)));
        answerTextView.setTextColor(ContextCompat.getColor(getBaseContext(), isCorrect ? R.color.dark_green : R.color.red));
    }
}
