package com.jameson.socketandroid.app.com.jameson.socketandroid.app.model;

import java.io.Serializable;

/**
 * Created by Lenovo on 5/25/2014.
 */
public class Agent implements Serializable {

    private int id;

    public Agent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
