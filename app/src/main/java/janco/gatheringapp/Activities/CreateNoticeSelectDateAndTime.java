package janco.gatheringapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        TimePicker timePicker = (TimePicker) findViewById(R.id.createNoticeTimePicker);
        timePicker.setIs24HourView(true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void returnToCreateNotice(View view)
    {
        finish();
    }

    public void selectedDateAndTime(View view)
    {
        DatePicker createNoticeDatePicker = (DatePicker) findViewById(R.id.createNoticeDatePicker);
        TimePicker createNoticeTimerPicker = (TimePicker) findViewById(R.id.createNoticeTimePicker);
        StringBuilder selectedDateAndTime = new StringBuilder();
        selectedDateAndTime.append(createNoticeDatePicker.getYear()+"-");
        selectedDateAndTime.append((createNoticeDatePicker.getMonth()+1)+"-");
        selectedDateAndTime.append(createNoticeDatePicker.getDayOfMonth()+" ");
        selectedDateAndTime.append(createNoticeTimerPicker.getCurrentHour()+":");
        selectedDateAndTime.append(createNoticeTimerPicker.getCurrentMinute()+":00");

        Log.e("Selected Date and Time", selectedDateAndTime.toString());

        //TODO Find an alternative to using preferences to save date.
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("Date", selectedDateAndTime.toString());
        editor.apply();
        finish();

    }

}
