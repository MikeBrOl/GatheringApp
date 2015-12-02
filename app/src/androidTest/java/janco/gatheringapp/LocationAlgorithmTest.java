//package janco.gatheringapp;
//
//import android.test.AndroidTestCase;
//
//import junit.framework.Assert;
//
//import java.util.ArrayList;
//
//import janco.gatheringapp.Activities.LocationAlgorithm;
//
///**
// * Created by Mads on 02-12-2015.
// */
//public class LocationAlgorithmTest extends AndroidTestCase
//{
//    private LocationAlgorithm algorithm;
//    public LocationAlgorithmTest(){
//        this.algorithm = new LocationAlgorithm();
//    }
//
//    public void testDependencies()
//    {
//        assertNotNull(algorithm);
//    }
//
//    public void testLocationAlgorithmForNotices()
//    {
//        GeoPoint mockLocation = new GeoPoint();
//        double mockRadius = 2000;
//        ArrayList<Notice> testList = algorithm.boundingBoxCalculation(0, mockLocation, mockRadius);
//        Assert.assertNotNull(testList);
//    }
//
//    public void testLocationAlgorithmForUsers()
//    {
//        GeoPoint mockLocation = new GeoPoint();
//        double mockRadius = 2000;
//        ArrayList<User> testList = algorithm.boundingBoxCalculation(1, mockLocation, mockRadius);
//        Assert.assertNotNull(testList);
//    }
//
//}
