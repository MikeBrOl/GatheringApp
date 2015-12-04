package janco.gatheringapp;

import android.test.AndroidTestCase;
import android.util.Log;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.google.android.maps.GeoPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import janco.gatheringapp.Database.DBUser;
import janco.gatheringapp.Model.Notice;
import janco.gatheringapp.Model.User;
import janco.gatheringapp.Database.DBLocationFinder;
/**
 * Created by Torn on 02-12-2015.
 */
public class DBLocationFinderTest extends AndroidTestCase
{
    //opslags: dato
    //User: status

    /*
    http://www.mapsofworld.com/lat_long/denmark-lat-long.html

    Test data for denmark
    minLat = 54.00
    maxLat = 57.00
    minLong = 8
    maxLong = 13

Aalborg lat, long = 57.02 and 9.54
     */

    DBLocationFinder dbLocationFinder;

    List<User> foundUsers;
    //List<Notice> foundNotices;

    // test data
    double latMax, latMin, longMax, longMin;
    boolean status;
    Date date;

    public DBLocationFinderTest()
    {
        dbLocationFinder = new DBLocationFinder();
    }

    public void testDependencies()
    {
        Assert.assertNotNull(dbLocationFinder);
    }


    public void testFindUsers()
    {
        // fill in test data
        // Aalborg lat, long = 57.02 and 9.54
        User testAalborgUser = new User("testUsername", "testName", "testPassword", "testEmail", 56.00, 9.54, true);
        DBUser dbUser = new DBUser();
        dbUser.insertUser(testAalborgUser);

        ArrayList<User> foundUsers = new ArrayList<>();

        /*
        Test data for denmark
        minLat = 54.00
        maxLat = 57.00
        minLong = 8
        maxLong = 13
         */
        latMax = 56.01;
        latMin = 55.98;
        longMax = 9.56;
        longMin = 9.51;

        status = true;

        foundUsers = dbLocationFinder.getUsersByRadiusAndStatus(latMax, latMin, longMax, longMin, status);
        Log.e("Array Size", Integer.toString(foundUsers.size()));

        Assert.assertEquals("testEmail", foundUsers.get(0).getEmail());

        // check what the list contains?

    }
    public void testFindNotices()
    {
        ArrayList<Notice> foundNotices;

        date = new Date();

        Notice testNotice = new Notice();

        foundNotices = dbLocationFinder.getNoticesByRadiusAndDate(latMax, latMin, longMax, longMin, date);

        Assert.assertEquals("testName", foundNotices.get(0));
    }


}
