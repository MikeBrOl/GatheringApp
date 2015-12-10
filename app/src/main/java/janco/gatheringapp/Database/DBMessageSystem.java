package janco.gatheringapp.Database;

import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import janco.gatheringapp.Model.UserConnection;

/**
 * Created by Mads on 10-12-2015.
 */
public class DBMessageSystem
{
    private DBConnection con;

    public DBMessageSystem()
    {
        con = new DBConnection();
    }

    public int createTable(String tableName)
    {
        int check = 1;
        try
        {
           Statement stmt = con.CONN().createStatement();
            String query = "IF EXISTS(SELECT * FROM dmaa0214_4Sem_Gruppe2.INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND TABLE_NAME = '"+tableName+"')" +
                    "BEGIN PRINT 'Table exists' END ELSE CREATE TABLE "+tableName+"([ID][int]IDENTITY(1,1)NOT NULL PRIMARY KEY, [Message][TEXT]NULL)";
            Log.e("Query:",query);
            check = stmt.executeUpdate(query);

            //Insert message


            //Create table
            //Insert message
            //Update UserConnection with table name
            //rollback possibility
        }
        catch (SQLException createTableException)
        {
            Log.e("Error in creating table", createTableException.toString());
        }
        return check;
    }

    public int insertMessage(String message, String tableName)
    {
        int check = 0;

        try
        {
            Statement stmt = con.CONN().createStatement();
            String query = "INSERT INTO "+tableName+"(Message) Values('"+message+"')";
            check = stmt.executeUpdate(query);
        }
        catch(SQLException insertMessageException)
        {
            Log.e("Error inserting message", insertMessageException.toString());
        }
        return check;
    }
}