package janco.gatheringapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import janco.gatheringapp.Database.DBUser;
import janco.gatheringapp.Model.Notice;
import janco.gatheringapp.Model.User;
import janco.gatheringapp.R;

public class NoticeOverviewActivity extends AppCompatActivity {

    private ListView noticeListView;
    private LocationAlgorithm algorithm;
    private User userLoggedIn;
    private DBUser dbUser;
    private int radius;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_overview);
        algorithm = new LocationAlgorithm();
        dbUser = new DBUser();
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userLoggedIn = dbUser.getUserByID(mySharedPreferences.getInt("UserID", 0));

        noticeListView = (ListView) findViewById(R.id.overviewListView);
        ArrayList<Notice> noticeList = algorithm.boundingBoxCalculationForNotice(userLoggedIn.getLastKnownlatitude(), userLoggedIn.getLastKnownLongitude(), radius, date);

        List<Map<String, String>> data = new ArrayList<>();
        for(Notice notice : noticeList)
        {
            Map<String, String> datum = new HashMap<>(2);
            datum.put("name", notice.getName());
            datum.put("date", notice.getTime());
            data.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_expandable_list_item_2,
                new String [] {"name", "date"},
                new int[] {android.R.id.text1, android.R.id.text2});
        noticeListView.setAdapter(adapter);

    }

    public void goToCreateNotice(View view)
    {
        Intent goTo = new Intent(this, CreateNotice.class);
        startActivity(goTo);
    }
}
