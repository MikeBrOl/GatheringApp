//package janco.gatheringapp.Activities;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import com.google.android.maps.GeoPoint;
//import java.util.Objects;
//
//import janco.gatheringapp.Database.DBLocationFinder;
//
///**
// * Created by Mads on 02-12-2015.
// */
//public class LocationAlgorithm
//{
//    DBLocationFinder locationQuery;
//
//    public LocationAlgorithm(){
//        locationQuery = new DBLocationFinder();
//    }
//
//    /**
//     * A simple attempt at a boundingBox calculation to find min and max values for latitude and longitude
//     * @param index used to identify which sql statement should be queried
//     * @param currentLocation used to determine users current longitude and latitude
//     * @param radius user determined search radius in meter
//     * @return list of objects in radius of the user
//     */
//    public ArrayList<Object> boundingBoxCalculation(int index, GeoPoint currentLocation, double radius, Date date, int status)
//    {
//        ArrayList<Object> listOfNoticesInRadius = new ArrayList<>();
//        ArrayList<Object> listOfUsersInRadius = new ArrayList<>();
//        double latVar;
//        double lonVar;
//        double latMin;
//        double latMax;
//        double lonMin;
//        double lonMax;
//        double latCur;
//        double lonCur;
//
//        latCur = currentLocation.getLatitudeE6();
//        lonCur = currentLocation.getLongitudeE6();
//
//        latVar = radius/31;
//        lonVar = radius/23.72;
//        latMin = latCur-latVar;
//        latMax = latCur+latVar;
//        lonMin = lonCur-lonVar;
//        lonMax = lonCur+lonVar;
//
//        switch(index)
//        {
//            case 0:
//                listOfNoticesInRadius.addAll(locationQuery.getNoticesByRadiusAndDate(latMin, lonMin, latMax, lonMax, date));
//                return listOfNoticesInRadius;
//            case 1:
//                listOfUsersInRadius.addAll(locationQuery.getUsersByRadiusAndStatus(latMax, latMin, lonMax, lonMin, status));
//                return listOfUsersInRadius;
//        }
//
//        return null;
//    }
//}
