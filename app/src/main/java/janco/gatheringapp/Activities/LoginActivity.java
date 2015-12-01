package janco.gatheringapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import janco.gatheringapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login()
    {
        EditText loginUserName = (EditText) findViewById(R.id.userNameInput);
        EditText password = (EditText) findViewById(R.id.passwordInput);
        String loginString = loginUserName.getText().toString();
        String passwordString = password.getText().toString();
        if(verifacation(loginString, passwordString))
        {
            Intent loginWork = new Intent(this, NoticeOverviewActivity.class);
            startActivity(loginWork);
        }
        else
        {
            Toast failedLogin = Toast.makeText(this,"R.string.loginFailed",Toast.LENGTH_SHORT);
            failedLogin.show();
        }
    }
    public void createNewUser()
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