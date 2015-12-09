package janco.gatheringapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
            editor.apply();
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
}