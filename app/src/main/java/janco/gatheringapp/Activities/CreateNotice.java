package janco.gatheringapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
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
    private DBNotice noticeDB;
    private float latitude;
    private float longitude;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);
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

    public void createNotice(View view) {
        EditText noticeName = (EditText) findViewById(R.id.createNoticeName);
        EditText noticeDescription = (EditText) findViewById(R.id.createNoticeDescription);
        EditText noticeAddress = (EditText) findViewById(R.id.createNoticeAddress);
        String noticeNameString = noticeName.getText().toString();
        String noticeDescriptionString = noticeDescription.getText().toString();
        String noticeAddressString = noticeAddress.getText().toString();

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!enabled)
        {
            Intent startGps = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(startGps);
        }

        //TODO Change how gps coordinates are aqquired

        checkCallingPermission(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        if(location != null) {
            onLocationChanged(location);
        }
        else
        {
            Toast locationNotAvailable = Toast.makeText(this, R.string.locationNotAvailable, Toast.LENGTH_SHORT);
            locationNotAvailable.show();
        }

        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String selectedDateString = mySharedPreferences.getString("Date", "");

        Notice newNotice = new Notice(noticeNameString, noticeDescriptionString, noticeAddressString, selectedDateString, latitude, longitude);

        if (!noticeNameString.equals("")) {
            int success = noticeDB.insertNotice(newNotice);

            if (success == 1) {
                Toast createNoticeSuccess = Toast.makeText(this, R.string.createNoticeSuccess, Toast.LENGTH_SHORT);
                createNoticeSuccess.show();
            } else {
                Toast createNoticeNotSuccess = Toast.makeText(this, R.string.createNoticeNotSuccess, Toast.LENGTH_SHORT);
                createNoticeNotSuccess.show();
            }
        } else {
            Toast nameCantBeEmpty = Toast.makeText(this, R.string.createNoticeNameCantBeEmpty, Toast.LENGTH_SHORT);
            nameCantBeEmpty.show();
        }

    }

    public void selectDateAndTime(View view) {
        Intent selectTimeAndDate = new Intent(this, CreateNoticeSelectDateAndTime.class);
        startActivityForResult(selectTimeAndDate, PICK_DATE_AND_TIME_REQUEST);

    }

    public void onLocationChanged(Location location)
    {
        latitude = (float) location.getLatitude();
        longitude = (float) location.getLongitude();
    }
}

