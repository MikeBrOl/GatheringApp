package janco.gatheringapp;

import android.test.AndroidTestCase;
import android.util.Log;

import junit.framework.Assert;

import java.util.ArrayList;

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

        Assert.assertEquals(1, success );

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
        //User appUser = dbUser.getUserByUsername("test Username");
        //ArrayList<UserConnection> appUserUserConnections= dbUserConnection.getUserConnectionsByAppUser(appUser);

        //Assert.assertNotNull("Arraylist for found user connections: ", appUserUserConnections);
    }
}
