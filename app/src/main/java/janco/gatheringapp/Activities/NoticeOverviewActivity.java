package janco.gatheringapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private final static String EXTRA_MESSAGE = "janco.gatheringapp.Activities.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_overview);
        algorithm = new LocationAlgorithm();
        dbUser = new DBUser();
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userLoggedIn = dbUser.getUserByID(mySharedPreferences.getInt("UserID", 0));
        Date currentDate = new Date();
        StringBuilder dateString = new StringBuilder();
        dateString.append(currentDate.getYear()+"-");
        dateString.append((currentDate.getMonth()+1)+"-");
        dateString.append(currentDate.getDate()+" ");
        dateString.append(currentDate.getHours()+":");
        dateString.append(currentDate.getMinutes()+":");
        dateString.append(currentDate.getSeconds());
        String date = dateString.toString();

        radius = 2000;

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
        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent inspectNotice = new Intent(getApplicationContext(), NoticeView.class);
                Notice entry = (Notice) parent.getItemAtPosition(position);
                String noticeName = entry.getName();
                inspectNotice.putExtra("NoticeName", noticeName);
                startActivity(inspectNotice);
            }
        });

    }

    public void goToCreateNotice(View view)
    {
        Intent goTo = new Intent(this, CreateNotice.class);
        startActivity(goTo);
    }
}
