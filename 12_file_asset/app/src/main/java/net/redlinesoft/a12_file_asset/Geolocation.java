package net.redlinesoft.a12_file_asset;

/**
 * Created by xavier on 5/16/2017 AD.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geolocation {



    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;

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
    public Geolocation(Double lat, Double lon) {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

}
