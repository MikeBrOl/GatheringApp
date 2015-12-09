package janco.gatheringapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import janco.gatheringapp.Database.DBNotice;
import janco.gatheringapp.Model.Notice;
import janco.gatheringapp.R;

public class NoticeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_view);
        String noticeName = getIntent().getStringExtra("NoticeName");
        DBNotice noticeDB = new DBNotice();
        Notice notice = noticeDB.getNoticeByName(noticeName);

        TextView noticeNameTextView = (TextView) findViewById(R.id.noticeViewName);
        TextView noticeDescriptionTV = (TextView) findViewById(R.id.noticeViewDescription);
        TextView noticeAddressTV = (TextView) findViewById(R.id.noticeViewAddress);
        TextView noticeTimeTV = (TextView) findViewById(R.id.noticeViewTime);

        noticeNameTextView.setText(notice.getName());
        noticeDescriptionTV.setText(notice.getDescription());
        noticeAddressTV.setText(notice.getAddress());
        noticeTimeTV.setText(notice.getTime());
    }
}
