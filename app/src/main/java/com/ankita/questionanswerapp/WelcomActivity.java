package com.ankita.questionanswerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNormalTest,btnCountDownTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        init();
        btnNormalTest.setOnClickListener(this);
        btnCountDownTest.setOnClickListener(this);
    }

    private void init() {
        btnNormalTest=findViewById(R.id.btnNormalTest);
        btnCountDownTest=findViewById(R.id.btnCountDownTest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNormalTest:
                startActivity(new Intent(WelcomActivity.this,MainActivity.class));
                break;
            case R.id.btnCountDownTest:
                startActivity(new Intent(WelcomActivity.this,CountDownTestActivity.class));
                break;
        }

    }
}
