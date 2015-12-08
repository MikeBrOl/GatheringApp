package janco.gatheringapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Date;

import janco.gatheringapp.R;

public class CreateNoticeSelectDateAndTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice_select_date_and_time);
    }

    public void returnToCreateNotice(View view)
    {
        finish();
    }

    public void selectedDateAndTime(View view)
    {
        DatePicker createNoticeDatePicker = (DatePicker) findViewById(R.id.createNoticeDatePicker);
        TimePicker createNoticeTimerPicker = (TimePicker) findViewById(R.id.createNoticeTimePicker);

        Date selectedDateAndTime = new Date();
        selectedDateAndTime.setHours(createNoticeTimerPicker.getCurrentHour());
        selectedDateAndTime.setMinutes(createNoticeTimerPicker.getCurrentMinute());
        selectedDateAndTime.setYear(createNoticeDatePicker.getYear());
        selectedDateAndTime.setMonth(createNoticeDatePicker.getMonth());
        selectedDateAndTime.setDate(createNoticeDatePicker.getDayOfMonth());

        Bundle conData = new Bundle();
        conData.putString("param_result", selectedDateAndTime.toString());
        Intent intent = new Intent();
        intent.putExtras(conData);
        setResult(RESULT_OK, intent);
        finish();

    }

}
