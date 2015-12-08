package janco.gatheringapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import janco.gatheringapp.Model.Notice;
import janco.gatheringapp.R;

public class CreateNotice extends AppCompatActivity {

    static final int PICK_DATE_AND_TIME_REQUEST = 1;
    private java.util.Date selectedDate;
    private DBNotice noticeDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);
        selectedDate = new java.util.Date();
        noticeDB = new DBNotice();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void createNotice(View view)
    {
        EditText noticeName = (EditText) findViewById(R.id.createNoticeName);
        EditText noticeDescription = (EditText) findViewById(R.id.createNoticeDescription);
        EditText noticeAddress = (EditText) findViewById(R.id.createNoticeAddress);
        String noticeNameString = noticeName.getText().toString();
        String noticeDescriptionString = noticeDescription.getText().toString();
        String noticeAddressString = noticeAddress.getText().toString();

        LocationManager locationManager = (LocationManager)
        getSystemService(Context.LOCATION_SERVICE);
        checkCallingPermission(LOCATION_SERVICE);

        double latitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
        double longitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
        Notice newNotice = new Notice(noticeNameString, noticeDescriptionString, noticeAddressString, selectedDate, latitude, longitude);

        noticeDB.insert(newNotice);
    }

    public void selectDateAndTime(View view)
    {
        Intent selectTimeAndDate = new Intent(this, CreateNoticeSelectDateAndTime.class);
        startActivityForResult(selectTimeAndDate, PICK_DATE_AND_TIME_REQUEST);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == PICK_DATE_AND_TIME_REQUEST)
        {
            if(resultCode == RESULT_OK)
            {
                Bundle res = data.getExtras();
                String selectedDateString = res.getString("date_result");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy mm:hh");
                try{
                    selectedDate = dateFormat.parse(selectedDateString);
                }
                catch(ParseException dateParsingException)
                {
                    Log.e("Date Failed to parse", dateParsingException.toString());
                }
            }
        }
    }
}
