package janco.gatheringapp.Activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.android.maps.GeoPoint;
import java.util.Objects;

import janco.gatheringapp.Database.DBLocationFinder;
import janco.gatheringapp.Model.Notice;
import janco.gatheringapp.Model.User;

/**
 * Created by Mads on 02-12-2015.
 */
public class LocationAlgorithm
{
    DBLocationFinder locationQuery;

    public LocationAlgorithm(){
        locationQuery = new DBLocationFinder();
    }

    /**
     * A simple variant of the bounding box algorithm, using a radius and date object to determine what notices are within range and still active
     * @param latitude is a double
     * @param longitude is a double
     * @param radius is a simple int, unit of meassure meters
     * @param date a date object
     * @return ArrayList<Notice> with all available notices within the given radius based of the latitude and longitude of the current position
     */
    public ArrayList<Notice> boundingBoxCalculationForNotice(double latitude, double longitude, double radius, Date date)
    {
        ArrayList<Notice> listOfNoticesInRadius = new ArrayList<>();
        double latVar;
        double lonVar;
        double latMin;
        double latMax;
        double lonMin;
        double lonMax;


        latVar = radius/31;
        lonVar = radius/23.72;
        latMin = latitude-latVar;
        latMax = latitude+latVar;
        lonMin = longitude-lonVar;
        lonMax = longitude+lonVar;

        listOfNoticesInRadius.addAll(locationQuery.getNoticesByRadiusAndDate(latMin, lonMin, latMax, lonMax, date));

        return listOfNoticesInRadius;
    }

    /**
     *
     * @param latitude is a double
     * @param longitude is a double
     * @param radius is an int, unit of meassurement is meters
     * @param status is a boolean, used to decide wether or not someone wants to find another person within range
     * @return ArrayList<User> with all the available users within the given radius based of the latitude and longitude of the current position
     */
    public ArrayList<User> boundingBoxCalculationForUsers(double latitude, double longitude, double radius, boolean status)
    {
        ArrayList<User> listOfUsersInRadius = new ArrayList<>();
        double latVar;
        double lonVar;
        double latMin;
        double latMax;
        double lonMin;
        double lonMax;
        int statusInInt;


        latVar = radius/31;
        lonVar = radius/23.72;
        latMin = latitude-latVar;
        latMax = latitude+latVar;
        lonMin = longitude-lonVar;
        lonMax = longitude+lonVar;

        if(status)
        {
            statusInInt = 1;
        }
        else
        {
            statusInInt = 0;
        }

        listOfUsersInRadius.addAll(locationQuery.getUsersByRadiusAndStatus(latMin, lonMin, latMax, lonMax, statusInInt));

        return listOfUsersInRadius;
    }
}
