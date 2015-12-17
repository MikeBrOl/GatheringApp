package janco.gatheringapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import janco.gatheringapp.Database.DBMessageSystem;
import janco.gatheringapp.Database.DBUser;
import janco.gatheringapp.Database.DBUserConnection;
import janco.gatheringapp.Model.User;
import janco.gatheringapp.Model.UserConnection;
import janco.gatheringapp.R;

public class UserConnectionActivity extends AppCompatActivity {

    private ListView userListView;
    private LocationAlgorithm algorithm;
    private User userLoggedIn;
    private DBUser dbUser;
    private int radius;
    private Switch status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_connection);
        algorithm = new LocationAlgorithm();
        dbUser = new DBUser();
        userLoggedIn = (User)getIntent().getSerializableExtra("User");
        status = (Switch) findViewById(R.id.UserConnectionActivitySearchStatus);
        status.setChecked(userLoggedIn.isSearchStatus());

        radius = 10000;

        userListView = (ListView) findViewById(R.id.UserConnectionActivityListView);

        status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    userLoggedIn.setSearchStatus(true);
                    final ArrayList<User> userList = algorithm.boundingBoxCalculationForUsers(userLoggedIn.getLastKnownlatitude(), userLoggedIn.getLastKnownLongitude(), radius, true);

                    List<Map<String, String>> data = new ArrayList();
                    for (User user : userList)
                    {
                        Map<String, String> datum = new HashMap<>(2);
                        datum.put("Username", user.getUsername());
                        datum.put("Name", user.getName());
                        data.add(datum);
                    }

                    SimpleAdapter adapter = new SimpleAdapter(UserConnectionActivity.this, data, android.R.layout.simple_expandable_list_item_2, new String [] {"Username", "Name"}, new int[] {android.R.id.text1, android.R.id.text2});
                    userListView.setAdapter(adapter);
                    userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent message = new Intent(getApplicationContext(), MessageOverviewActivity.class);
                            HashMap entry = (HashMap) parent.getItemAtPosition(position);
                            String userName = entry.get("Username").toString();
                            String messageTableName ="";
                            int index = 0;
                            boolean found = false;

                            while (!found || userList.size() > index)
                            {
                                if(userList.get(index).getUsername().compareTo(userName)==0)
                                {
                                    User user = userList.get(index);
                                    UserConnection userConnection = new UserConnection(userLoggedIn, user);
                                    DBUserConnection dbUserConnection = new DBUserConnection();

                                    if(dbUserConnection.insertUserConnection(userConnection)==0)
                                    {
                                        UserConnection alternativeUserConnection = dbUserConnection.checkForExistingUserConnection(userConnection.getAppUser(), userConnection.getConnectedUser());
                                        messageTableName = alternativeUserConnection.getAppUser().getUsername()+
                                                alternativeUserConnection.getConnectedUser().getUsername();
                                    }
                                    else
                                    {
                                        messageTableName = userConnection.getAppUser().getUsername()+
                                                userConnection.getConnectedUser().getUsername();
                                        Log.e("Table Name", messageTableName);
                                    }

                                    found = true;
                                }
                                else
                                {
                                    Toast badShit = Toast.makeText(getApplicationContext(), R.string.ConnectionErrorToast, Toast.LENGTH_LONG);
                                    badShit.show();
                                }

                                index ++;
                            }
                            message.putExtra("TableName", messageTableName);
                            message.putExtra("User", userLoggedIn);
                            startActivity(message);
                        }
                    });
                }
                        else
                        {
                            userLoggedIn.setSearchStatus(false);
                        }

                        dbUser.updateUser(userLoggedIn);
                    }
                });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void goBack(View view)
    {
        finish();
    }

    public void connectionHistory(View view)
    {
        Intent userHistory = new Intent(this, UserConnectionHistoryActivity.class);
        userHistory.putExtra("User", userLoggedIn);
        startActivity(userHistory);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_menu_user_connection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.radius){


        }
        return super.onOptionsItemSelected(item);
    }



}
