package janco.gatheringapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.jasypt.util.password.BasicPasswordEncryptor;

import janco.gatheringapp.Database.DBUser;
import janco.gatheringapp.Model.User;

import janco.gatheringapp.R;

public class LoginActivity extends AppCompatActivity {
    private float latitude;
    private float longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void login(View view)
    {
        EditText loginUserName = (EditText) findViewById(R.id.userNameInput);
        EditText password = (EditText) findViewById(R.id.passwordInput);
        String loginString = loginUserName.getText().toString();
        String passwordString = password.getText().toString();

        DBUser userDB = new DBUser();

        User user = userDB.getUserByUsername(loginString);

        BasicPasswordEncryptor pe = new BasicPasswordEncryptor();
        String encryptedPassword = user.getPassword();

        if(pe.checkPassword(passwordString, encryptedPassword))
        {
            SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putInt("UserID", user.getID());
            editor.putString("UserName", user.getUsername());
            editor.apply();
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
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);
            if(location != null) {
                onLocationChanged(location);
            }
            else
            {
                Toast locationNotAvailable = Toast.makeText(this, R.string.locationNotAvailable, Toast.LENGTH_SHORT);
                locationNotAvailable.show();
            }
            user.setLastKnownlatitude(latitude);
            user.setLastKnownLongitude(longitude);
            userDB.updateUser(user);
            Intent loginWork = new Intent(this, NoticeOverviewActivity.class);
            startActivity(loginWork);
        }
        else
        {
            Toast failedLogin = Toast.makeText(this,R.string.loginFailed,Toast.LENGTH_SHORT);
            failedLogin.show();
        }
    }
    public void createNewUser(View view)
    {
        Intent newUser = new Intent(this, CreateNewUserActivity.class);
        startActivity(newUser);
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

    public void onLocationChanged(Location location)
    {
        latitude = (float) location.getLatitude();
        longitude = (float) location.getLongitude();
    }
}