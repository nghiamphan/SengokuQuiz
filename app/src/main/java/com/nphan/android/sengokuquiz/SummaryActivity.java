package com.nphan.android.sengokuquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private static final String EXTRA_CORRECT_ANSWERS = "com.nphan.android.sengokuquiz.number_correct_answers";
    private static final String EXTRA_TOTAL_ANSWERED = "com.nphan.android.sengokuquiz.total_questions_answered";

    private TextView mNumberCorrectAnswersTextView;
    private TextView mTotalAnsweredTextView;
    private TextView mScoreTextView;
    private TextView mBuildVersionTextView;

    private int mNumberCorrectAnswers;
    private int mTotalAnswered;

    public static Intent newIntent(Context context, int numberCorrectAnswers, int totalAnswered) {
        Intent intent = new Intent(context, SummaryActivity.class);
        intent.putExtra(EXTRA_CORRECT_ANSWERS, numberCorrectAnswers);
        intent.putExtra(EXTRA_TOTAL_ANSWERED, totalAnswered);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        mNumberCorrectAnswers = getIntent().getIntExtra(EXTRA_CORRECT_ANSWERS, 0);
        mTotalAnswered = getIntent().getIntExtra(EXTRA_TOTAL_ANSWERED, 0);

        mNumberCorrectAnswersTextView = findViewById(R.id.number_correct_answers_text_view);
        String correctAnswersText = getResources().getString(R.string.number_correct_answers);
        mNumberCorrectAnswersTextView.setText(correctAnswersText + " " + mNumberCorrectAnswers);

        mTotalAnsweredTextView = findViewById(R.id.total_answers_text_view);
        String totalAnsweredText = getResources().getString(R.string.total_answers);
        mTotalAnsweredTextView.setText(totalAnsweredText + " " + mTotalAnswered);

        mScoreTextView = findViewById(R.id.score_text_view);
        float score = 0;
        if (mTotalAnswered != 0) {
            score = (float) mNumberCorrectAnswers / mTotalAnswered * 100;
        }
        String scoreText = getResources().getString(R.string.score);
        mScoreTextView.setText(scoreText + " " + String.format("%.2f", score) + "%");

        mBuildVersionTextView = findViewById(R.id.build_version_text_view);
        mBuildVersionTextView.setText("API Level: " + Integer.toString(Build.VERSION.SDK_INT));
    }
}
