package janco.gatheringapp.Database;

import android.util.Log;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import janco.gatheringapp.Model.User;
import janco.gatheringapp.Model.UserConnection;

/**
 * Created by Torn on 10-12-2015.
 */
public class DBUserConnection
{
    private DBConnection dbConnection;

     public DBUserConnection()
    {
        this.dbConnection = new DBConnection();
    }


    public int insertUserConnection(UserConnection newUserConnection)
    {
        int check = 0;

        try
        {
            Connection con = dbConnection.CONN();


            User appUser = newUserConnection.getAppUser();
            User connectedUser = newUserConnection.getConnectedUser();

            // check if the user connection already exists as appUser
            // equals appUser or connectedUser
            // otherwise save the user connection in the database

            // get the two users' IDs
            int appUserID = appUser.getID();
            int connectedUserID = connectedUser.getID();

            Statement statement = con.createStatement();
            String query = "IF NOT EXISTS (SELECT * FROM Connection " +
                    "WHERE UserID1 = " + appUserID + " AND UserID2 = " + connectedUserID + " OR " +
                    " UserID1 = " + connectedUserID + " AND UserID2 = " + appUserID + ")" +
                    "INSERT INTO Connection " +
                     "VALUES (" + appUserID + ","
                      + connectedUserID + ");";

                Log.e("Query: ", query);

            check = statement.executeUpdate(query);

        }

        catch (SQLException sqle)
        {
            sqle.getStackTrace();
        }

        return check;
    }

    public int deleteUserConnection(UserConnection userConnection)
    {
        int check = 0;



        return check;
    }

    public ArrayList<UserConnection> getUserConnectionsByAppUser(User appUser)
    {
        ArrayList<UserConnection> foundUserConnections = null;



        return foundUserConnections;
    }

//    private boolean checkForExistingUserConnection(User appUser, User connectedUser)
//    {
//        Connection con = dbConnection.CONN();
//
////        IF EXISTS (SELECT * FROM Table1 WHERE Column1='SomeValue')
////        UPDATE Table1 SET (...) WHERE Column1='SomeValue'
////        ELSE
////        INSERT INTO Table1 VALUES (...)
//
//        // get users' IDS
//        int appUserID = appUser.getID();
//        int connectedUserID = connectedUser.getID();
//
//        Statement statement = con.prepareStatement()
//
//
//        return false;
//    }

}
