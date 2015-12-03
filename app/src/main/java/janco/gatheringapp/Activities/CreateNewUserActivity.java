package janco.gatheringapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.jasypt.util.password.BasicPasswordEncryptor;

import janco.gatheringapp.Model.User;
import janco.gatheringapp.Database.DBUser;

import janco.gatheringapp.R;

public class CreateNewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);
    }

    public void createNewUser(View view)
    {
        EditText newUsername = (EditText) findViewById(R.id.newUserNameInput);
        EditText newName = (EditText) findViewById(R.id.newNameInput);
        EditText newPassword = (EditText) findViewById(R.id.newPasswordInput);
        EditText newEmail = (EditText) findViewById(R.id.newEmailInput);
        String userNameString = newUsername.getText().toString();
        String nameString = newName.getText().toString();
        String passwordString = newPassword.getText().toString();
        String emailString = newEmail.getText().toString();

        BasicPasswordEncryptor bpe = new BasicPasswordEncryptor();
        String encryptedPassword = bpe.encryptPassword(passwordString);

        int check = 0;

        try
        {
            User user = new User();
            DBUser dbUser = new DBUser();
            user.setUsername(userNameString);
            user.setName(nameString);
            user.setPassword(encryptedPassword);
            user.setEmail(emailString);
            check = dbUser.insertUser(user);

            if(check == 1)
            {
                Intent login = new Intent(this, LoginActivity.class);
                startActivity(login);
            }
            else
            {
                Toast failedToCreate = Toast.makeText(this,R.string.createUserFailed,Toast.LENGTH_SHORT);
                failedToCreate.show();
            }
        }
        catch(Exception e)
        {
            Log.e("ERROR", e.getMessage());
        }
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
