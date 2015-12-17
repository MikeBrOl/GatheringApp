package janco.gatheringapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Calendar;
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
        userLoggedIn = (User) getIntent().getSerializableExtra("LoggedInUser");
        Calendar currentDate = Calendar.getInstance();
        StringBuilder dateString = new StringBuilder();
        dateString.append(currentDate.get(Calendar.YEAR)+"-");
        dateString.append((currentDate.get(Calendar.MONTH)+1)+"-");
        dateString.append(currentDate.get(Calendar.DAY_OF_MONTH)+" ");
        dateString.append(currentDate.get(Calendar.HOUR_OF_DAY)+":");
        dateString.append(currentDate.get(Calendar.MINUTE) + ":");
        dateString.append(currentDate.get(Calendar.SECOND));
        date = dateString.toString();
        Log.e("Date Overview", date);
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        radius = mySharedPreferences.getInt("Radius", 0);
        noticeListView = (ListView) findViewById(R.id.overviewListView);

        String lastKnownLat = String.valueOf(userLoggedIn.getLastKnownlatitude());
        String lastKnownLon = String.valueOf(userLoggedIn.getLastKnownLongitude());
        String r = String.valueOf(radius);

        String[] strings = {lastKnownLat, lastKnownLon, r, date};
        GetNoticeListAsync lba = new GetNoticeListAsync();
        lba.execute(strings);

        //populateLisView();

    }

    public void goToCreateNotice(View view)
    {
        Intent goTo = new Intent(this, CreateNotice.class);
        startActivity(goTo);
    }

    public void goToUserConnection(View view)
    {
        Intent goTO = new Intent(this, UserConnectionActivity.class);
        goTO.putExtra("User", userLoggedIn);
        startActivity(goTO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_menu_notice_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("Case", Integer.toString(item.getItemId()));
        if(item.getItemId() == R.id.radius500)
        {
            radius = 500;
        }
        if(item.getItemId() == R.id.radius1000)
        {
            radius = 1000;
        }
        if(item.getItemId() == R.id.radius2000)
        {
            radius = 2000;
        }
        if(item.getItemId() == R.id.radius5000)
        {
            radius = 5000;
        }
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt("Radius", radius);
        editor.apply();
        Log.e("Radius", Integer.toString(radius));

        String lastKnownLat = String.valueOf(userLoggedIn.getLastKnownlatitude());
        String lastKnownLon = String.valueOf(userLoggedIn.getLastKnownLongitude());
        String r = String.valueOf(radius);

        String[] strings = {lastKnownLat, lastKnownLon, r, date};
        GetNoticeListAsync lba = new GetNoticeListAsync();
        lba.execute(strings);


        //populateLisView();

        return super.onOptionsItemSelected(item);
    }

//    public void populateLisView()
//    {
//
//        final ArrayList<Notice> noticeList = algorithm.boundingBoxCalculationForNotice(userLoggedIn.getLastKnownlatitude(), userLoggedIn.getLastKnownLongitude(), radius, date);
//
//        List<Map<String, String>> data = new ArrayList<>();
//        for(Notice notice : noticeList)
//        {
//            Map<String, String> datum = new HashMap<>(2);
//            datum.put("name", notice.getName());
//            datum.put("date", notice.getTime());
//            data.add(datum);
//        }
//
//        SimpleAdapter adapter = new SimpleAdapter(NoticeOverviewActivity.this, data,
//                android.R.layout.simple_expandable_list_item_2,
//                new String [] {"name", "date"},
//                new int[] {android.R.id.text1, android.R.id.text2});
//        noticeListView.setAdapter(adapter);
//        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent inspectNotice = new Intent(getApplicationContext(), NoticeView.class);
//                HashMap entry = (HashMap) parent.getItemAtPosition(position);
//                String noticeName = entry.get("name").toString();
//
//                int index = 0;
//                while(noticeList.size() > index)
//                {
//                    if(noticeList.get(index).getName().equals(noticeName))
//                    {
//                        Notice notice = noticeList.get(index);
//                        inspectNotice.putExtra("SelectedNotice", notice);
//                    }
//                    index++;
//                }
//                startActivity(inspectNotice);
//            }
//        });
//
//    }


    private class GetNoticeListAsync extends AsyncTask<String, Void, ArrayList>
    {

        @Override
        protected ArrayList doInBackground(String... params)
        {
            double lat = Double.parseDouble(params[0]);
            double lon = Double.parseDouble(params[1]);
            int radius = Integer.parseInt(params[2]);

            ArrayList<Notice> list = algorithm.boundingBoxCalculationForNotice(lat, lon, radius, params[3]);
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList list)
        {
            final ArrayList<Notice> noticeList = list;
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

                    int index = 0;
                    while(noticeList.size() > index)
                    {
                        if(noticeList.get(index).getName().equals(noticeName))
                        {
                            Notice notice = noticeList.get(index);
                            inspectNotice.putExtra("SelectedNotice", notice);
                        }
                        index++;
                    }
                    startActivity(inspectNotice);
                }
            });

        }
    }

}
