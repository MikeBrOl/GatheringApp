package janco.gatheringapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import janco.gatheringapp.Model.User;
import janco.gatheringapp.Database.DBUser;

import janco.gatheringapp.R;

public class CreateNewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);
    }

    public void createNewUser()
    {
        EditText newUsername = (EditText) findViewById(R.id.newUsernameInput);
        EditText newName = (EditText) findViewById(R.id.newNameInput);
        EditText newAddress = (EditText) findViewById(R.id.newAddressInput);
        EditText newEmail = (EditText) findViewById(R.id.newEmailInput);
        String userNameString = newUsername.getText().toString();
        String nameString = newName.getText().toString();
        String addressString = newAddress.getText().toString();
        String emailString = newEmail.getText().toString();
        try
        {
            User user = new User();
            DBUser dbUser = new DBUser();
            user.setUsername(userNameString);
            user.setName(nameString);
            user.setAddress(addressString);
            user.setEmail(emailString);
            dbUser.insertUser(user);

            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }
        catch(Exception e)
        {
            Toast failedToCreate = Toast.makeText(this,R.string.createUserFailed,Toast.LENGTH_SHORT);
            failedToCreate.show();
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
