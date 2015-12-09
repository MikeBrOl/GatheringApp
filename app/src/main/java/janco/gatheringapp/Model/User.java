package janco.gatheringapp.Model;


import com.google.android.maps.GeoPoint;

/**
 * Created by Torn on 02-12-2015.
 */
public class User
{
    String username;
    String name;
    String password;
    String email;
    int ID;
    //GeoPoint lastKnownLocation;

    // user's
    double lastKnownLatitude;
    double lastKnownLongitude;

    boolean searchStatus;

    public User()
    {

    }
    public User(String username, String name, String password, String email,
                double lastKnownLatitude, double lastKnownLongitude, boolean searchStatus)
    {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        //this.lastKnownLocation = lastKnownLocation;
        this.lastKnownLatitude = lastKnownLatitude;
        this.lastKnownLongitude = lastKnownLongitude;
        this.searchStatus = searchStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setID(int ID) { this.ID = ID;}

    public int getID() { return ID;}

    /*
    public GeoPoint getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(GeoPoint lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }
    */

    public double getLastKnownlatitude() {
        return lastKnownLatitude;
    }

    public void setLastKnownlatitude(double lastKnownlatitude) {
        this.lastKnownLatitude = lastKnownlatitude;
    }

    public double getLastKnownLongitude() {
        return lastKnownLongitude;
    }

    public void setLastKnownLongitude(double lastKnownLongitude) {
        this.lastKnownLongitude = lastKnownLongitude;
    }

    public boolean isSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(boolean searchStatus) {
        this.searchStatus = searchStatus;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastKnownLatitude=" + lastKnownLatitude +
                ", lastKnownLongitude=" + lastKnownLongitude +
                '}';
    }
}
