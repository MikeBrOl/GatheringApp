package janco.gatheringapp.Database;

import java.util.ArrayList;
import java.util.List;

import janco.gatheringapp.Model.User;

/**
 * Created by Mike on 02-12-2015.
 */
public class DBUser
{

    public User getUserByUsername(String username)
    {
        User user = new User();


        return user;
    }

    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> userList = new ArrayList<>();

        return userList;
    }

    public void insertUser(User user)
    {

    }

    public int deleteUser(User user)
    {
        int check = 0;

        return check;
    }

    public void updateUser(User user)
    {

    }
}
