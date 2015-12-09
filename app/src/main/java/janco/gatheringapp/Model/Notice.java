package janco.gatheringapp.Model;


import java.util.Date;

/**
 * Created by Mads on 01-12-2015.
 */
public class Notice
{
    String name;
    String description;
    String address;
    String time;
    float latitude;
    float longitude;

    public Notice() {}

    public Notice(String name, String description, String address, String time,
                  float latitude, float longitude)
    {
        this.name = name;
        this.description = description;
        this.address = address;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", time=" + time +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
