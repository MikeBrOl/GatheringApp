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
    GeoPoint lastKnownLocation;

    public User()
    {

    }
    public User(String username, String name, String password, String email, GeoPoint lastKnownLocation)
    {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.lastKnownLocation = lastKnownLocation;
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

    public GeoPoint getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(GeoPoint lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastKnownLocation=" + lastKnownLocation +
                '}';
    }
}
