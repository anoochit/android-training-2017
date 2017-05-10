package net.redlinesoft.a13_retrofit.model;

/**
 * Created by xavier on 5/3/2017 AD.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geolocation {

    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;

    /**
     * No args constructor for use in serialization
     *
     */
    public Geolocation() {
    }

    /**
     *
     * @param lon
     * @param lat
     */
    public Geolocation(String lat, String lon) {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

}