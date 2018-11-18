package com.example.android.persistence.codelab.airportDB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
public class Flight {
    @PrimaryKey
    @NonNull
    public String id;
    public String flightNumber;
    public String origin;
    public String destination;
    public Date takeoffTime;
    public Date landingTime;
}
