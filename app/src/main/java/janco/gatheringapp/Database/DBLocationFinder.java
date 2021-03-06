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

    private DBConnection dbConnection;

    public DBLocationFinder()
    {
        this.dbConnection = new DBConnection();
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
                                                boolean status, int id)
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

        float latMaxFloat = (float) latMax;
        float latMinFloat = (float) latMin;
        float longMaxFloat = (float) longMax;
        float longMinFloat = (float) longMin;


        try {
            Connection con = dbConnection.CONN();

            PreparedStatement statement = con.prepareStatement
                    ("SELECT * FROM Users " +
                            "WHERE Latitude BETWEEN ? AND ? AND " +
                            "Longitude BETWEEN ? AND ? AND " +
                            "SearchStatus = ? AND ID != ?");

            // set parameters for query
            statement.setFloat(1, latMinFloat);     // set latMin and latMax
            statement.setFloat(2, latMaxFloat);
            statement.setFloat(3, longMinFloat);    // set longMin and longMax
            statement.setFloat(4, longMaxFloat);
            statement.setInt(5, intStatus);         // status is either 0 or 1
            statement.setInt(6, id);

            ResultSet rs = statement.executeQuery();

            // put found users in the foundUsers list
            while (rs.next()) {
                String username = rs.getString("UserName");
                String name = rs.getString("Name");
                String password = rs.getString("Password"); // HASH?
                String email = rs.getString("Email");

                // generate geopoint for user
                float dbLatitude = rs.getFloat("Latitude");
                float dbLongitude = rs.getFloat("Longitude");

                double latitude = (double) dbLatitude;
                double longitude = (double) dbLongitude;

                int newUserIntStatus = rs.getInt("SearchStatus");

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
                Log.e("User Email", foundUser.getEmail());
                foundUser.setID(rs.getInt("ID"));
                foundUsers.add(foundUser);
            }
            rs.close();
            statement.close();
            con.close();
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
    public ArrayList<Notice> getNoticesByRadiusAndDate(float latMax, float latMin, float longMax, float longMin,
                                                  String date)
    {
        ArrayList<Notice> foundNotices = new ArrayList<>();

        try{
            Connection con = dbConnection.CONN();

            PreparedStatement statement = con.prepareStatement
                    ("SELECT * FROM Notices " +
                            "WHERE Time > ? AND " +
                            "Latitude BETWEEN ? AND ? AND "
                            + "Longitude BETWEEN ? AND ?");
            statement.setString(1, date);
            statement.setFloat(2, latMin);
            statement.setFloat(3, latMax);
            statement.setFloat(4, longMin);
            statement.setFloat(5, longMax);

            ResultSet rs = statement.executeQuery();


            while(rs.next())
            {
                //navn, beskrivelse, billede, addresse, tidspunkt, lokation
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String address = rs.getString("Address");
                String time = rs.getString("Time");
                double latitude = rs.getDouble("Latitude");
                double longitude = rs.getDouble("Longitude");
                float conLati = (float) latitude;
                float conLon = (float) longitude;
                Notice foundNotice = new Notice(name, description, address, time, conLati,conLon);


                foundNotices.add(foundNotice);
            }

            rs.close();
            statement.close();
            con.close();
        }
        catch (SQLException getNoticesSQLError)
        {
            Log.e("ERROR", getNoticesSQLError.getMessage());
        }


        return foundNotices;
    }


}
