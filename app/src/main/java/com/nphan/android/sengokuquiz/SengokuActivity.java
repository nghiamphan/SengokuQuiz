package com.nphan.android.sengokuquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SengokuActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";
    private static final String ALREADY_ANSWERED = "already_answered";
    private static final String CORRECT_ANSWERED = "number of correct answers";
    private static final String TOTAL_ANSWERED = "number of questions answered";

    private TextView mQuestionTextView;
    private Button mOptionA_Button;
    private Button mOptionB_Button;
    private Button mOptionC_Button;
    private Button mOptionD_Button;
    private Button mNextButton;
    private Button mFinishButton;
    private TextView mNumberCorrectAnswersTextView;
    
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_1, R.string.question_1_A, R.string.question_1_B, R.string.question_1_C, R.string.question_1_D, R.string.question_1_answer),
            new Question(R.string.question_2, R.string.question_2_A, R.string.question_2_B, R.string.question_2_C, R.string.question_2_D, R.string.question_2_answer)
    };

    private boolean[] mAlreadyAnswered = new boolean[mQuestionBank.length]; // default value = false

    private int mCurrentIndex = 0;
    private int mNumberCorrectAnswers = 0;
    private int mTotalAnswered = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sengoku);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mAlreadyAnswered = savedInstanceState.getBooleanArray(ALREADY_ANSWERED);
            mNumberCorrectAnswers = savedInstanceState.getInt(CORRECT_ANSWERED, 0);
            mTotalAnswered = savedInstanceState.getInt(TOTAL_ANSWERED, 0);
        }

        mQuestionTextView = findViewById(R.id.question_text_view);

        mOptionA_Button = findViewById(R.id.option_a_button_view);
        mOptionA_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0);
            }
        });

        mOptionB_Button = findViewById(R.id.option_b_button_view);
        mOptionB_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        mOptionC_Button = findViewById(R.id.option_c_button_view);
        mOptionC_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });

        mOptionD_Button = findViewById(R.id.option_d_button_view);
        mOptionD_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3);
            }
        });

        mNextButton = findViewById(R.id.next_button_view);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex < mQuestionBank.length - 1) {
                    mCurrentIndex += 1;
                    updateQuestion();
                }
            }
        });

        mNumberCorrectAnswersTextView = findViewById(R.id.number_correct_answers_text_view);

        mFinishButton = findViewById(R.id.finish_button_view);
        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SummaryActivity.newIntent(SengokuActivity.this, mNumberCorrectAnswers, mTotalAnswered);
                startActivity(intent);
            }
        });

        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putBooleanArray(ALREADY_ANSWERED, mAlreadyAnswered);
        savedInstanceState.putInt(CORRECT_ANSWERED, mNumberCorrectAnswers);
        savedInstanceState.putInt(TOTAL_ANSWERED, mTotalAnswered);
    }

    private void updateQuestion() {
        Question question = mQuestionBank[mCurrentIndex];
        mQuestionTextView.setText(question.getTextId());
        mOptionA_Button.setText(question.getOptionAId());
        mOptionB_Button.setText(question.getOptionBId());
        mOptionC_Button.setText(question.getOptionCId());
        mOptionD_Button.setText(question.getOptionDId());
        updateButtons();
        updateScore();
    }

    private void checkAnswer(int userAnswer) {
        int answerId = mQuestionBank[mCurrentIndex].getAnswerId();
        String answer = getResources().getString(answerId);

        int toastMessageId = R.string.incorrect_toast;
        if (userAnswer == Integer.valueOf(answer)) {
            toastMessageId = R.string.correct_toast;
            mNumberCorrectAnswers += 1;
        }
        mTotalAnswered += 1;

        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT)
                .show();

        mAlreadyAnswered[mCurrentIndex] = true;
        updateButtons();
        updateScore();
    }

    private void updateButtons() {
        if (mAlreadyAnswered[mCurrentIndex]) {
            mOptionA_Button.setEnabled(false);
            mOptionB_Button.setEnabled(false);
            mOptionC_Button.setEnabled(false);
            mOptionD_Button.setEnabled(false);
            mNextButton.setEnabled(true);
        }
        else {
            mOptionA_Button.setEnabled(true);
            mOptionB_Button.setEnabled(true);
            mOptionC_Button.setEnabled(true);
            mOptionD_Button.setEnabled(true);
            mNextButton.setEnabled(false);
        }
    }

    private void updateScore() {
        String correctAnswersText = getResources().getString(R.string.number_correct_answers);
        mNumberCorrectAnswersTextView.setText(correctAnswersText + " " + mNumberCorrectAnswers); // Later, use resource with placeholders
    }
}
