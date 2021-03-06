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
            String query = "IF NOT EXISTS (SELECT * FROM UserConnection " +
                    "WHERE UserID1 = " + appUserID + " AND UserID2 = " + connectedUserID + " OR " +
                    " UserID1 = " + connectedUserID + " AND UserID2 = " + appUserID + ")" +
                    "INSERT INTO UserConnection " +
                     "VALUES (null," + appUserID + ","
                      + connectedUserID + ");";

                Log.e("Query: ", query);

            check = statement.executeUpdate(query);

            statement.close();
            con.close();
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
                    ("DELETE FROM UserConnection WHERE UserID1 = ? AND UserID2 = ?");

            statement.setInt(1, UserID1);
            statement.setInt(2, UserID2);

            check = statement.executeUpdate();
            statement.close();
            con.close();

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
        ArrayList<UserConnection> foundUserConnections = new ArrayList<>();

        Connection con = dbConnection.CONN();

        try
        {
            int userID1 = appUser.getID();
            //appUser = dbUser.getUserByID(userID1);

            PreparedStatement statement = con.prepareStatement
                    ("SELECT * FROM UserConnection WHERE UserID1 = ? or UserID2= ?");

            statement.setInt(1, userID1);
            statement.setInt(2, userID1);

            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                int userID2 = rs.getInt("UserID2");
                User connectedUser = dbUser.getUserByID(userID2);
                UserConnection userConnection = new UserConnection(appUser, connectedUser);

                foundUserConnections.add(userConnection);
            }

            con.close();
            statement.close();
            rs.close();

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

    public UserConnection checkForExistingUserConnection(User appUser, User connectedUser)
    {
        UserConnection userConnection = new UserConnection();

        try
        {
            Connection con = dbConnection.CONN();


            // get the two users' IDs
            int appUserID = appUser.getID();
            int connectedUserID = connectedUser.getID();

            Statement statement = con.createStatement();
            String query = "SELECT * FROM UserConnection " +
                    "WHERE UserID1 = " + appUserID + " AND UserID2 = " + connectedUserID + " OR " +
                    " UserID1 = " + connectedUserID + " AND UserID2 = " + appUserID;

            Log.e("Query: ", query);
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            User userApp = dbUser.getUserByID(rs.getInt("UserID1"));
            User userConnected = dbUser.getUserByID(rs.getInt("UserID2"));
            userConnection.setAppUser(userApp);
            userConnection.setConnectedUser(userConnected);
            Log.e("UserConnection", Integer.toString(userConnection.getAppUser().getID()));

            con.close();
            statement.close();
            rs.close();
        }

        catch (SQLException sqle)
        {
            sqle.getStackTrace();
        }


        return userConnection;
    }

    public int updateUserConnection(UserConnection userConnection, String tableName)
    {
        int check = 0;
        try
        {
            Connection con = dbConnection.CONN();
            PreparedStatement stmt = con.prepareStatement("UPDATE UserConnection SET ChatName = ? WHERE UserID1 = ? AND UserID2 = ?");
            stmt.setString(1, tableName);
            stmt.setInt(2, userConnection.getAppUser().getID());
            stmt.setInt(3, userConnection.getConnectedUser().getID());

            check = stmt.executeUpdate();

            stmt.close();
        }
        catch(SQLException updateUserConnectionError)
        {
            Log.e("Error Connection:", updateUserConnectionError.getStackTrace().toString());
        }

        return check;
    }

}
