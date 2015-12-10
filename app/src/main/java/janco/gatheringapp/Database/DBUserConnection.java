package janco.gatheringapp.Database;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private DBUser dbUser;

     public DBUserConnection()
    {
        this.dbConnection = new DBConnection();
        this.dbUser = new DBUser();
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

        Connection con = dbConnection.CONN();

        try
        {
            int UserID1 = userConnection.getAppUser().getID();
            int UserID2 = userConnection.getConnectedUser().getID();

            PreparedStatement statement = con.prepareStatement
                    ("DELETE FROM Connection WHERE UserID1 = ? AND UserID2 = ?");

            statement.setInt(1, UserID1);
            statement.setInt(2, UserID2);

            check = statement.executeUpdate();

        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());

            if(con != null)
            {
                try
                {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                }
                catch (SQLException sqle)
                {
                    Log.e("Error", sqle.getMessage());
                }
            }
        }

        return check;
    }

    public ArrayList<UserConnection> getUserConnectionsByAppUser(User appUser)
    {
        ArrayList<UserConnection> foundUserConnections = null;

        Connection con = dbConnection.CONN();

        try
        {
            int userID1 = appUser.getID();

            PreparedStatement statement = con.prepareStatement
                    ("SELECT FROM Connection WHERE UserID1 = ?");

            statement.setInt(1, userID1);

            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                int userID2 = rs.getInt("UserID2");
                User connectedUser = dbUser.getUserByID(userID2);
                UserConnection userConnection = new UserConnection(appUser, connectedUser);

                foundUserConnections.add(userConnection);
            }

        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());

            if(con != null)
            {
                try
                {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                }
                catch (SQLException sqle)
                {
                    Log.e("Error", sqle.getMessage());
                }
            }
        }



        return foundUserConnections;
    }

}
