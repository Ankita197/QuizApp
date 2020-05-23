package com.ankita.questionanswerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ankita.questionanswerapp.adapter.ItemOptionAdapter;
import com.ankita.questionanswerapp.adapter.OnItemClick;
import com.ankita.questionanswerapp.model.Option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class CountDownTestActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvTime, tvQuestionNumber, tvQuestionDescription;
    private boolean timerRunning=false;
    private int min = 1, second = 60, i = 0, skipCount=0,writeAnswer=0;
    private boolean setSubmit=false;
    private HashMap<Integer,Boolean> mapSelectedOrNot=new HashMap<>();
    private ArrayList<Option> optionArrayList;
    private Button btnNext;
    private CountDownTimer countDownTimer;

    private RecyclerView rvItemOption;
    private ItemOptionAdapter itemOptionAdapter;
    private BaseActivity baseActivity=new BaseActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_test);
        init();
        baseActivity.setFinalAnswer();
        setCountDownTimer();
        baseActivity.addQuestionInList();
        initMap();
        baseActivity.addOptions();
        tvQuestionDescription.setText(baseActivity.getQuestion(0));
        setAdapter();
        btnNext.setOnClickListener(this);

        itemOptionAdapter.setClick(new OnItemClick() {
            @Override
            public void OnClick(ItemOptionAdapter.ViewHolder holder, int position) {
                Log.d("___!___", itemOptionAdapter.getSelectedPos() + "final answer is");
                if(baseActivity.getFinalAnswer(i)!=position){
                    itemOptionAdapter.setWrongAnswerPosition(position);
                }
                else {
                    writeAnswer+=1;
                }
                if(!mapSelectedOrNot.get(i)) {
                    itemOptionAdapter.setAnswer(baseActivity.getFinalAnswer(i));
                    itemOptionAdapter.selectedItem();
                }
                mapSelectedOrNot.put(i,true);
                //TODO
                btnNext.setEnabled(true);
            }
        });
    }

    private void initMap() {
        for(int i=0;i<baseActivity.getTotalSize();i++){
            mapSelectedOrNot.put(i,false);
        }
    }

    private void setAdapter() {
        if (baseActivity.getTotalSize() > 0) {
            setAnswerList(baseActivity.getOptions(i));
            itemOptionAdapter.setList(optionArrayList);
            rvItemOption.setAdapter(itemOptionAdapter);
        } else {
            Toast.makeText(this, "list is empty", Toast.LENGTH_LONG).show();
        }
    }



    //set count down timer
    private void setCountDownTimer() {
        countDownTimer=new CountDownTimer(120000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerRunning=true;
                min=(int) (millisUntilFinished/1000)/60;
                second=(int) (millisUntilFinished/1000)%60;
                String setTime=String.format(Locale.getDefault(),"%02d:%02d",min,second);
                tvTime.setText("Time:  " + setTime);
            }

            @Override
            public void onFinish() {
                if(i<baseActivity.getTotalSize()-1){
                    timerRunning=false;
                    getNextQuestionData();
                }
                else {
                    showResult();
                }
            }
        }.start();
    }

    private void startShowResultActivity() {
        Intent intent =new Intent(CountDownTestActivity.this,ShowResultActivity.class);
        intent.putExtra("correctAnswer",String.valueOf(writeAnswer));
        intent.putExtra("wrongAnswer",String.valueOf(baseActivity.getTotalSize()-writeAnswer-skipCount));
        intent.putExtra("skipAnswer",String.valueOf(skipCount));
        Log.d("___@___","Write Answer is"+writeAnswer);
        Log.d("___@___","Wrong Answer is"+(baseActivity.getTotalSize()-writeAnswer-skipCount));
        Log.d("___@___","skip Answer is"+skipCount);

        startActivity(intent);
        finish();
    }


    private void init() {
        tvTime = findViewById(R.id.tvTime);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestionDescription = findViewById(R.id.tvQuestionDescription);
        btnNext = findViewById(R.id.btnNext);
        rvItemOption = findViewById(R.id.rvItemOption);
        itemOptionAdapter = new ItemOptionAdapter(this);
        optionArrayList = new ArrayList<>();
    }

    private void setAnswerList(ArrayList<String> options) {
        optionArrayList.clear();
        for (int i = 0; i < options.size(); i++) {
            optionArrayList.add(new Option("option " + (i + 1), options.get(i)));
        }

    }

    private void getNextQuestionData() {
        i += 1;
        tvQuestionNumber.setText(String.valueOf(i + 1));
        tvQuestionDescription.setText(baseActivity.getQuestion(i));
        setAnswerList(baseActivity.getOptions(i));
        itemOptionAdapter.setAnswer(-1);
        itemOptionAdapter.setSelectedPos(-1);
        itemOptionAdapter.setWrongAnswerPosition(-1);
        itemOptionAdapter.notifyDataSetChanged();
        if(timerRunning){
            timerRunning=false;
            countDownTimer.cancel();
        }
        setCountDownTimer();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNext:
                min = 1;
                second = 60;
                if (i < baseActivity.getTotalSize() - 1) {
                    Log.d("___@___", "i is" + i);
                    getNextQuestionData();

                }
                if (setSubmit) {
                    showResult();
                }
                if (i == baseActivity.getTotalSize() - 1) {
                    btnNext.setText("Submit");
                    setSubmit = true;
                }

                break;
        }
    }

    private void showResult() {
        for(int i=0;i<baseActivity.getTotalSize();i++){
            if(!mapSelectedOrNot.get(i)){
                skipCount+=1;
            }
        }
        startShowResultActivity();
    }
}
