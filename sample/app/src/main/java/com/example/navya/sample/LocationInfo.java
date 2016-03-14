package com.example.navya.sample;

import org.json.JSONObject;

/**
 * Created by navya on 11/3/16.
 */
public class LocationInfo {
    private String name;
    private int Id;
    private JSONObject fromcentral;
    private double latitude;
    private double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public JSONObject getFromcentral() {
        return fromcentral;
    }

    public void setFromcentral(JSONObject fromcentral) {
        this.fromcentral = fromcentral;
    }
}