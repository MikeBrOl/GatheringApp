package janco.gatheringapp.Model;


/**
 * Created by Torn on 04-12-2015.
 */
public class UserConnection
{
    User appUser;
    User connectedUser;
    // date and time?

    public UserConnection()
    {

    }

    public UserConnection(User appUser, User connectedUser)
    {
        this.appUser = appUser;
        this.connectedUser = connectedUser;
    }

    public User getAppUser() {
        return appUser;
    }

    public void setAppUser(User appUser) {
        this.appUser = appUser;
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

     @Override
      public String toString() {
      return "UserConnection{" +
             "appUser=" + appUser.getUsername() +
                ", connectedUser=" + connectedUser.getUsername() +
                '}';
    }
}
