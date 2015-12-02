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
    GeoPoint lastKnownLocation;

    public void testCreateUser()
    {
        username = "testUsername";
        name = "testName";
        password = "testPassword";
        email = "testEmail";
        lastKnownLocation = new GeoPoint(30, 30);

        User newTestUser = new User(username, name, password, email, lastKnownLocation);

        Assert.assertEquals("testEmail", newTestUser.getEmail());

    }
}
