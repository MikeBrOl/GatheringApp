package janco.gatheringapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import janco.gatheringapp.R;

public class NoticeOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_overview);
    }

    public void goToCreateNotice(View view)
    {
        Intent goTo = new Intent(this, CreateNotice.class);
        startActivity(goTo);
    }
}
