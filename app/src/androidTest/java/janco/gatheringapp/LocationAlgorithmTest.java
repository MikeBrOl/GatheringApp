package janco.gatheringapp;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import com.google.android.maps.GeoPoint;
import java.util.ArrayList;

import janco.gatheringapp.Activities.LocationAlgorithm;
import janco.gatheringapp.Model.User;

/**
 * Created by Mads on 02-12-2015.
 */
public class LocationAlgorithmTest extends AndroidTestCase
{
    private LocationAlgorithm algorithm;
    public LocationAlgorithmTest(){
        this.algorithm = new LocationAlgorithm();
    }

    public void testDependencies()
    {
        assertNotNull(algorithm);
    }

    public void testLocationAlgorithmForNotices()
    {
        //TODO create date and status objects
        GeoPoint mockLocation = new GeoPoint(55, 55);
        double mockRadius = 2000;
        ArrayList<Notice> testList = algorithm.boundingBoxCalculation(0, mockLocation, mockRadius, date, status);
        Assert.assertNotNull(testList);
    }

    public void testLocationAlgorithmForUsers()
    {
        //TODO create date and status objects
        GeoPoint mockLocation = new GeoPoint(55,55);
        double mockRadius = 2000;
        ArrayList<User> testList = algorithm.boundingBoxCalculation(1, mockLocation, mockRadius, date, status);
        Assert.assertNotNull(testList);
    }

}
