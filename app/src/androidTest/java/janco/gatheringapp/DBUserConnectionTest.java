package janco.gatheringapp;

import android.test.AndroidTestCase;
import android.util.Log;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import janco.gatheringapp.Database.DBUser;
import janco.gatheringapp.Database.DBUserConnection;
import janco.gatheringapp.Model.User;
import janco.gatheringapp.Model.UserConnection;


/**
 * Created by Torn on 10-12-2015.
 */
public class DBUserConnectionTest extends AndroidTestCase
{
    DBUserConnection dbUserConnection;
    DBUser dbUser;





    public DBUserConnectionTest() {
        dbUserConnection = new DBUserConnection();

        dbUser = new DBUser();
    }

    public void testDependencies()
    {
        Assert.assertNotNull(dbUserConnection);
    }

    //
    public void testInsertUserConnection()
    {
        User appUser = dbUser.getUserByUsername("testUsername");
        User connectedUser = dbUser.getUserByUsername("InsertTest");


        UserConnection testUserConnection = new UserConnection(appUser, connectedUser);

        int success = dbUserConnection.insertUserConnection(testUserConnection);

        Assert.assertEquals(1, success);

    }

    public void testDeleteUserConnection()
    {
        User appUser = dbUser.getUserByUsername("testUsername");
        User connectedUser = dbUser.getUserByUsername("InsertTest");

        UserConnection testUserConnection = new UserConnection(appUser, connectedUser);

        int success = dbUserConnection.deleteUserConnection(testUserConnection);
        Assert.assertEquals(1, success );
    }

    public void testGetUserConnectionsByAppUser()
    {
        User appUser = dbUser.getUserByUsername("testUsername");
        ArrayList<UserConnection> appUserConnections = dbUserConnection.getUserConnectionsByAppUser(appUser);

        boolean status;
        if(appUserConnections.size() > 0) {
            status = true;
        }
        else
        {
            status = false;
        }


        Assert.assertEquals(true, status);
    }

    public void testCheckForExistingUserConnection()
    {
        User appUser = dbUser.getUserByID(35);
        User connectedUser = dbUser.getUserByID(34);
        UserConnection reverseConnection = dbUserConnection.checkForExistingUserConnection(appUser,connectedUser);

        Assert.assertEquals(35, reverseConnection.getAppUser().getID());
    }

    public void testUpdateUserConnection()
    {
        User appUser = dbUser.getUserByID(35);
        User connectedUser = dbUser.getUserByID(34);
        UserConnection userConnection = new UserConnection(appUser,connectedUser);
        String tableName = "Kimmie";
        int check = dbUserConnection.updateUserConnection(userConnection, tableName);

        Assert.assertEquals(1, check);
    }
}
