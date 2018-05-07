package com.nphan.android.sengokuquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SengokuActivity extends AppCompatActivity {

    private TextView mQuestionTextView;
    private Button mOptionA_Button;
    private Button mOptionB_Button;
    private Button mOptionC_Button;
    private Button mOptionD_Button;
    private Button mNextButton;
    
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_1, R.string.question_1_A, R.string.question_1_B, R.string.question_1_C, R.string.question_1_D, R.string.question_1_answer),
            new Question(R.string.question_2, R.string.question_2_A, R.string.question_2_B, R.string.question_2_C, R.string.question_2_D, R.string.question_2_answer)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sengoku);


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

        updateQuestion();
    }

    private void updateQuestion() {
        Question question = mQuestionBank[mCurrentIndex];
        mQuestionTextView.setText(question.getTextId());
        mOptionA_Button.setText(question.getOptionAId());
        mOptionB_Button.setText(question.getOptionBId());
        mOptionC_Button.setText(question.getOptionCId());
        mOptionD_Button.setText(question.getOptionDId());
    }

    private void checkAnswer(int userAnswer) {
        int answerId = mQuestionBank[mCurrentIndex].getAnswerId();
        String answer = getResources().getString(answerId);

        int toastMessageId = R.string.incorrect_toast;
        if (userAnswer == Integer.valueOf(answer)) {
            toastMessageId = R.string.correct_toast;
        }

        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT)
                .show();
    }
}
