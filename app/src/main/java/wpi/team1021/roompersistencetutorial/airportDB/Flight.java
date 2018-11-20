package wpi.team1021.roompersistencetutorial.airportDB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
@TypeConverters(DateConverter.class)
public class Flight {
    @PrimaryKey
    @NonNull
    public String id;

    public String origin;

    public String destination;

    public Date takeoffTime;

    public Date landingTime;
}
