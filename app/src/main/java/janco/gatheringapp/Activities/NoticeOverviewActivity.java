package janco.gatheringapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Date;
import java.util.GregorianCalendar;
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
        Calendar currentDate = Calendar.getInstance();
        StringBuilder dateString = new StringBuilder();
        dateString.append(currentDate.get(Calendar.YEAR)+"-");
        dateString.append((currentDate.get(Calendar.MONTH)+1)+"-");
        dateString.append(currentDate.get(Calendar.DAY_OF_MONTH)+" ");
        dateString.append(currentDate.get(Calendar.HOUR_OF_DAY)+":");
        dateString.append(currentDate.get(Calendar.MINUTE)+":");
        dateString.append(currentDate.get(Calendar.SECOND));
        String date = dateString.toString();
        Log.e("Date Overview", date);

        radius = 2000;

        noticeListView = (ListView) findViewById(R.id.overviewListView);
        final ArrayList<Notice> noticeList = algorithm.boundingBoxCalculationForNotice(userLoggedIn.getLastKnownlatitude(), userLoggedIn.getLastKnownLongitude(), radius, date);

        List<Map<String, String>> data = new ArrayList<>();
        for(Notice notice : noticeList)
        {
            Map<String, String> datum = new HashMap<>(2);
            datum.put("name", notice.getName());
            datum.put("date", notice.getTime());
            data.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter(NoticeOverviewActivity.this, data,
                android.R.layout.simple_expandable_list_item_2,
                new String [] {"name", "date"},
                new int[] {android.R.id.text1, android.R.id.text2});
        noticeListView.setAdapter(adapter);
        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent inspectNotice = new Intent(getApplicationContext(), NoticeView.class);
                HashMap entry = (HashMap) parent.getItemAtPosition(position);
                String noticeName = entry.get("name").toString();
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

    public void goToUserConnection(View view)
    {
        Intent goTO = new Intent(this, UserConnectionActivity.class);
        startActivity(goTO);
    }
}
