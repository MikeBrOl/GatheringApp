package janco.gatheringapp;

import android.test.AndroidTestCase;
import junit.framework.Assert;

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
    public void testInsertNoticeTest()
    {
        String time = "2015-12-09 11:22:33";

        double lat = 57.02;
        float latFloat = (float) lat;

        double lon = 9.54;
        float lonFloat = (float) lon;

        //Notice notice = new Notice("insertTest", "description", "testAddress", time, latFloat, lonFloat);

       // Assert.assertEquals(1, dbNotice.insertNotice(notice));
    }

    //Notice with name deleteTest, has to be in the database before the test can be run.
    public void testDeleteNoticeTest()
    {
        //Date time = new Date();
        String time = "2015-12-09 11:22:33";

        double lat = 57.02;
        float latFloat = (float) lat;

        double lon = 9.54;
        float lonFloat = (float) lon;


       // Notice notice = new Notice("deleteTest", "description", "testAddress", time, latFloat, lonFloat);
        //Assert.assertEquals(1, dbNotice.deleteNotice(notice));
    }

    //Database must contain a notice with the name test before the test can be run
    public void testGetNoticeByName()
    {
        Notice notice = dbNotice.getNoticeByName("Test");

        Assert.assertEquals("Test", notice.getName());
    }


}
