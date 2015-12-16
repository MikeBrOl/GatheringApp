package janco.gatheringapp.Activities;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import janco.gatheringapp.Database.DBMessageSystem;
import janco.gatheringapp.R;

public class MessageOverviewActivity extends AppCompatActivity {
    String tableName;
    String userName;
    ListView messageListView;
    DBMessageSystem system;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_overview);
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        tableName = getIntent().getStringExtra("TableName");
        userName = mySharedPreferences.getString("UserName", "");
        messageListView = (ListView) findViewById(R.id.messageOverviewList);
        system = new DBMessageSystem();

        List<Map<String, String>> data = new ArrayList<>();
        data.add(system.getAllMessages(tableName));

        SimpleAdapter adapter = new SimpleAdapter(MessageOverviewActivity.this, data,
                android.R.layout.simple_expandable_list_item_2,
                new String [] {"UserName", "Message"},
                new int[] {android.R.id.text1, android.R.id.text2});
        messageListView.setAdapter(adapter);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void sendMessage(View view)
    {
        EditText textMessage = (EditText) findViewById(R.id.messageSendText);
        String message = textMessage.getText().toString();
        system.insertMessage(message, tableName, userName);
    }
}
