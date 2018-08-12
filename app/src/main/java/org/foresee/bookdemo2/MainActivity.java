package org.foresee.bookdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mTrueBtn;
    Button mFalseBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrueBtn=findViewById(R.id.true_btn);
        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast msg=Toast.makeText(MainActivity.this, R.string.correct_msg, Toast.LENGTH_LONG);
                msg.setGravity(Gravity.TOP, 0, 0);
                msg.show();
            }
        });
        mFalseBtn=findViewById(R.id.false_btn);
        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast msg = Toast.makeText(MainActivity.this, R.string.incorrect_msg, Toast.LENGTH_LONG);
                msg.setGravity(Gravity.TOP, 0,0);
                msg.show();
            }
        });
    }
}
