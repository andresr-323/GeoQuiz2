package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private Button mTruebtn;
    private Button mFalsebtn;
    private Button mNextbtn;
    private Button mBackbtn;
    private Button mCheatbtn;
    private TextView mText;
    private boolean mIsCheater;
    private static final int REQUEST_CODE_CHEAT = 0;

    private int qNum = 0;
    private quizQ[] questions = new quizQ[]{
            new quizQ(R.string.question_1, true),
            new quizQ(R.string.question_2, false),
            new quizQ(R.string.question_3, true),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mText = (TextView) findViewById(R.id.question_text);
        updateQuestion();

        mTruebtn = findViewById(R.id.true_btn);
        mTruebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(mIsCheater){
                    Toast.makeText(QuizActivity.this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
                }else {
                    if (questions[qNum].isTrueFalse()) {
                        Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        mFalsebtn = findViewById(R.id.false_btn);
        mFalsebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(mIsCheater){
                    Toast.makeText(QuizActivity.this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
                }else {
                    if (!questions[qNum].isTrueFalse()) {
                        Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mNextbtn = findViewById(R.id.next_Btn);
        mNextbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Log.d("TAG", String.valueOf(qNum));
                qNum = (qNum + 1) % questions.length;
                updateQuestion();

            }
        });

        mText = findViewById(R.id.question_text);
        mText.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                    qNum = (qNum + 1) % questions.length;
                updateQuestion();
            }
        });

        mBackbtn = findViewById(R.id.back_btn);
        mBackbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(qNum == 0){
                    qNum = questions.length;
                    updateQuestion();
                }else{
                    qNum = (qNum - 1) % questions.length;
                    updateQuestion();
                }
            }
        });

        mCheatbtn = findViewById(R.id.cheat_btn);
        mCheatbtn.setOnClickListener(new View.OnClickListener(){
         public void onClick(View v){
             //Intent i = new Intent(QuizActivity.this, CheatActivity.class);
             boolean ansIsTrue = questions[qNum].isTrueFalse();
             Intent i = CheatActivity.newIntent(QuizActivity.this, ansIsTrue);
             //startActivity(i);
             startActivityForResult(i, REQUEST_CODE_CHEAT);
         }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(data == null){
            return;
        }

        mIsCheater = CheatActivity.wasAnswerShown(data);
    }

    private void updateQuestion(){
        mIsCheater = false;
        int q = questions[qNum].getQuizQuestid();
        mText.setText(q);
    }

}

 class quizQ{
        boolean trueFalse;
        int quizQuestid;

        quizQ(int qTxt, boolean TorF){
            trueFalse = TorF;
            quizQuestid = qTxt;
        }

     public boolean isTrueFalse() {
         return trueFalse;
     }

     public int getQuizQuestid() {
         return quizQuestid;
     }
 }
