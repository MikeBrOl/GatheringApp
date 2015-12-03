//package janco.gatheringapp.Database;
//
//import com.google.android.maps.GeoPoint;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import java.sql.*;
//// local imports
//import janco.gatheringapp.Model.User;
//import janco.gatheringapp.Database.DBConnection;
//
///**
// * Created by Torn on 02-12-2015.
// */
//public class DBLocationFinder
//{
//    //opslags: dato
//    //User: status
//
//
//
//    public DBLocationFinder()
//    {
//
//    }
//
//    /**
//     * Find users based on radius and user status
//     *
//     * @param latMax - max latitude limit
//     * @param latMin - min latitude limit
//     * @param longMax - max longitude limit
//     * @param longMin - min longitude limit
//     * @param status - if found users should be active or non-active
//     * @return foundUsers - found users in data who fit inside the specificed radius
//     */
//    public List<User> getUsersByRadiusAndStatus(double latMax, double latMin, double longMax, double longMin,
//                                                int status)
//    {
//        // empty list to put found users from database in
//        ArrayList<User> foundUsers = new ArrayList<User>();
//
//        DBConnection dbConnection = new DBConnection();
//
//        PreparedStatement statement = dbConnection.CONN().prepareStatement
//                ("SELECT * FROM Users " +
//                        "WHERE SearchStatus = ? AND " +
//                        "Latitude BETWEEN ? AND ?" +
//                        "Longitude BETWEEN ? AND ?");
//
//        // set parameters for query
//        statement.setInt(1, status);        // status is either 0 or 1
//        statement.setDouble(2, latMin);     // set latMin and latMax
//        statement.setDouble(3, latMax);
//        statement.setDouble(4, longMin);    // set longMin and longMax
//        statement.setDouble(5, longMax);
//
//        ResultSet rs = statement.executeQuery();
//
//        // put found users in the foundUsers list
//        while (rs.next())
//        {
//            String username = rs.getString("Username");
//            String name = rs.getString("Name");
//            String password = rs.getString("Password"); // HASH?
//            String email = rs.getString("Email");
//
//            // generate geopoint for user
//            double latitude = rs.getDouble("Latitude");
//            double longitude = rs.getDouble("Longitude");
//            GeoPoint lastKnownLocation = new GeoPoint(latitude, longitude);
//
//            // generate new user from found resultset
//            User foundUser = new User(username, name, password, email, lastKnownLocation);
//
//            foundUsers.add(foundUser);
//        }
//
//
//        return foundUsers;
//    }
//
//    /*
//    Find Notices based on radius and date
//     */
//    public List<Notice> getNoticesByRadiusAndDate(double latMax, double latMin, double longMax, double LongMin,
//                                                  Date date)
//    {
//        List<Notice> foundNotices = new List<Notice>();
//
//
//        return foundNotices;
//    }
//
//
//}
