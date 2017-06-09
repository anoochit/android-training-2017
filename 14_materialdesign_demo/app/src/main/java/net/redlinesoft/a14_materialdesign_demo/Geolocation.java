package net.redlinesoft.a14_materialdesign_demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Geolocation {


    // TODO 5 : add geolocation model from jsonschema2pojo

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;

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
