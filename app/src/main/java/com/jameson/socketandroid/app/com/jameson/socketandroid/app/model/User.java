package com.jameson.socketandroid.app.com.jameson.socketandroid.app.model;

import java.io.Serializable;

/**
 * Created by Lenovo on 5/24/2014.
 */
public class User implements Serializable {
    private int id;

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
