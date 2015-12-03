package janco.gatheringapp;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import com.google.android.maps.GeoPoint;
import java.util.ArrayList;
import java.util.Date;

import janco.gatheringapp.Activities.LocationAlgorithm;
import janco.gatheringapp.Model.Notice;
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
        double mockLatitude = 57.0481705;
        double mockLongitude = 9.92281909999997;
        double mockRadius = 2000;
        Date mockDate = new Date();
        ArrayList<Notice> testList = algorithm.boundingBoxCalculationForNotice(mockLatitude, mockLongitude, mockRadius, mockDate);
        Assert.assertNotNull(testList);
    }

    public void testLocationAlgorithmForUsers()
    {
        //TODO create date and status objects
        double mockLatitude = 57.0481705;
        double mockLongitude = 9.92281909999997;
        double mockRadius = 2000;
        boolean mockStatus = true;
        ArrayList<User> testList = algorithm.boundingBoxCalculationForUsers(mockLatitude, mockLongitude, mockRadius, mockStatus);
        Assert.assertNotNull(testList);
    }

}
