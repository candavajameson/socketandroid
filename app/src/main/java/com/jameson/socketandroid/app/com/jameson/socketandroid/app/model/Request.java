package com.jameson.socketandroid.app.com.jameson.socketandroid.app.model;

/**
 * Created by Lenovo on 5/24/2014.
 */
public class Request {

    private int request_id;
    private String latitude;
    private String longitude;
  private int agent_id;

    public Request(int request_id, String latitude, String longitude, int agent_id) {
        this.request_id = request_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.agent_id = agent_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(int agent_id) {
        this.agent_id = agent_id;
    }
}
