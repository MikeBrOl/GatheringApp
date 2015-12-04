package janco.gatheringapp;


import com.google.android.maps.GeoPoint;

import junit.framework.Assert;

import janco.gatheringapp.Model.User;
/**
 * Created by Torn on 02-12-2015.
 */
public class UserModelTest
{
    String username;
    String name;
    String password;
    String email;

    //GeoPoint lastKnownLocation;
    // user's
    double lastKnownLatitude;
    double lastKnownLongitude;

    boolean searchStatus;

    public void testCreateUser()
    {
        username = "testUsername";
        name = "testName";
        password = "testPassword";
        email = "testEmail";
        // Aalborg lat, long = 57.02 and 9.54
        lastKnownLatitude = 57.02;
        lastKnownLongitude = 9.54;

        searchStatus = false;

        User newTestUser = new User(username, name, password, email, lastKnownLatitude, lastKnownLongitude, searchStatus);

        Assert.assertEquals("testEmail", newTestUser.getEmail());

    }
}
