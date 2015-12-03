package janco.gatheringapp.Database;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import janco.gatheringapp.Model.User;

/**
 * Created by Mike on 02-12-2015.
 */
public class DBUser
{
    private DBConnection dbConnection;

    public DBUser()
    {
        this.dbConnection = new DBConnection();
    }

    public User getUserByUsername(String username)
    {
        User user = new User();

        try
        {
            Connection con = dbConnection.CONN();

            String query = "select * from Users where UserName = " + username;

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();

            double latitude = Double.parseDouble(rs.getString("Latitude"));
            double longitude = Double.parseDouble(rs.getString("Longitude"));
            boolean ss;

            if(rs.getInt("SearchStatus") == 1)
            {
                ss = true;
            }
            else
            {
                ss = false;
            }

            user.setName(rs.getString("Name"));
            user.setUsername(rs.getString("UserName"));
            user.setPassword(rs.getString("Password"));
            user.setEmail(rs.getString("Email"));
            user.setLastKnownlatitude(latitude);
            user.setLastKnownLongitude(longitude);
            user.setSearchStatus(ss);

            rs.close();


        }catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());
        }

        return user;
    }

    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> userList = new ArrayList<>();

        try
        {
            Connection con = dbConnection.CONN();

            String query = "select * from Users";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            rs.first();

            while (rs.next())
            {
                String name = rs.getString("Name");
                String username = rs.getString("UserName");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                double latitude = Double.parseDouble(rs.getString("Latitude"));
                double longitude = Double.parseDouble(rs.getString("Longitude"));
                int searchStatus = rs.getInt("SearchStatus");

                boolean ss;
                if (searchStatus == 1)
                {
                    ss = true;
                }
                else
                {
                    ss = false;
                }

                User user = new User(name, username, password, email, latitude, longitude, ss);
                userList.add(user);
            }
            rs.close();

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());
        }

        return userList;
    }

    public int insertUser(User user)
    {
        Connection con = dbConnection.CONN();
        int check = 0;

        try {
            String name = user.getName();
            String username = user.getUsername();
            String password = user.getPassword();
            String email = user.getEmail();
            double longitude = user.getLastKnownLongitude();
            double latitude = user.getLastKnownlatitude();
            boolean ss = user.isSearchStatus();

            String lon = String.valueOf(longitude);
            String lat = String.valueOf(latitude);
            int searchStatus;

            if(ss == true)
            {
                searchStatus = 1;
            }
            else
            {
                searchStatus = 0;
            }

            Statement stmt = con.createStatement();
            String query = "INSERT INTO Users " +
                    "VALUES ('"+ name + "', '" +
                    username +"', '" +
                    password +"', '" +
                    email +"', " +
                    lat + ", " +
                    lon + ", "+
                    searchStatus +
                    ");";
            Log.e("Query:",query);
            check = stmt.executeUpdate(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());
            if (con != null)
            {
                try
                {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                }
                catch (SQLException se)
                {
                    Log.e("SQL ERROR", e.getMessage());

                }
            }
        }
        return check;
    }

    public int deleteUser(User user)
    {
        int check = 0;
        Connection con = dbConnection.CONN();

        try
        {
            String username = user.getUsername();
            Statement stmt = con.createStatement();

            String query = "DELETE FROM Users " +
                    "Where UserName=" + "'" + username + "';";

            check = stmt.executeUpdate(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());
            if (con != null)
            {
                try
                {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                }
                catch (SQLException se)
                {
                    Log.e("ERROR", se.getMessage());
                }
            }
        }

        return check;


    }

    public int updateUser(User user)
    {
        Connection con = dbConnection.CONN();
        int check = 0;


        try
        {
            String name = user.getName();
            String username = user.getUsername();
            String password = user.getPassword();
            String email = user.getEmail();
            double longitude = user.getLastKnownLongitude();
            double latitude = user.getLastKnownlatitude();
            boolean ss = user.isSearchStatus();

            String lon = String.valueOf(longitude);
            String lat = String.valueOf(latitude);
            int searchStatus;

            if(ss == true)
            {
                searchStatus = 1;
            }
            else
            {
                searchStatus = 0;
            }

            Statement stmt = con.createStatement();
            String query = "UPDATE Users " +
                    "SET Name='" + name + "', " +
                    "Password='" + password + "', " +
                    "Email='" + email + "', " +
                    "Latitude='" + lat + "', " +
                    "Longitude='" + lon + "', " +
                    "SearchStatus=" + searchStatus +
                    " WHERE Username='" + username + "';"
                    ;

            check = stmt.executeUpdate(query);


        }
        catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());
            if (con != null)
            {
                try
                {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                }
                catch (SQLException se)
                {
                    Log.e("ERROR", se.getMessage());
                }
            }
        }

        return check;



    }
}
