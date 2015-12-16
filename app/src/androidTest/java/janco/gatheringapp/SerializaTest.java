package janco.gatheringapp;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import janco.gatheringapp.Model.User;


/**
 * Created by MrWeed on 12/16/2015.
 */
public class SerializaTest extends AndroidTestCase
{
    public void test()
    {
        String filename = "time.ser";
        User u = new User();
        u.setName("hej");

        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(u);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            u = (User) in.readObject();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(u);
        Assert.assertEquals("hej", u.getName());
    }
}
