package janco.gatheringapp;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import janco.gatheringapp.Database.DBMessageSystem;
import janco.gatheringapp.Database.DBUser;
import janco.gatheringapp.Model.User;
import janco.gatheringapp.Model.UserConnection;

/**
 * Created by Mads on 10-12-2015.
 */
public class DBMessageSystemTest extends AndroidTestCase
{
    private DBMessageSystem system;
    public DBMessageSystemTest()
    {
        system = new DBMessageSystem();
    }

    public void testDependencies(){Assert.assertNotNull(system);}

    public void testCreateTable()
    {
        String tableName = "Kimmie";
        DBUser dbUser = new DBUser();
        User appUser = dbUser.getUserByID(35);
        User connectedUser = dbUser.getUserByID(34);
        UserConnection userConnection = new UserConnection(appUser, connectedUser);

        int check = system.createTable(tableName, userConnection);

        Assert.assertEquals(0,check);
    }

    public void testInsertMessage()
    {
        String tableName = "Kimmie";
        String message = "Hej Kimmie";
        String userName = "Lille O";

        int check = system.insertMessage(message, tableName, userName);

        Assert.assertEquals(1,check);
    }
}
