package janco.gatheringapp.Database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Mike on 01-12-2015.
 */
public class DBConnection
{
    private String ip = "kraka.ucn.dk";
    private String classs = "net.sourceforge.jtds.jdbc.Driver";
    private String db = "dmaa0214_4Sem_Gruppe2";
    private String userName = "dmaa0214_4Sem_Gruppe2";
    private String password = "IsAllowed";

    @SuppressLint("NewApi")
    public Connection CONN()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection conn = null;

        try
        {
            Class.forName(classs);
            String ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + userName + ";password="
                    + password + ";";

            conn = DriverManager.getConnection(ConnURL);
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("ERROR", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());
        }

        return conn;

    }



}
