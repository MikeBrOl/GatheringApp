package janco.gatheringapp;

import android.test.AndroidTestCase;
import junit.framework.Assert;
import junit.framework.TestCase;

import janco.gatheringapp.Model.User;
import janco.gatheringapp.Model.UserConnection;

/**
 * Created by Torn on 04-12-2015.
 */
public class UserConnectionModelTest extends AndroidTestCase {

    //User appUser;
    //User connectedUser;


    public void testCreateConnectionBetweenUsers(User appUser, User connectedUser) {
        // appUser information
        String testAppUserUsername = "testAppUserUsername";
        String testAppUserName = "testAppUserName";
        String testAppUserPassword = "testAppUserPassword";
        String testAppUserEmail = "testAppUserEmail";
        // Aalborg lat, long = 57.02 and 9.54
        double testAppUserLastKnownLatitude = 57.02;
        double testAppUserLastKnownLongitude = 9.54;
        boolean testAppUserSearchStatus = true;

        User testAppUser = new User(testAppUserUsername, testAppUserName, testAppUserPassword, testAppUserEmail,
                                testAppUserLastKnownLatitude, testAppUserLastKnownLongitude, testAppUserSearchStatus);

        // connectedUser information
        String TestConnectedUserUsername = "testConnectedUserUsername";
        String TestConnectedUserName = "testConnectedUserName";
        String TestConnectedUserPassword = "testConnectedUserPassword";
        String TestConnectedUserEmail = "testConnectedUserEmail";
        // Aalborg lat, long = 57.02 and 9.54
        double TestConnectedUserLatitude = 57.02;
        double TestConnectedUserLongitude = 9.54;
        boolean TestConnectedUserSearchStatus = true;

        User testConnectedUser = new User(TestConnectedUserUsername, TestConnectedUserName, TestConnectedUserPassword,
                                        TestConnectedUserEmail, TestConnectedUserLatitude, TestConnectedUserLongitude,
                                        TestConnectedUserSearchStatus);

        UserConnection newUserConnection = new UserConnection(testAppUser, testConnectedUser);


        // tests for testAppUser
        Assert.assertSame(testAppUser, newUserConnection.getAppUser());

        // tests for testConnectedUser
        Assert.assertSame(connectedUser, newUserConnection.getConnectedUser());
    }
}