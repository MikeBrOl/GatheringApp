package janco.gatheringapp;

import android.test.AndroidTestCase;
import junit.framework.Assert;
import junit.framework.TestCase;

import com.google.android.maps.GeoPoint;

import janco.gatheringapp.Model.User;
/**
 * Created by Torn on 02-12-2015.
 */
public class UserModelTest extends AndroidTestCase
{
    String username;
    String name;
    String password;
    String email;

    //GeoPoint lastKnownLocation;
    // user's
    double lastKnownlatitude;
    double lastKnownLongitude;

    boolean searchStatus;

    public void testCreateUser()
    {
        username = "testUsername";
        name = "testName";
        password = "testPassword";
        email = "testEmail";
        //lastKnownLocation = new GeoPoint(30, 30);
        // Aalborg lat, long = 57.02 and 9.54
        lastKnownlatitude = 57.02;
        lastKnownLongitude = 9.54;

        searchStatus = false;

        User newTestUser = new User(username, name, password, email, lastKnownLongitude, lastKnownLongitude, searchStatus);

        Assert.assertEquals("testEmail", newTestUser.getEmail());

    }
}
