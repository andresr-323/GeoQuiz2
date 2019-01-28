package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTruebtn;
    private Button mFalsebtn;
    private TextView qText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTruebtn = findViewById(R.id.true_btn);
        mTruebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            }
            //add toast
        });
        mFalsebtn = findViewById(R.id.false_btn);
        mFalsebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
            //add toast
        });

        qText = findViewById(R.id.question_text);

    }
}
