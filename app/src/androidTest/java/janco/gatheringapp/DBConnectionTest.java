package janco.gatheringapp;

import android.test.AndroidTestCase;

import junit.framework.Assert;
import junit.framework.TestCase;

import janco.gatheringapp.Database.DBConnection;


/**
 * Created by Mike on 01-12-2015.
 */
public class DBConnectionTest extends AndroidTestCase
{
    private DBConnection dbCon;

    public DBConnectionTest()
    {
        this.dbCon = new DBConnection();

    }

    public void testDBConnection()
    {
        Assert.assertNotNull(dbCon.CONN());
    }
}
