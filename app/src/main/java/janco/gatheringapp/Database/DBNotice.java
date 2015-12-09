package janco.gatheringapp.Database;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import janco.gatheringapp.Model.Notice;

/**
 * Created by Mike on 08-12-2015.
 */
public class DBNotice
{

    private DBConnection dbConnection;

    public DBNotice()
    {
        this.dbConnection = new DBConnection();
    }

    public int insertNotice(Notice notice)
    {

        Connection con = dbConnection.CONN();
        int check = 0;



        try {
            String name = notice.getName();
            String description = notice.getDescription();
            String address = notice.getAddress();
            float lat = notice.getLatitude();
            float lon = notice.getLongitude();

            String date = notice.getTime();



            PreparedStatement stmt = con.prepareStatement
                    ("INSERT INTO Notices VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, address);
            stmt.setString(4, date);
            stmt.setFloat(5, lat);
            stmt.setFloat(6, lon);

            check = stmt.executeUpdate();
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());
        }

        return check;
    }

    public int deleteNotice(Notice notice)
    {
        int check = 0;
        Connection con = dbConnection.CONN();


        try
        {
            String name = notice.getName();

            PreparedStatement stmt = con.prepareStatement
                    ("DELETE FROM Notices WHERE Name= ?");

            stmt.setString(1, name);
            check = stmt.executeUpdate();
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            if(con != null)
            {
                try
                {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                }
                catch (SQLException se)
                {
                    Log.e("ERROR", se.getMessage());
                }
            }
        }

        return check;
    }


    public Notice getNoticeByName(String name)
    {
        Notice notice = new Notice();

        try
        {
            Connection con = dbConnection.CONN();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Notices WHERE Name = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            notice.setName(rs.getString("Name"));
            notice.setDescription(rs.getString("Description"));
            notice.setAddress(rs.getString("Address"));
            notice.setTime(rs.getString("Time"));

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());
        }

        return notice;
    }


}
