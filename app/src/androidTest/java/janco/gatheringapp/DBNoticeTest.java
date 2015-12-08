package janco.gatheringapp;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import janco.gatheringapp.Database.DBNotice;
import janco.gatheringapp.Model.Notice;

/**
 * Created by Mike on 08-12-2015.
 */
public class DBNoticeTest extends AndroidTestCase
{
    private DBNotice dbNotice;

    public DBNoticeTest()
    {
        dbNotice = new DBNotice();
    }

    //Database must not contain a notice with name insertTest, when the test is run.
    public void insertNoticeTest()
    {
        Notice notice = new Notice("insertTest", "description", "testAddress", "Time", "Date", 57.02, 9.54);

        Assert.assertEquals(1, dbNotice.insertNotice(notice));
    }

    //Notice with name deleteTest, has to be in the database before the test can be run.
    public void deleteNoticeTest()
    {
        Notice notice = new Notice("deleteTest", "description", "testAddress", "Time", "Date", 57.02, 9.54);
        Assert.assertEquals(1, dbNotice.deleteNotice(notice));
    }


}
