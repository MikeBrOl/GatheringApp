package janco.gatheringapp.Database;

import android.util.Log;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import janco.gatheringapp.Model.UserConnection;

/**
 * Created by Mads on 10-12-2015.
 */
public class DBMessageSystem
{
    private DBConnection dbConnection;

    public DBMessageSystem()
    {
        this.dbConnection = new DBConnection();
    }

    public int createTable(String tableName, UserConnection userConnection)
    {
        int check = 1;
        try
        {
           Statement stmt = dbConnection.CONN().createStatement();
            String query = "IF EXISTS(SELECT * FROM dmaa0214_4Sem_Gruppe2.INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND TABLE_NAME = '"+tableName+"')" +
                    "BEGIN PRINT 'Table exists' END ELSE CREATE TABLE "+tableName+"([ID][int]IDENTITY(1,1)NOT NULL PRIMARY KEY, [Message][TEXT]NULL, [Sender][TEXT]NULL)";
            Log.e("Query:",query);
            check = stmt.executeUpdate(query);

            stmt.close();

            DBUserConnection DBUserConnection = new DBUserConnection();
            DBUserConnection.updateUserConnection(userConnection, tableName);
            //Insert message


            //Create table
            //Insert message
            //Update UserConnection with table name
            //rollback possibility
            stmt.close();
        }
        catch (SQLException createTableException)
        {
            Log.e("Error in creating table", createTableException.toString());
        }
        return check;
    }

    public int insertMessage(String message, String tableName, String userName)
    {
        int check = 0;

        try
        {
            Statement stmt = dbConnection.CONN().createStatement();
            String query = "INSERT INTO "+tableName+"(Message, Sender) Values('"+message+"', '"+userName+"')";
            check = stmt.executeUpdate(query);

            stmt.close();
        }
        catch(SQLException insertMessageException)
        {
            Log.e("Error inserting message", insertMessageException.toString());
        }
        return check;
    }

    public ArrayList<String> getChatsByUserName(String userName)
    {
        ArrayList<String> chats = new ArrayList<>();
        try
        {
            Statement stmt = dbConnection.CONN().createStatement();
            String query = "SELECT * FROM dmaa0214_4Sem_Gruppe2.INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME LIKE '%"+userName+"%'";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                chats.add(rs.getString("TABLE_NAME"));
            }
        }
        catch(SQLException chatsByUserName)
        {
            Log.e("ChatByUserName", chatsByUserName.getStackTrace().toString());
        }
        return chats;
    }
}
