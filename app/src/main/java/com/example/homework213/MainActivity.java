package com.example.homework213;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private Button chooseStartDate;
    private Button chooseEndDate;
    private Button btnOK;
    private CalendarView startDateCalendar;
    private CalendarView endDateCalendar;
    private long startDate;
    private long endDate;
    private String startDateText;
    private String endDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //обрабатываем события показа календаря
        chooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDateCalendar.setVisibility(View.GONE);
                startDateCalendar.setVisibility(View.VISIBLE);
            }
        });
        chooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDateCalendar.setVisibility(View.GONE);
                endDateCalendar.setVisibility(View.VISIBLE);
            }
        });
        //обрабатываем события выбора дат
        startDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                startDateText = i2+"."+i1 +"."+ i;
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.set(i,i1,i2);
                startDate = calendar.getTimeInMillis();
                chooseStartDate.setText(getString(R.string.btn_start)+"\n"+startDateText);
                startDateCalendar.setVisibility(View.GONE);
            }
        });
        endDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                endDateText = i2+"."+i1 +"."+ i;
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.set(i,i1,i2);
                endDate = calendar.getTimeInMillis();
                chooseEndDate.setText(getString(R.string.btn_end)+"\n"+endDateText);
                endDateCalendar.setVisibility(View.GONE);
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result="";
                if(startDate<endDate){
                    result=getString(R.string.btn_start) +": "+ startDateText + ", "+getString(R.string.btn_end) +": " + endDateText;
                }else{
                    result=getString(R.string.toast_error);
                }
                chooseStartDate.setText(getString(R.string.btn_start));
                chooseEndDate.setText(getString(R.string.btn_end));
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initView(){
        chooseStartDate = findViewById(R.id.chooseStartDate);
        chooseEndDate = findViewById(R.id.chooseEndDate);
        btnOK = findViewById(R.id.btnOK);
        startDateCalendar = findViewById(R.id.startDateCalendar);
        endDateCalendar = findViewById(R.id.endDateCalendar);
        //скрываем поля
        startDateCalendar.setVisibility(View.GONE);
        endDateCalendar.setVisibility(View.GONE);
    }
}
