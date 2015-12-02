//package janco.gatheringapp.Activities;
//
//import java.util.ArrayList;
//import java.util.List;
//import
//import java.util.Objects;
//
///**
// * Created by Mads on 02-12-2015.
// */
//public class LocationAlgorithm
//{
//    dbLocationFinder locationQuery;
//
//    public LocationAlgorithm(){
//        locationQuery = new locantionQuery();
//    }
//
//    /**
//     * A simple attempt at a boundingBox calculation to find min and max values for latitude and longitude
//     * @param index used to identify which sql statement should be queried
//     * @param currentLocation used to determine users current longitude and latitude
//     * @param radius user determined search radius in meter
//     * @return list of objects in radius of the user
//     */
//    public ArrayList<Object> boundingBoxCalculation(int index, GeoPoint currentLocation, double radius)
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
//        latCur = currentLocation.getLatitude();
//        lonCur = currentLocation.getLongitude();
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
//                listOfNoticesInRadius = locationQuery.getNoticesByRadiusAndDate(latMin, lonMin, latMax, lonMax);
//                return listOfNoticesInRadius;
//            case 1:
//                listOfUsersInRadius = locationQuery.getUsersByRadiusAndDate(latMin, lonMin, latMax, lonMax);
//                return listOfUsersInRadius;
//        }
//
//        return null;
//    }
//}
