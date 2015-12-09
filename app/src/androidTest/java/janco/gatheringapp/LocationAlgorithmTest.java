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
    boolean testSuccess;

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
        boolean testSuccess;
        double mockLatitude = 57.0481705;
        double mockLongitude = 9.92281909999997;
        double mockRadius = 2000;

        String mockDate = "2015-12-09 11:22:33";

        ArrayList<Notice> testList = algorithm.boundingBoxCalculationForNotice(mockLatitude, mockLongitude, mockRadius, mockDate);

        if(testList.size() > 0)
        {
            testSuccess = true;
        }
        else
        {
            testSuccess = false;
        }
        Assert.assertEquals(true, testSuccess);

    }

    public void testLocationAlgorithmForUsers()
    {
        double mockLatitude = 56;
        double mockLongitude = 9.54;
        double mockRadius = 2000;
        boolean mockStatus = true;
        ArrayList<User> testList = algorithm.boundingBoxCalculationForUsers(mockLatitude, mockLongitude, mockRadius, mockStatus);
        if(testList.size() > 0)
        {
            testSuccess = true;
        }
        else
        {
            testSuccess = false;
        }
        Assert.assertEquals(true, testSuccess);
    }

}
