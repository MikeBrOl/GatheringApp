package janco.gatheringapp.Database;

import android.util.Log;

import com.google.android.maps.GeoPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.*;
// local imports
import janco.gatheringapp.Model.Notice;
import janco.gatheringapp.Model.User;
import janco.gatheringapp.Database.DBConnection;

/**
 * Created by Torn on 02-12-2015.
 */
public class DBLocationFinder
{
    //opslags: dato
    //User: status



    public DBLocationFinder()
    {

    }

    /**
     * Find users based on radius and user status
     *
     * @param latMax - max latitude limit
     * @param latMin - min latitude limit
     * @param longMax - max longitude limit
     * @param longMin - min longitude limit
     * @param status - if found users should be active or non-active
     * @return foundUsers - found users in data who fit inside the specificed radius
     */
    public ArrayList<User> getUsersByRadiusAndStatus(double latMax, double latMin, double longMax, double longMin,
                                                boolean status)
    {
        // empty list to put found users from database in
        ArrayList<User> foundUsers = new ArrayList<>();
        boolean newUserStatus;
        int intStatus;
        if(status)
        {
            intStatus = 1;
        }
        else
        {
            intStatus = 0;
        }

        String latMaxString = String.valueOf(latMax);
        String latMinString = String.valueOf(latMin);
        String longMaxString = String.valueOf(longMax);
        String longMinString = String.valueOf(longMin);

        DBConnection dbConnection = new DBConnection();

        try {
            PreparedStatement statement = dbConnection.CONN().prepareStatement
                    ("SELECT * FROM Users " +
                            "WHERE Latitude BETWEEN ? AND ? AND " +
                            "Longitude BETWEEN ? AND ? AND "+ "SearchStatus = ?");

            // set parameters for query
            statement.setInt(5, intStatus);        // status is either 0 or 1
            statement.setString(1, latMinString);     // set latMin and latMax
            statement.setString(2, latMaxString);
            statement.setString(3, longMinString);    // set longMin and longMax
            statement.setString(4, longMaxString);

            ResultSet rs = statement.executeQuery();

            rs.first();

            // put found users in the foundUsers list
            while (rs.next()) {
                String username = rs.getString("Username");
                String name = rs.getString("Name");
                String password = rs.getString("Password"); // HASH?
                String email = rs.getString("Email");

                // generate geopoint for user
                double latitude = Double.valueOf(rs.getString("Latitude"));
                double longitude = Double.valueOf(rs.getString("Longitude"));
                int newUserIntStatus = rs.getInt("Status");

                if(newUserIntStatus == 1)
                {
                    newUserStatus = true;
                }
                else
                {
                    newUserStatus = false;
                }

                // generate new user from found resultset
                User foundUser = new User(username, name, password, email, latitude, longitude, newUserStatus);

                foundUsers.add(foundUser);
            }
            rs.close();
        }
        catch (SQLException getUserSqlError)
        {
            Log.e("Error", getUserSqlError.getMessage());
        }


        return foundUsers;
    }

    /*
    Find Notices based on radius and date
     */
    public ArrayList<Notice> getNoticesByRadiusAndDate(double latMax, double latMin, double longMax, double longMin,
                                                  Date date)
    {
        String dateString = date.toString();
        ArrayList<Notice> foundNotices = new ArrayList<>();
        DBConnection dbCon = new DBConnection();

        try{
            PreparedStatement statement = dbCon.CONN().prepareStatement
                    ("SELECT * FROM Notices " +
                            "WHERE date = ? AND " +
                            "Latitude BETWEEN ? AND ? AND"
                            +"Longitude BETWEEN ? AND ?");
            statement.setString(1, dateString);
            statement.setDouble(2, latMin);
            statement.setDouble(3, latMax);
            statement.setDouble(4, longMin);
            statement.setDouble(5, longMax);

            ResultSet rs = statement.executeQuery();

            rs.first();

            while(rs.next())
            {
                //navn, beskrivelse, billede, addresse, tidspunkt, lokation
                String navn = rs.getString("Navn");
                String beskrivelse = rs.getString("Beskrivelse");
                String addresse = rs.getString("Addresse");
                String tidspunkt = rs.getString("Tidspunkt");
                double latitude = rs.getDouble("Latitude");
                double longitude = rs.getDouble("Longitude");

                Notice foundNotice = new Notice();

                foundNotices.add(foundNotice);
            }

            rs.close();
        }
        catch (SQLException getNoticesSQLError)
        {
            Log.e("ERROR", getNoticesSQLError.getMessage());
        }


        return foundNotices;
    }


}
