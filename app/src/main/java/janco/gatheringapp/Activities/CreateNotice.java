package janco.gatheringapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import janco.gatheringapp.Database.DBNotice;
import janco.gatheringapp.Model.Notice;
import janco.gatheringapp.R;

public class CreateNotice extends AppCompatActivity {

    static final int PICK_DATE_AND_TIME_REQUEST = 1;
    private Date selectedDate;
    private DBNotice noticeDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);
        noticeDB = new DBNotice();
        selectedDate = new Date();

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

        float latitude = (float)locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
        float longitude = (float)locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
        Notice newNotice = new Notice(noticeNameString, noticeDescriptionString, noticeAddressString, selectedDate, latitude, longitude);

        if(!noticeNameString.equals(""))
        {
            int success = noticeDB.insertNotice(newNotice);

            if(success == 1)
            {
                Toast createNoticeSuccess = Toast.makeText(this, R.string.createNoticeSuccess, Toast.LENGTH_SHORT);
                createNoticeSuccess.show();
            }
            else
            {
                Toast createNoticeNotSuccess = Toast.makeText(this, R.string.createNoticeNotSuccess, Toast.LENGTH_SHORT);
                createNoticeNotSuccess.show();
            }
        }
        else
        {
            Toast nameCantBeEmpty = Toast.makeText(this, R.string.createNoticeNameCantBeEmpty, Toast.LENGTH_SHORT);
            nameCantBeEmpty.show();
        }

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
                Log.e("Date Format", selectedDateString);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
