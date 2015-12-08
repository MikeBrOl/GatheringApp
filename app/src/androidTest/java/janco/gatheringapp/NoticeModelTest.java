package janco.gatheringapp;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.sql.Date;

import janco.gatheringapp.Model.Notice;

/**
 * Created by Torn on 08-12-2015.
 */
public class NoticeModelTest extends AndroidTestCase
{
    String name;
    String description;
    String address;
    Date time;
    float latitude;
    float longitude;

    public void testCreateNotice()
    {
        name = "Hans Kristian";
        description = "Bla bla, turning skal være her og der, you know";
        address = "test gaden 123";

        // set time
        time = new Date();
        time.getTime();


        // GPS
        latitude = 57.02;
        longitude = 9.54;

        Notice testNotice = Notice(name, description, address, time, latitude, longitude);

        Assert.assertEquals("test gaden 123", testNotice.getAddress());
    }






}
