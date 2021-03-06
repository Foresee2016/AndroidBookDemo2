package org.foresee.bookdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_RESULT = "result";
    private static final String KEY_COUNTER = "count";
    private Button mTrueBtn;
    private Button mFalseBtn;
    private ImageButton mPrevBtn;
    private ImageButton mNextBtn;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };
    private int[] mResult = new int[mQuestionBank.length]; // 记录回答结果，-1未答，0答错，1答对
    private int mCounter = 0;
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mResult = savedInstanceState.getIntArray(KEY_RESULT);
            mCounter = savedInstanceState.getInt(KEY_COUNTER);
        } else {
            Arrays.fill(mResult, -1);
        }

        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() { // 单击题目文本时下一题
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mTrueBtn = findViewById(R.id.true_btn);
        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        mFalseBtn = findViewById(R.id.false_btn);
        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        mPrevBtn = findViewById(R.id.prev_btn);
        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex--;
                if (mCurrentIndex < 0) {
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                updateQuestion();
            }
        });
        mNextBtn = findViewById(R.id.next_btn);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putIntArray(KEY_RESULT, mResult);
        outState.putInt(KEY_COUNTER, mCounter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        setAnswerBtnVisible(mResult[mCurrentIndex] == -1 ? View.VISIBLE : View.INVISIBLE);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        mResult[mCurrentIndex] = answerIsTrue ? 0 : 1;
        mCounter++;
        setAnswerBtnVisible(View.INVISIBLE);
        if (mCounter == mQuestionBank.length) {
            int score = 0;
            for (int res : mResult) {
                score += res;
            }
            double rate = (double) score / mResult.length;
            String percent = String.format(Locale.getDefault(), "已全部作答，回答正确率：%.2f", rate * 100);
            Toast.makeText(this, percent, Toast.LENGTH_LONG).show();
            return;
        }
        int messageResId = userPressedTrue == answerIsTrue ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
    private void setAnswerBtnVisible(int visible){
        mTrueBtn.setVisibility(visible);
        mFalseBtn.setVisibility(visible);
    }
}
