package janco.gatheringapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import janco.gatheringapp.Database.DBMessageSystem;
import janco.gatheringapp.Database.DBUser;
import janco.gatheringapp.Database.DBUserConnection;
import janco.gatheringapp.Model.User;
import janco.gatheringapp.Model.UserConnection;
import janco.gatheringapp.R;

public class UserConnectionHistoryActivity extends AppCompatActivity
{
    private User userLoggedIn;
    private DBUser dbUser;
    private DBMessageSystem dbMessageSystem;

    private String userName;

    private String date;
    private int radius;
    private ListView historyListView;

    private final static String EXTRA_MESSAGE = "janco.gatheringapp.Activities.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_connection_history);


        dbMessageSystem = new DBMessageSystem();





        userLoggedIn = (User)getIntent().getSerializableExtra("User");
        userName = userLoggedIn.getName();



        historyListView = (ListView) findViewById(R.id.HistoryListView);


        final ArrayList<String> userConnectionHistoryList = dbMessageSystem.getChatsByUserName(userName);

        // populate list
        List<Map<String, String>> data = new ArrayList<>();
        for(String tableName : userConnectionHistoryList)
        {
            Map<String, String> datum = new HashMap<>(1);
            datum.put("TableName", tableName);
            data.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter(UserConnectionHistoryActivity.this, data,
                android.R.layout.simple_expandable_list_item_2,
                new String [] {"TableName"},
                new int [] {android.R.id.text1});

        historyListView.setAdapter(adapter);
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent message = new Intent(getApplicationContext(), MessageOverviewActivity.class);
                HashMap entry = (HashMap) parent.getItemAtPosition(position);
                String messageTableName = entry.get("TableName").toString();
                message.putExtra("TableName", messageTableName);
                message.putExtra("User", userLoggedIn);
                startActivity(message);
            }

        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


}
