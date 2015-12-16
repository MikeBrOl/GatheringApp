package janco.gatheringapp;

import android.test.AndroidTestCase;
import android.util.Log;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Assert.assertEquals(0, check);
    }

    public void testInsertMessage()
    {
        String tableName = "Kimmie";
        String message = "Hej Kimmie";
        String userName = "Lille O";

        int check = system.insertMessage(message, tableName, userName);

        Assert.assertEquals(1,check);
    }

    public void testFindMessagesByUserName()
    {
        boolean success = false;
        int index = 0;
        String userName = "mad_mike";
        ArrayList<String> chats = system.getChatsByUserName(userName);
                while(chats.size() > index)
        {
            Log.e("Nuværede Tabel", chats.get(index));
            index++;
        }if(chats.size()>0)
    {
        success = true;
    }

        Assert.assertEquals(true, success);
    }

    public void testGetAllMessage()
    {
        boolean succes = false;
        int index = 0;
        String tableName = "julemandmad_mike";
        List<Map<String, String>> message = system.getAllMessages(tableName);
        if(message.size() > 0)
        {
            succes = true;
        }
        while(message.size() > index)
        {
            Log.e("Message", message.get(index).get("Message"));
            index++;
        }

        Assert.assertEquals(true, succes);
    }
}
