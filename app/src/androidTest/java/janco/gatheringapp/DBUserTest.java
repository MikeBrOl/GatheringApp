package janco.gatheringapp;

import android.test.AndroidTestCase;
import junit.framework.Assert;

import java.util.List;

import janco.gatheringapp.Database.DBUser;
import janco.gatheringapp.Model.User;

/**
 * Created by Mike on 02-12-2015.
 */
public class DBUserTest extends AndroidTestCase
{
    private DBUser userDB;

    public DBUserTest()
    {
        userDB = new DBUser();
    }


    //User with username test has to be in the database before this test can be run
    public void testGetUserByUsername()
    {
        User user = userDB.getUserByUsername("Test");

        //Assert.assertNotNull(user);
        Assert.assertEquals("test", user.getUsername());
    }

    //Database must not contain a user with the username InsertTest when this test is run
    public void testInsertUser()
    {
        User user = new User("InsertTest", "Test", "InsertTest", "InsertTest", 57.02, 9.54, false);

        Assert.assertEquals(1, userDB.insertUser(user));
    }

    //User with username UpdateTest has to be in the database before the test can be run
    public void testUpdateUser()
    {
        User user = userDB.getUserByUsername("UpdateTest");

        user.setEmail("Updated");

        Assert.assertEquals(1, userDB.updateUser(user));
    }

    //User with username DeleteTest has to be in the database before this test can be run
    public void testDeleteUser()
    {
        User user = new User("DeleteTest", "DeleteTest", "DeleteTest", "DeleteTest", 57.02, 9.54, false);
        int check = userDB.deleteUser(user);

        Assert.assertEquals(check, 1);
    }


    public void testGetAllUsers()
    {
        List<User> users = userDB.getAllUsers();

        Assert.assertNotNull(users);
    }
}
