package com.example.android.persistence.codelab.airportDB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Passenger {
    @PrimaryKey
    @NonNull
    public String id;
    public String name;
    public String lastName;
    public int age;
}
