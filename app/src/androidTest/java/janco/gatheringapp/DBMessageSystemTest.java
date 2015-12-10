package janco.gatheringapp;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import janco.gatheringapp.Database.DBMessageSystem;

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

        int check = system.createTable(tableName);

        Assert.assertEquals(0,check);
    }

    public void testInsertMessage()
    {
        String tableName = "Kimmie";
        String message = "Hej Kimmie";

        int check = system.insertMessage(message, tableName);

        Assert.assertEquals(1,check);
    }
}
