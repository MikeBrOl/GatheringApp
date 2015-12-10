package janco.gatheringapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import janco.gatheringapp.Database.DBUser;
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
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userLoggedIn = dbUser.getUserByID(mySharedPreferences.getInt("UserID", 0));
        status = (Switch) findViewById(R.id.UserConnectionActivitySearchStatus);

        radius = 2000;

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
                        Map<String, String> datum = new HashMap<>(1);
                        datum.put("Username", user.getUsername());
                        data.add(datum);
                    }

                    SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, android.R.layout.simple_expandable_list_item_1, new String [] {"Username"}, new int[] {android.R.id.text1});
                    userListView.setAdapter(adapter);
                    userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent message = new Intent(getApplicationContext(), MessageOverviewActivity.class);
                            HashMap entry = (HashMap) parent.getItemAtPosition(position);
                            String userName = entry.get("Username").toString();
                            int index = 0;
                            boolean found = false;

                            while (!found || userList.size() > index)
                            {
                                if(userList.get(index).getUsername().compareTo(userName)==0)
                                {
                                    UserConnection userConnection = new UserConnection(userLoggedIn, userList.get(index));
                                    DBUserConnection dbUserConnection = new DBUserConnection();

                                    dbUserConnection.insertUserConnection(userConnection);

                                    found = true;
                                }
                                else
                                {
                                    Toast badShit = Toast.makeText(getApplicationContext(), R.string.ConnectionErrorToast, Toast.LENGTH_LONG);
                                    badShit.show();
                                }

                                index ++;
                            }
                            startActivity(message);
                        }
                    });
                }
                        else
                        {
                            userLoggedIn.setSearchStatus(false);
                        }
                    }
                });
    }

    public void goBack(View view)
    {
        finish();
    }





}
