package janco.gatheringapp;

import android.test.AndroidTestCase;
import junit.framework.Assert;
import junit.framework.TestCase;

import com.google.android.maps.GeoPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    int status;
    Date date;

    // test users
    User testUser1;
    User testUser2;
    User testUser3;
    User testUser4;

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
        User testAalborgUser = new User("testUsername", "testName", "testPassword", "testEmail", 57.02, 9.54, true);

        ArrayList<User> foundUsers = new ArrayList<>();

        /*
        Test data for denmark
        minLat = 54.00
        maxLat = 57.00
        minLong = 8
        maxLong = 13
         */
        latMax = 57.00;
        latMin = 54.00;
        longMax = 13.00;
        longMin = 8;

        foundUsers = dbLocationFinder.getUsersByRadiusAndStatus(latMax, latMin, longMax, longMin, status);

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
