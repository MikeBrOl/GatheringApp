package janco.gatheringapp.Database;

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
        int check = 0;

        return 0;
    }

    public int deleteNotice(Notice notice)
    {
        int check = 0;

        return check;
    }


}
